package id.rumahawan.ifightco.features.main.repository

import id.rumahawan.ifightco.features.main.model.response.ResponseInbox
import id.rumahawan.ifightco.repomanager.remote.RemoteRequestManager
import id.rumahawan.ifightco.repomanager.remote.SafeApiRequest

class RepositoryInbox(
    private val remoteRequestManager: RemoteRequestManager
): SafeApiRequest() {

    suspend fun getInbox(): ResponseInbox {
        return apiRequest { remoteRequestManager.getInbox() }
    }

}