package id.rumahawan.ifightco.features.tracking.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import id.rumahawan.ifightco.R
import id.rumahawan.ifightco.databinding.ActivityRegisterOneBinding
import id.rumahawan.ifightco.features.tracking.bridge.InterfaceRegisterOne
import id.rumahawan.ifightco.features.tracking.viewmodel.ViewModelRegisterOne
import id.rumahawan.ifightco.utils.BaseActivity

class ActivityRegisterOne:
    BaseActivity(),
    InterfaceRegisterOne
{

    private lateinit var binding: ActivityRegisterOneBinding
    private lateinit var viewModel: ViewModelRegisterOne

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_one)
        viewModel = ViewModelProvider(this).get(ViewModelRegisterOne::class.java)
        viewModel.bridge = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initView()
    }

    private fun initView(){
        viewModel.title.value = resources.getString(R.string.label_data_pasien)
    }

    override fun onBackButton() {
        onBackPressed()
    }
}
