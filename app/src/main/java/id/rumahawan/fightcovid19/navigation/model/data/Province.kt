package id.rumahawan.fightcovid19.navigation.model.data

import com.google.gson.annotations.SerializedName

data class Province(
    var positive: Int? = 0,
    var lat: String? = "",
    var lng: String? = "",
    var province: String? = "",
    @SerializedName("province_code")
    var provinceCode: String? = "",
    var size: Int? = 0
){
    override fun toString(): String {
        return "Province(positive=$positive, lat=$lat, lng=$lng, province=$province, provinceCode=$provinceCode, size=$size)"
    }
}