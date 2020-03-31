package id.rumahawan.fightcovid19.navigation.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rumahawan.fightcovid19.navigation.bridge.InterfaceHome
import id.rumahawan.fightcovid19.navigation.bridge.InterfaceWebView
import id.rumahawan.fightcovid19.navigation.repository.RepositoryHome
import id.rumahawan.fightcovid19.utils.ApiException
import id.rumahawan.fightcovid19.utils.Coroutines
import id.rumahawan.fightcovid19.utils.NoInternetException

class ViewModelWebView: ViewModel() {

    var bridge: InterfaceWebView? = null

    val title = MutableLiveData<String>().apply { value = "" }

    val onNavigationBackClick = View.OnClickListener { bridge?.onBackButton() }

}
