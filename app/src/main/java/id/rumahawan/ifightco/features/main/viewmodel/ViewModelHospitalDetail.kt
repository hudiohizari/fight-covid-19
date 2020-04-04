package id.rumahawan.ifightco.features.main.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rumahawan.ifightco.features.main.bridge.InterfaceHospitalDetail

class ViewModelHospitalDetail: ViewModel() {

    var bridge: InterfaceHospitalDetail? = null

    val name = MutableLiveData<String>().apply { value = "" }
    val address = MutableLiveData<String>().apply { value = "" }
    val distance = MutableLiveData<String>().apply { value = "" }
    val phone = MutableLiveData<String>().apply { value = "" }

    @Suppress("UNUSED_PARAMETER")
    fun onClosePressed(view: View){
        bridge?.onClosePressed()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onNavigationPressed(view: View){
        bridge?.launchNavigation()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onPhonePressed(view: View){
        bridge?.launchPhone()
    }

}
