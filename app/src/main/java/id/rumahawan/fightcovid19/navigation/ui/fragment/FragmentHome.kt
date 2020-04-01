package id.rumahawan.fightcovid19.navigation.ui.fragment

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import id.rumahawan.fightcovid19.R
import id.rumahawan.fightcovid19.databinding.FragmentHomeBinding
import id.rumahawan.fightcovid19.navigation.adapter.AdapterMenu
import id.rumahawan.fightcovid19.navigation.bridge.InterfaceHome
import id.rumahawan.fightcovid19.navigation.model.data.MenuItem
import id.rumahawan.fightcovid19.navigation.model.response.ResponseProvince
import id.rumahawan.fightcovid19.navigation.ui.activity.ActivityReference
import id.rumahawan.fightcovid19.navigation.ui.activity.ActivityWebView
import id.rumahawan.fightcovid19.navigation.viewmodel.ViewModelHome
import id.rumahawan.fightcovid19.navigation.viewmodelfactory.ViewModelFactoryHome
import id.rumahawan.fightcovid19.utils.*
import org.kodein.di.generic.instance

class FragmentHome:
    BaseFragment(),
    InterfaceHome,
    OnMapReadyCallback
{

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: ViewModelHome
    private val factory: ViewModelFactoryHome by instance()

    private lateinit var map: GoogleMap
    private lateinit var ctx: Context

    override fun onResume() {
        super.onResume()
        if(::binding.isInitialized) binding.mvMain.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if(::binding.isInitialized) binding.mvMain.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ctx = requireContext()

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        viewModel = ViewModelProvider(this, factory).get(ViewModelHome::class.java)
        viewModel.bridge = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initView(savedInstanceState)

        return binding.root
    }

    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    private fun initView(savedInstanceState: Bundle?){
        val tvHeader = SpannableString("#FightCOVID-19")
        tvHeader.setSpan(ForegroundColorSpan(ContextCompat.getColor(ctx, R.color.red)),
            0, tvHeader.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvHeader.append("Selamat datang di aplikasi ")
        binding.tvHeader.append(tvHeader)
        binding.tvHeader.append(" Indonesia")

        binding.mvMain.onCreate(savedInstanceState)
        binding.mvMain.getMapAsync(this)

        binding.tvTitle.append(
            SpannableString("Rasio COVID-19").also {
                it.setSpan(ForegroundColorSpan(ContextCompat.getColor(ctx, R.color.red)),
                    6, it.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )

        getMenuAdapter()
        getSponsorAdapter()
    }

    private fun getMenuAdapter(): AdapterMenu {
        val adapter: AdapterMenu
        if (binding.rvMenu.adapter != null) {
            adapter = binding.rvMenu.adapter as AdapterMenu
        } else {
            val mLayoutManager: RecyclerView.LayoutManager =
                GridLayoutManager(ctx, 4)
            binding.rvMenu.apply{
                layoutManager = mLayoutManager
                itemAnimator = DefaultItemAnimator()
                adapter = AdapterMenu(Constant.getHomeMenu(), R.layout.item_menu)
                this.adapter = adapter
                adapter.setInterface(object: AdapterMenu.Interface{
                    override fun onClickItemProduk(item: MenuItem?, position: Int) {
                        ctx.apply {
                            if (item?.isActive == true) {
                                when (item.img){
                                    R.drawable.ic_hospital -> launchNewActivity(ActivityReference::class.java)
                                    R.drawable.ic_book -> {
                                        launchNewActivityReturn(ActivityWebView::class.java).apply {
                                            putExtra(Constant.KEY_TITLE, item.name)
                                            putExtra(Constant.KEY_URL, Constant.URL_EDUKASI)
                                            startActivity(this)
                                        }
                                    }
                                    R.drawable.ic_stethoscope -> {
                                        launchNewActivityReturn(ActivityWebView::class.java).apply {
                                            putExtra(Constant.KEY_TITLE, item.name)
                                            putExtra(Constant.KEY_URL, Constant.URL_DIAGNOSA_MANDIRI)
                                            startActivity(this)
                                        }
                                    }
                                    R.drawable.ic_database -> {
                                        launchNewActivityReturn(ActivityWebView::class.java).apply {
                                            putExtra(Constant.KEY_TITLE, item.name)
                                            putExtra(Constant.KEY_URL, Constant.URL_DATA_INTERNASIONAL)
                                            startActivity(this)
                                        }
                                    }
                                    R.drawable.ic_phone -> {
                                        Intent(
                                            Intent.ACTION_DIAL,
                                            Uri.fromParts("tel", "119", null)
                                        ).also { startActivity(it) }
                                    }
                                    R.drawable.img_dtpeduli -> {
                                        launchNewActivityReturn(ActivityWebView::class.java).apply {
                                            putExtra(Constant.KEY_TITLE, item.name)
                                            putExtra(Constant.KEY_URL, Constant.URL_DT_PEDULI_DONASI)
                                            startActivity(this)
                                        }
                                    }
                                    R.drawable.ic_graphic -> {
                                        launchNewActivityReturn(ActivityWebView::class.java).apply {
                                            putExtra(Constant.KEY_TITLE, item.name)
                                            putExtra(Constant.KEY_URL, Constant.URL_GRAFIK)
                                            startActivity(this)
                                        }
                                    }
                                }
                            } else{
                                toast("${item?.name} sedang dalam pengembangan")
                            }
                        }
                    }
                })
            }
        }
        return adapter
    }

    private fun getSponsorAdapter(): AdapterMenu {
        val adapter: AdapterMenu
        if (binding.rvSponsor.adapter != null) {
            adapter = binding.rvSponsor.adapter as AdapterMenu
        } else {
            val mLayoutManager: RecyclerView.LayoutManager =
                GridLayoutManager(ctx, 4)
            binding.rvSponsor.apply{
                layoutManager = mLayoutManager
                itemAnimator = DefaultItemAnimator()
                adapter = AdapterMenu(Constant.getHomeSponsor(), R.layout.item_sponsor)
                this.adapter = adapter
                adapter.setInterface(object: AdapterMenu.Interface{
                    override fun onClickItemProduk(item: MenuItem?, position: Int) {
                        ctx.apply {
                            if (item?.isActive == true) {
                                when (item.img){
                                    R.drawable.img_kemenkes -> {
                                        Intent(Intent.ACTION_VIEW).also {
                                            it.data = Uri.parse(Constant.URL_KEMENKES)
                                            startActivity(it)
                                        }
                                    }
                                    R.drawable.img_bnbp -> {
                                        Intent(Intent.ACTION_VIEW).also {
                                            it.data = Uri.parse(Constant.URL_BNBP)
                                            startActivity(it)
                                        }
                                    }
                                    R.drawable.img_prixa -> {
                                        Intent(Intent.ACTION_VIEW).also {
                                            it.data = Uri.parse(Constant.URL_PRIXA)
                                            startActivity(it)
                                        }
                                    }
                                    R.drawable.img_idcloudhost -> {
                                        Intent(Intent.ACTION_VIEW).also {
                                            it.data = Uri.parse(Constant.URL_ID_CLOUDHOST)
                                            startActivity(it)
                                        }
                                    }
                                    R.drawable.img_dramatelyu -> {
                                        Intent(Intent.ACTION_VIEW).also {
                                            it.data = Uri.parse(Constant.URL_DRAMA_TELYU)
                                            startActivity(it)
                                        }
                                    }
                                }
                            } else{
                                toast("${item?.name} sedang dalam pengembangan")
                            }
                        }
                    }
                })
            }
        }
        return adapter
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0

        try {
            val success = map.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                context, R.raw.map_style))
            if (!success) Log.e("TAG", "Style parsing failed")
        } catch (e: Resources.NotFoundException) {
            Log.e("TAG", "Can't find style. Error: ", e)
        }

        val indonesia = LatLng(-3.630372, 117.93473)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(indonesia, 3.45f))

        viewModel.getMapData()
    }

    override fun onProvincesLoaded(response: ResponseProvince) {
        val size = response.provinces?.size
        if(size != null) {
            val bitRed = ((ContextCompat.getDrawable(context!!,
                R.drawable.img_marker_red)) as BitmapDrawable).bitmap
            val bitGreen = ((ContextCompat.getDrawable(context!!,
                R.drawable.img_marker_green)) as BitmapDrawable).bitmap

            for (i in 0 until size) {
                response.provinces
                val positive = response.provinces?.get(i)?.positive

                val lat = response.provinces?.get(i)?.lat?.toDouble() ?: 0.0
                val lng = response.provinces?.get(i)?.lng?.toDouble() ?: 0.0
                val position = LatLng(lat, lng)
                val province = response.provinces?.get(i)?.province
                val s = response.provinces?.get(i)?.size ?: 0

                val markerIcon = if (positive == 0) {
                    Bitmap.createScaledBitmap(bitGreen, 10, 10, false)
                } else {
                    Bitmap.createScaledBitmap(bitRed, s, s, false)
                }

                map.addMarker(
                    MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromBitmap(markerIcon))
                        .position(position)
                        .title("$province Positif : $positive orang")
                )
            }
        }
    }

    override fun showLoading() {
//        ctx.toast("Loading start")
    }

    override fun hideLoading() {
//        ctx.toast("Loading stop")
    }

    override fun showMessage(message: String?) {
        binding.container.snackbar(message)
    }

    override fun showMessageLong(message: String?) {
        binding.container.snackbarLong(message)
    }

}
