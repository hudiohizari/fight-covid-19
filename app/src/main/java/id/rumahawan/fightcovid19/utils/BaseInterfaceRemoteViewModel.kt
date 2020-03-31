package id.rumahawan.fightcovid19.utils

interface BaseInterfaceRemoteViewModel {
    fun showLoading()
    fun hideLoading()
    fun showMessage(message: String?)
    fun showMessageLong(message: String?)
}