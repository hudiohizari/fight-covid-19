package id.rumahawan.fightcovid19.navigation.model.response

import com.google.gson.annotations.SerializedName

data class ResponseVersion(
    @SerializedName("app_version")
    var appVersion: Double? = 0.0
)