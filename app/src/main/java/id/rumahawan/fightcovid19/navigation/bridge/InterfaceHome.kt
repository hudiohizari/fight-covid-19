package id.rumahawan.fightcovid19.navigation.bridge

import id.rumahawan.fightcovid19.navigation.model.response.ResponseProvince
import id.rumahawan.fightcovid19.utils.BaseInterfaceRemoteViewModel

interface InterfaceHome: BaseInterfaceRemoteViewModel {
    fun onProvincesLoaded(response: ResponseProvince)
}