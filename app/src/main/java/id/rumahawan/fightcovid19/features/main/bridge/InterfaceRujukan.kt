package id.rumahawan.fightcovid19.features.main.bridge

import id.rumahawan.fightcovid19.features.main.model.response.ResponseHospital
import id.rumahawan.fightcovid19.utils.BaseInterfaceRemoteViewModel

interface InterfaceRujukan: BaseInterfaceRemoteViewModel{
    fun onBackButton()
    fun getLocation(isMoveToMarker: Boolean)
    fun setListProvinceInput(list: List<String>)
    fun onHospitalsSucceed(response: ResponseHospital)
    fun launchHospitalList()
}