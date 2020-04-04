package id.rumahawan.ifightco.features.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rumahawan.ifightco.features.main.bridge.InterfaceHome
import id.rumahawan.ifightco.features.main.repository.RepositoryHome
import id.rumahawan.ifightco.utils.ApiException
import id.rumahawan.ifightco.utils.Coroutines
import id.rumahawan.ifightco.utils.NoInternetException

class ViewModelHome(
    private val repository: RepositoryHome
): ViewModel() {

    var bridge: InterfaceHome? = null

    val positive = MutableLiveData<String>().apply { value = "0" }
    val recovered = MutableLiveData<String>().apply { value = "0" }
    val dead = MutableLiveData<String>().apply { value = "0" }
    val lastUpdated = MutableLiveData<String>().apply { value = "-" }
    val version = MutableLiveData<String>().apply { value = "-" }

    init {
        getRatio()
    }

    private fun getRatio() {
        bridge?.showLoading()
        Coroutines.main{
            try {
                repository.getRatio().also {
                    positive.value = it.confirmed.toString()
                    recovered.value = it.recovered.toString()
                    dead.value = it.deaths.toString()
                    lastUpdated.value = "Terakhir dipebarui: ${it.lastUpdated}"
                }
            } catch (e: ApiException) {
                bridge?.showMessage(e.message)
            } catch (e: NoInternetException) {
                bridge?.showMessageLong(e.message)
            }
            bridge?.hideLoading()
        }
    }

    fun getMapData() {
        bridge?.showLoading()
        Coroutines.main{
            try {
                bridge?.onProvincesLoaded(repository.getProvinces())
            } catch (e: ApiException) {
                bridge?.showMessage(e.message)
            } catch (e: NoInternetException) {
                bridge?.showMessageLong(e.message)
            }
            bridge?.hideLoading()
        }
    }

}
