package id.rumahawan.ifightco.features.tracking.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rumahawan.ifightco.features.tracking.bridge.InterfaceTrackOdp
import id.rumahawan.ifightco.features.tracking.ui.ActivityRegisterOne
import id.rumahawan.ifightco.utils.launchNewActivity
import id.rumahawan.ifightco.utils.toast

class ViewModelTrackOdp: ViewModel() {

    var bridge: InterfaceTrackOdp? = null

    val title = MutableLiveData<String>().apply { value = "" }

    val onNavigationBackClick = View.OnClickListener { bridge?.onBackButton() }

    fun onLoginPressed(view: View){
        view.context.toast("Masuk belum bisa ya")
    }

    fun onRegisterPressed(view: View){
        view.context.launchNewActivity(ActivityRegisterOne::class.java)
    }

}
