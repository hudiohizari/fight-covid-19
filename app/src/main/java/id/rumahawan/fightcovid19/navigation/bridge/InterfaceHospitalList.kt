package id.rumahawan.fightcovid19.navigation.bridge

import id.rumahawan.fightcovid19.navigation.adapter.AdapterHospital

interface InterfaceHospitalList{
    fun onBackButton()
    fun getHospitalAdapter(): AdapterHospital
}