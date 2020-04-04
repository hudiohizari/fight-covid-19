package id.rumahawan.ifightco.features.tracking.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class OdpDaerahTerjangkit(
    var value: Boolean = false,
    var deskripsi: String = ""
) : Parcelable{

    override fun toString(): String {
        return "DaerahTerjangkit(value=$value, deskripsi='$deskripsi')"
    }

    companion object{
        fun empty(): OdpDaerahTerjangkit{
            return OdpDaerahTerjangkit(false, "")
        }
    }
}