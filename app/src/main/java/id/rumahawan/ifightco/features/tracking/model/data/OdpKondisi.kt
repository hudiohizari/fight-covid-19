package id.rumahawan.ifightco.features.tracking.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class OdpKondisi(
    var value: Boolean = false,
    var deskripsi: String = ""
) : Parcelable{

    override fun toString(): String {
        return "KondisiODP(value=$value, deskripsi='$deskripsi')"
    }

    companion object{
        fun empty(): OdpKondisi {
            return OdpKondisi(false, "")
        }
    }
}