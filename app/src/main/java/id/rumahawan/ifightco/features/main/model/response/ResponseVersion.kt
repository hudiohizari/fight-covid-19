package id.rumahawan.ifightco.features.main.model.response

import com.google.gson.annotations.SerializedName

data class ResponseVersion(
    @SerializedName("app_version")
    var appVersion: Double? = 0.0
)