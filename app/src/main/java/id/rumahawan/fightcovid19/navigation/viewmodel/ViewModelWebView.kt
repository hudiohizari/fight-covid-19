package id.rumahawan.fightcovid19.navigation.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rumahawan.fightcovid19.navigation.bridge.InterfaceWebView

class ViewModelWebView: ViewModel() {

    var bridge: InterfaceWebView? = null

    val title = MutableLiveData<String>().apply { value = "" }

    val onNavigationBackClick = View.OnClickListener { bridge?.onBackButton() }

}
