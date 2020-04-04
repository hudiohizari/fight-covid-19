package id.rumahawan.ifightco.features.main.model.response

import com.google.gson.annotations.SerializedName
import id.rumahawan.ifightco.features.main.model.data.Inbox

data class ResponseInbox (
    @SerializedName("results")
    var inbox: MutableList<Inbox>? = mutableListOf()
)

