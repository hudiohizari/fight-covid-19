package id.rumahawan.fightcovid19.navigation.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Hospital(
    var name: String? = "",
    var lat: String? = "",
    var lng: String? = "",
    var telephone: String? = "",
    var address: String? = "",
    var distance: Double? = 0.0
) : Parcelable {
    companion object{
        fun empty(): Hospital{
            return Hospital("", "", "", "", "", 0.0)
        }
    }
}