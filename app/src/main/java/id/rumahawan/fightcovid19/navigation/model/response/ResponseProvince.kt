package id.rumahawan.fightcovid19.navigation.model.response

import com.google.gson.annotations.SerializedName
import id.rumahawan.fightcovid19.navigation.model.data.Province

data class ResponseProvince (
    @SerializedName("province")
    var provinces: MutableList<Province>? = mutableListOf()
)

