package id.rumahawan.fightcovid19.features.main.bridge

import id.rumahawan.fightcovid19.features.main.adapter.AdapterHospital

interface InterfaceHospitalList{
    fun onBackButton()
    fun getHospitalAdapter(): AdapterHospital
}