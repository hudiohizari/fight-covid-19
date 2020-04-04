package id.rumahawan.ifightco.features.main.bridge

import id.rumahawan.ifightco.features.main.model.response.ResponseHospital
import id.rumahawan.ifightco.utils.BaseInterfaceRemoteViewModel

interface InterfaceRujukan: BaseInterfaceRemoteViewModel{
    fun onBackButton()
    fun getLocation(isMoveToMarker: Boolean)
    fun setListProvinceInput(list: List<String>)
    fun onHospitalsSucceed(response: ResponseHospital)
    fun launchHospitalList()
}