package id.rumahawan.ifightco.utils

interface BaseInterfaceRemoteViewModel {
    fun showLoading()
    fun hideLoading()
    fun showMessage(message: String?)
    fun showMessageLong(message: String?)
}