package id.rumahawan.ifightco.features.main.bridge

import id.rumahawan.ifightco.features.main.adapter.AdapterInbox
import id.rumahawan.ifightco.utils.BaseInterfaceRemoteViewModel

interface InterfaceInbox: BaseInterfaceRemoteViewModel{
    fun getInboxAdapter(): AdapterInbox
}