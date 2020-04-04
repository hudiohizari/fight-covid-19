package id.rumahawan.ifightco.features.main.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.rumahawan.ifightco.R
import id.rumahawan.ifightco.databinding.ActivityHospitalListBinding
import id.rumahawan.ifightco.features.main.adapter.AdapterHospital
import id.rumahawan.ifightco.features.main.bridge.InterfaceHospitalList
import id.rumahawan.ifightco.features.main.model.data.Hospital
import id.rumahawan.ifightco.features.main.ui.bottomsheet.BottomSheetHospitalDetail
import id.rumahawan.ifightco.features.main.viewmodel.ViewModelHospitalList
import id.rumahawan.ifightco.utils.BaseActivity
import id.rumahawan.ifightco.utils.Constant

class ActivityHospitalList:
    BaseActivity(),
    InterfaceHospitalList
{

    private lateinit var binding: ActivityHospitalListBinding
    private lateinit var viewModel: ViewModelHospitalList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_hospital_list)
        viewModel = ViewModelProvider(this).get(ViewModelHospitalList::class.java)
        viewModel.bridge = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initView()
    }

    private fun initView(){
        viewModel.title.value = intent.getStringExtra(Constant.KEY_TITLE)
        viewModel.hospitals.value = intent.getParcelableArrayListExtra(Constant.KEY_HOSPITAL_LIST)
    }

    override fun onBackButton() {
        finish()
    }

    override fun getHospitalAdapter(): AdapterHospital {
        val adapter: AdapterHospital
        if (binding.rvMain.adapter != null) {
            adapter = binding.rvMain.adapter as AdapterHospital
        } else {
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false)
            binding.rvMain.apply{
                this.layoutManager = layoutManager
                itemAnimator = DefaultItemAnimator()
                adapter = AdapterHospital(viewModel.hospitals.value ?: mutableListOf())
                adapter.setInterface(object: AdapterHospital.Interface{
                    override fun onClickItem(item: Hospital, position: Int) {
                        val dialog = BottomSheetHospitalDetail(item)
                        dialog.show(supportFragmentManager, dialog.tag)
                    }
                })
                this.adapter = adapter
            }
        }
        return adapter
    }
}
