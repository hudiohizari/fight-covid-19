package id.rumahawan.fightcovid19.features.tracking.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rumahawan.fightcovid19.features.tracking.bridge.InterfaceTrackOdp
import id.rumahawan.fightcovid19.features.tracking.ui.ActivityRegisterOne
import id.rumahawan.fightcovid19.utils.launchNewActivity
import id.rumahawan.fightcovid19.utils.toast

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
