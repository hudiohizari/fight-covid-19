package id.rumahawan.fightcovid19.navigation.bridge

import id.rumahawan.fightcovid19.navigation.model.response.ResponseHospital
import id.rumahawan.fightcovid19.utils.BaseInterfaceRemoteViewModel

interface InterfaceRujukan: BaseInterfaceRemoteViewModel{
    fun onBackButton()
    fun getLocation()
    fun setListProvinceInput(list: List<String>)
    fun onHospitalsSucceed(response: ResponseHospital)
}