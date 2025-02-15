package id.rumahawan.ifightco.features.tracking.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.rumahawan.ifightco.R
import id.rumahawan.ifightco.databinding.ActivityTrackOdpBinding
import id.rumahawan.ifightco.features.main.adapter.AdapterMenu
import id.rumahawan.ifightco.features.tracking.bridge.InterfaceTrackOdp
import id.rumahawan.ifightco.features.main.model.data.MenuItem
import id.rumahawan.ifightco.features.main.ui.activity.ActivityReference
import id.rumahawan.ifightco.features.main.ui.activity.ActivityWebView
import id.rumahawan.ifightco.features.tracking.viewmodel.ViewModelTrackOdp
import id.rumahawan.ifightco.utils.*

class ActivityTrackOdp:
    BaseActivity(),
    InterfaceTrackOdp
{

    private lateinit var binding: ActivityTrackOdpBinding
    private lateinit var viewModel: ViewModelTrackOdp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_track_odp)
        viewModel = ViewModelProvider(this).get(ViewModelTrackOdp::class.java)
        viewModel.bridge = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initView()
    }

    private fun initView(){
        viewModel.title.value = resources.getString(R.string.label_llt)
        getMenuAdapter()
    }

    private fun getMenuAdapter(): AdapterMenu {
        val adapter: AdapterMenu
        if (binding.rvMenu.adapter != null) {
            adapter = binding.rvMenu.adapter as AdapterMenu
        } else {
            val mLayoutManager: RecyclerView.LayoutManager =
                GridLayoutManager(this, 4)
            binding.rvMenu.apply{
                layoutManager = mLayoutManager
                itemAnimator = DefaultItemAnimator()
                adapter = AdapterMenu(Constant.getMenuOdp(), R.layout.item_menu)
                this.adapter = adapter
                adapter.setInterface(menuInterface)
            }
        }
        return adapter
    }

    private val menuInterface = object: AdapterMenu.Interface{
        override fun onClickItemProduk(item: MenuItem?, position: Int) {
            apply {
                if (item?.isActive == true) {
                    when (item.img){
                        R.drawable.ic_hospital ->
                            launchNewActivity(ActivityReference::class.java)
                        R.drawable.ic_book -> {
                            launchNewActivityReturn(ActivityWebView::class.java).apply {
                                putExtra(Constant.KEY_TITLE, item.name)
                                putExtra(Constant.KEY_URL, Constant.URL_EDUKASI)
                                startActivity(this)
                            }
                        }
                    }
                } else{
                    toast("${item?.name} sedang dalam pengembangan")
                }
            }
        }
    }

    override fun onBackButton() {
        finish()
    }
}
