package id.rumahawan.ifightco.features.main.repository

import id.rumahawan.ifightco.features.main.model.response.ResponseProvince
import id.rumahawan.ifightco.features.main.model.response.ResponseRatio
import id.rumahawan.ifightco.features.main.model.response.ResponseVersion
import id.rumahawan.ifightco.repomanager.remote.RemoteRequestManager
import id.rumahawan.ifightco.repomanager.remote.SafeApiRequest


class RepositoryHome(
    private val remoteRequestManager: RemoteRequestManager
): SafeApiRequest() {

    suspend fun getVersion(): ResponseVersion {
        return apiRequest { remoteRequestManager.getVersion() }
    }

    suspend fun getRatio(): ResponseRatio {
        return apiRequest { remoteRequestManager.getRatio() }
    }

    suspend fun getProvinces(): ResponseProvince {
        return apiRequest { remoteRequestManager.getProvinces() }
    }

}