package id.rumahawan.ifightco.features.main.model.response

import com.google.gson.annotations.SerializedName

data class ResponseRatio(
    var confirmed: Int? = 0,
    var recovered: Int? = 0,
    var deaths: Int? = 0,
    @SerializedName("lastupdated")
    var lastUpdated: String? = ""

)