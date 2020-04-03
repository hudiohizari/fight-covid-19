package id.rumahawan.fightcovid19.features.tracking.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import id.rumahawan.fightcovid19.R
import id.rumahawan.fightcovid19.databinding.ActivityRegisterOneBinding
import id.rumahawan.fightcovid19.features.tracking.bridge.InterfaceRegisterOne
import id.rumahawan.fightcovid19.features.tracking.viewmodel.ViewModelRegisterOne
import id.rumahawan.fightcovid19.utils.BaseActivity

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
