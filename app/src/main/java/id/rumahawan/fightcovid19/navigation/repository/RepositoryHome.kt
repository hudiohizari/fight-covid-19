package id.rumahawan.fightcovid19.navigation.repository

import id.rumahawan.fightcovid19.navigation.model.response.ResponseProvince
import id.rumahawan.fightcovid19.navigation.model.response.ResponseRatio
import id.rumahawan.fightcovid19.navigation.model.response.ResponseVersion
import id.rumahawan.fightcovid19.repomanager.local.LocalRequestManager
import id.rumahawan.fightcovid19.repomanager.remote.RemoteRequestManager
import id.rumahawan.fightcovid19.repomanager.remote.SafeApiRequest


class RepositoryHome(
    private val localRequestManager: LocalRequestManager,
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