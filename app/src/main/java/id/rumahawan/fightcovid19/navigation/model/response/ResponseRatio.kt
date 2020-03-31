package id.rumahawan.fightcovid19.navigation.model.response

import com.google.gson.annotations.SerializedName

data class ResponseRatio(
    var confirmed: Int? = 0,
    var recovered: Int? = 0,
    var deaths: Int? = 0,
    @SerializedName("lastupdated")
    var lastUpdated: String? = ""

)