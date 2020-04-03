package id.rumahawan.fightcovid19.features.tracking.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class OdpData(
    var nama: String = "",
    var usia: Int = 1,
    @SerializedName("no_hp")
    var noHp: String = "",
    var email: String = "",
    @SerializedName("no_ktp")
    var noKtp: String = "",
    var alamat: String = ""
) : Parcelable {

    override fun toString(): String {
        return "DataODP(nama='$nama', usia=$usia, noHp='$noHp', email='$email', noKtp='$noKtp', alamat='$alamat')"
    }

    companion object{
        fun empty(): OdpData {
            return OdpData("", 0, "", "", "", "")
        }
    }
}