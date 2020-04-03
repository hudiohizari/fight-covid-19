package id.rumahawan.fightcovid19.features.tracking.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rumahawan.fightcovid19.features.tracking.bridge.InterfaceRegisterOne
import id.rumahawan.fightcovid19.features.tracking.model.RequestODP
import id.rumahawan.fightcovid19.utils.toast

class ViewModelRegisterOne: ViewModel() {

    var bridge: InterfaceRegisterOne? = null

    private var lastButtonEnabled = false

    val title = MutableLiveData<String>().apply { value = "" }
    val name = MutableLiveData<String>().apply { value = "" }
    val age = MutableLiveData<String>().apply { value = "" }
    val phone = MutableLiveData<String>().apply { value = "" }
    val id = MutableLiveData<String>().apply { value = "" }
    val email = MutableLiveData<String>().apply { value = "" }
    val emailError = MutableLiveData<String>().apply { value = null }
    val address = MutableLiveData<String>().apply { value = "" }
    val buttonEnabled = MutableLiveData<Boolean>().apply { value = false }

    val onNavigationBackClick = View.OnClickListener { bridge?.onBackButton() }

    init {
        name.observeForever { checkButton() }
        age.observeForever { checkButton() }
        phone.observeForever { checkButton() }
        id.observeForever { checkButton() }
        email.observeForever { checkButton() }
        address.observeForever { checkButton() }
    }

    private fun checkButton(){
        val enabled = !name.value.isNullOrEmpty() && !age.value.isNullOrEmpty() &&
                !phone.value.isNullOrEmpty() && !id.value.isNullOrEmpty() &&
                (!email.value.isNullOrEmpty() && checkEmail()) &&
                !address.value.isNullOrEmpty()
        if (lastButtonEnabled != enabled){
            buttonEnabled.value = enabled
            lastButtonEnabled = enabled
        }
    }

    private fun checkEmail(): Boolean{
        return if(android.util.Patterns.EMAIL_ADDRESS.matcher(email.value ?: "").matches()){
            if(!emailError.value.isNullOrEmpty()) emailError.value = null
            true
        } else {
            if(emailError.value.isNullOrEmpty()) emailError.value = "Format email belum benar"
            false
        }
    }

    fun onNextPressed(view: View){val requestODP = RequestODP()
        requestODP.odpData.nama = name.value ?: ""
        requestODP.odpData.usia = (age.value ?: "0").toInt()
        requestODP.odpData.noHp = phone.value ?: ""
        requestODP.odpData.noKtp = id.value ?: ""
        requestODP.odpData.email = email.value ?: ""
        requestODP.odpData.alamat = address.value ?: ""
        Log.d("Request Odp Data", requestODP.toString())
        view.context.toast("Next page not ready yet")
//        view.context.launchNewActivityReturn(ActivityRegisterTwo::class.java).apply {
//            putExtra("requestOdp", requestODP)
//            view.context.startActivity(this)
//        }
    }

}
