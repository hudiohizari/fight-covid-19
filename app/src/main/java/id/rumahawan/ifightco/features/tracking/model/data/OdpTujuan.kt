package id.rumahawan.ifightco.features.tracking.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class OdpTujuan(
    var value: Int = 0,
    var deskripsi: String = ""
) : Parcelable{

    override fun toString(): String {
        return "TujuanODP(value=$value, deskripsi='$deskripsi')"
    }

    companion object{
        fun empty(): OdpTujuan {
            return OdpTujuan(0, "")
        }
    }
}