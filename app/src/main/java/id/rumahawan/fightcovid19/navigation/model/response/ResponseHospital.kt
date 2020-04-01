package id.rumahawan.fightcovid19.navigation.model.response

import com.google.gson.annotations.SerializedName
import id.rumahawan.fightcovid19.navigation.model.data.Hospital

data class ResponseHospital (
    @SerializedName("hospital")
    var hospitals: MutableList<Hospital>? = mutableListOf()
)

