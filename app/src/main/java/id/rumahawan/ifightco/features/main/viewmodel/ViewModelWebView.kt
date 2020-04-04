package id.rumahawan.ifightco.features.main.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rumahawan.ifightco.features.main.bridge.InterfaceWebView

class ViewModelWebView: ViewModel() {

    var bridge: InterfaceWebView? = null

    val title = MutableLiveData<String>().apply { value = "" }

    val onNavigationBackClick = View.OnClickListener { bridge?.onBackButton() }

}
