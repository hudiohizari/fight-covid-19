package id.rumahawan.ifightco.features.main.bridge

import id.rumahawan.ifightco.features.main.adapter.AdapterHospital

interface InterfaceHospitalList{
    fun onBackButton()
    fun getHospitalAdapter(): AdapterHospital
}