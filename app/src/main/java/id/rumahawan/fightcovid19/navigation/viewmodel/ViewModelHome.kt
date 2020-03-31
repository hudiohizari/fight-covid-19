package id.rumahawan.fightcovid19.navigation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rumahawan.fightcovid19.navigation.bridge.InterfaceHome
import id.rumahawan.fightcovid19.navigation.repository.RepositoryHome
import id.rumahawan.fightcovid19.utils.ApiException
import id.rumahawan.fightcovid19.utils.Coroutines
import id.rumahawan.fightcovid19.utils.NoInternetException

class ViewModelHome(
    private val repository: RepositoryHome
): ViewModel() {

    var bridge: InterfaceHome? = null

    val positive = MutableLiveData<String>().apply { value = "" }
    val recovered = MutableLiveData<String>().apply { value = "" }
    val dead = MutableLiveData<String>().apply { value = "" }
    val lastUpdated = MutableLiveData<String>().apply { value = "" }
    val version = MutableLiveData<String>().apply { value = "" }

    init {
        getAllData()
    }

    private fun getAllData() {
        bridge?.showLoading()
        Coroutines.main{
            try {
                repository.getRatio().also {
                    positive.value = it.confirmed.toString()
                    recovered.value = it.recovered.toString()
                    dead.value = it.deaths.toString()
                    lastUpdated.value = "Last updated: ${it.lastUpdated}"
                }
                repository.getVersion().also {
                    version.value = "App Version ${it.appVersion}"
                }
            } catch (e: ApiException) {
                bridge?.showMessage(e.message)
            } catch (e: NoInternetException) {
                bridge?.showMessageLong(e.message)
            }
            bridge?.hideLoading()
        }
    }

}
