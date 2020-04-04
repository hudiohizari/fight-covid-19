package id.rumahawan.ifightco.features.main.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rumahawan.ifightco.features.main.bridge.InterfaceHospitalList
import id.rumahawan.ifightco.features.main.model.data.Hospital

class ViewModelHospitalList: ViewModel() {

    var bridge: InterfaceHospitalList? = null

    val hospitals = MutableLiveData<MutableList<Hospital>>()
        .apply {
            value = mutableListOf()
        }
    val title = MutableLiveData<String>().apply { value = "" }

    val onNavigationBackClick = View.OnClickListener { bridge?.onBackButton() }

    init {
        hospitals.observeForever{
            bridge?.getHospitalAdapter()?.notifyDataSetChanged()
        }
    }

}
