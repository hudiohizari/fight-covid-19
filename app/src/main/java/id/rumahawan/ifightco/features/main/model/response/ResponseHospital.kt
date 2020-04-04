package id.rumahawan.ifightco.features.main.model.response

import com.google.gson.annotations.SerializedName
import id.rumahawan.ifightco.features.main.model.data.Hospital

data class ResponseHospital (
    @SerializedName("hospital")
    var hospitals: MutableList<Hospital>? = mutableListOf()
)

