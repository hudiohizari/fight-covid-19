package id.rumahawan.fightcovid19.navigation.ui.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import id.rumahawan.fightcovid19.R
import id.rumahawan.fightcovid19.databinding.FragmentHomeBinding
import id.rumahawan.fightcovid19.navigation.adapter.AdapterMenu
import id.rumahawan.fightcovid19.navigation.bridge.InterfaceHome
import id.rumahawan.fightcovid19.navigation.model.data.MenuItem
import id.rumahawan.fightcovid19.navigation.ui.activity.ActivitySponsor
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

    private lateinit var ctx: Context

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if(::binding.isInitialized) binding.mvMain.onSaveInstanceState(outState)
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

    private fun initView(savedInstanceState: Bundle?){
        val tvHeader = SpannableString("Welcome to #FightCOVID-19 App Indonesia")
        tvHeader.setSpan(ForegroundColorSpan(ContextCompat.getColor(ctx, R.color.red)),
            11, 25, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvHeader.append(tvHeader)

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
                                        launchNewActivityReturn(ActivitySponsor::class.java).apply {
                                            putExtra(Constant.KEY_TITLE, item.name)
                                            putExtra(Constant.KEY_URL, Constant.URL_KEMENKES)
                                            startActivity(this)
                                        }
                                    }
                                    R.drawable.img_bnbp -> {
                                        launchNewActivityReturn(ActivitySponsor::class.java).apply {
                                            putExtra(Constant.KEY_TITLE, item.name)
                                            putExtra(Constant.KEY_URL, Constant.URL_BNBP)
                                            startActivity(this)
                                        }
                                    }
                                    R.drawable.img_prixa -> {
                                        launchNewActivityReturn(ActivitySponsor::class.java).apply {
                                            putExtra(Constant.KEY_TITLE, item.name)
                                            putExtra(Constant.KEY_URL, Constant.URL_PRIXA)
                                            startActivity(this)
                                        }
                                    }
                                    R.drawable.img_idcloudhost -> {
                                        launchNewActivityReturn(ActivitySponsor::class.java).apply {
                                            putExtra(Constant.KEY_TITLE, item.name)
                                            putExtra(Constant.KEY_URL, Constant.URL_ID_CLOUDHOST)
                                            startActivity(this)
                                        }
                                    }
                                    R.drawable.img_dramatelyu -> {
                                        launchNewActivityReturn(ActivitySponsor::class.java).apply {
                                            putExtra(Constant.KEY_TITLE, item.name)
                                            putExtra(Constant.KEY_URL, Constant.URL_DRAMA_TELYU)
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

    override fun onMapReady(p0: GoogleMap) {
//        try {
//            val success = p0.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.mapstyle))
//            if (!success){
//
//                Log.e("TAG", "Style parsing failed")
//            }
//        }catch (e: Resources.NotFoundException) {
//            Log.e("TAG", "Can't find style. Error: ", e)
//        }
//
//
//
//        val bitmapDraw =
//            ContextCompat.getDrawable(ctx, R.drawable.circle_red_translucent) as BitmapDrawable
//        val b = bitmapDraw.bitmap
//
//        val bitmapDrawSafe =
//            ContextCompat.getDrawable(ctx, R.drawable.circle_green_translucent) as BitmapDrawable
//        val bSafe = bitmapDrawSafe.bitmap
//
//        val args = baseGetInstance<ResponseDataProvince?>("provinces")
//
//        var x = 0
//        val size = args?.province?.size
//        if(size != null) {
//            while (x < size!!) {
//                val positive = args.province.get(x).positive
//
//                val lat = args.province.get(x).lat?.toDouble()
//                val lng = args.province.get(x).lng?.toDouble()
//                val position = LatLng(lat!!, lng!!)
//                val province = args.province.get(x).province
//                var size = args.province[x].size
//
//                if (positive == 0) {
//                    val scaleMarker = Bitmap.createScaledBitmap(bSafe, 10, 10, false)
//                    p0.addMarker(
//                        MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(scaleMarker)).position(
//                            position
//                        ).title(province + " Positif : " + positive + " orang")
//                    )
//                } else {
//
//                    val scaleMarker = Bitmap.createScaledBitmap(b, size, size, false)
//                    p0.addMarker(
//                        MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(scaleMarker)).position(
//                            position
//                        ).title(province + " Positif : " + positive + " orang")
//                    )
//                }
//                x++
//            }
//        }
//
//
//        val indonesia = LatLng(-3.630372, 117.93473)
//        p0.moveCamera(CameraUpdateFactory.newLatLngZoom(indonesia, 3.45f))
    }

    override fun showLoading() {
        ctx.toast("Loading start")
    }

    override fun hideLoading() {
        ctx.toast("Loading stop")
    }

    override fun showMessage(message: String?) {
        binding.container.snackbar(message)
    }

    override fun showMessageLong(message: String?) {
        binding.container.snackbarLong(message)
    }

}
