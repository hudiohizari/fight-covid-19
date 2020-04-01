package id.rumahawan.fightcovid19.navigation.repository

import id.rumahawan.fightcovid19.navigation.model.response.ResponseHospital
import id.rumahawan.fightcovid19.navigation.model.response.ResponseProvince
import id.rumahawan.fightcovid19.repomanager.remote.RemoteRequestManager
import id.rumahawan.fightcovid19.repomanager.remote.SafeApiRequest

class RepositoryReference(
    private val remoteRequestManager: RemoteRequestManager
): SafeApiRequest() {

    suspend fun getProvinces(): ResponseProvince {
        return apiRequest { remoteRequestManager.getProvinces() }
    }

    suspend fun getHospitals(provinceId: String, lat: Double, lng: Double): ResponseHospital {
        return apiRequest { remoteRequestManager.getHospitals(provinceId, lat, lng) }
    }

}