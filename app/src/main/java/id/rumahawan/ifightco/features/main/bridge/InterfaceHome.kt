package id.rumahawan.ifightco.features.main.bridge

import id.rumahawan.ifightco.features.main.model.response.ResponseProvince
import id.rumahawan.ifightco.utils.BaseInterfaceRemoteViewModel

interface InterfaceHome: BaseInterfaceRemoteViewModel {
    fun onProvincesLoaded(response: ResponseProvince)
}