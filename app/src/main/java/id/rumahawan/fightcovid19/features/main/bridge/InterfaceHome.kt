package id.rumahawan.fightcovid19.features.main.bridge

import id.rumahawan.fightcovid19.features.main.model.response.ResponseProvince
import id.rumahawan.fightcovid19.utils.BaseInterfaceRemoteViewModel

interface InterfaceHome: BaseInterfaceRemoteViewModel {
    fun onProvincesLoaded(response: ResponseProvince)
}