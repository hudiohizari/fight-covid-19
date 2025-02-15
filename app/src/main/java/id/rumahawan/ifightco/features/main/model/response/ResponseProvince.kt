package id.rumahawan.ifightco.features.main.model.response

import com.google.gson.annotations.SerializedName
import id.rumahawan.ifightco.features.main.model.data.Province

data class ResponseProvince (
    @SerializedName("province")
    var provinces: MutableList<Province>? = mutableListOf()
)

