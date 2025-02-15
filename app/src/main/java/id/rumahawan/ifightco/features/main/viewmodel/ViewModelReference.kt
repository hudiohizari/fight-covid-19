package id.rumahawan.ifightco.features.main.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rumahawan.ifightco.features.main.bridge.InterfaceRujukan
import id.rumahawan.ifightco.features.main.model.data.Hospital
import id.rumahawan.ifightco.features.main.model.data.Province
import id.rumahawan.ifightco.features.main.repository.RepositoryReference
import id.rumahawan.ifightco.utils.ApiException
import id.rumahawan.ifightco.utils.Coroutines
import id.rumahawan.ifightco.utils.NoInternetException

class ViewModelReference(
    private val repository: RepositoryReference
): ViewModel(){

    var bridge: InterfaceRujukan? = null

    private val provinces = MutableLiveData<MutableList<Province>>()
        .apply {
            value = mutableListOf()
        }
    private val hospitals = MutableLiveData<MutableList<Hospital>>()
        .apply {
            value = mutableListOf()
        }

    val onNavigationBackClick = View.OnClickListener { bridge?.onBackButton() }

    val seeListVisibility = MutableLiveData<Int>()
        .apply { value = View.GONE }
    val title = MutableLiveData<String>().apply { value = "" }
    val address = MutableLiveData<String>().apply { value = "" }

    init {
        provinces.observeForever{
            val newList = mutableListOf<String>()
            for(province in it){
                newList.add(province.province ?: "")
            }
            bridge?.setListProvinceInput(newList)
        }

        getProvinces()
    }

    private fun getProvinces(){
        bridge?.showLoading()
        Coroutines.main{
            try {
                repository.getProvinces().also { provinces.value = it.provinces }
            } catch (e: ApiException) {
                bridge?.showMessage(e.message)
            } catch (e: NoInternetException) {
                bridge?.showMessageLong(e.message)
            }
            bridge?.hideLoading()
        }
    }

    fun getProvinceList(): MutableList<Province>{
        return provinces.value ?: mutableListOf()
    }

    fun getHospitalList(provinceId: String, lat: Double, lng: Double){
        bridge?.showLoading()
        Coroutines.main{
            try {
                val hospitalList = repository.getHospitals(provinceId, lat, lng)
                bridge?.getLocation(false)
                bridge?.onHospitalsSucceed(hospitalList)
                hospitals.value = hospitalList.hospitals
            } catch (e: ApiException) {
                bridge?.showMessage(e.message)
            } catch (e: NoInternetException) {
                bridge?.showMessageLong(e.message)
            }
            bridge?.hideLoading()
        }
    }

    fun getHospitalList(): MutableList<Hospital>{
        return hospitals.value ?: mutableListOf()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onSeeAllPressed(view: View){
        bridge?.launchHospitalList()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onRefreshPressed(view: View){
        bridge?.getLocation(true)
    }
}