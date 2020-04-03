package id.rumahawan.fightcovid19.features.tracking.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import id.rumahawan.fightcovid19.features.tracking.model.data.OdpData
import id.rumahawan.fightcovid19.features.tracking.model.data.OdpKondisi
import id.rumahawan.fightcovid19.features.tracking.model.data.OdpDaerahTerjangkit
import id.rumahawan.fightcovid19.features.tracking.model.data.OdpTujuan
import kotlinx.android.parcel.Parcelize

@Parcelize
class RequestODP(
    @SerializedName("data_odp")
    val odpData: OdpData = OdpData.empty(),
    @SerializedName("tujuan_odp")
    val odpTujuan: OdpTujuan = OdpTujuan.empty(),
    @SerializedName("daerah_terjangkit")
    val daerahTerjangkit: OdpDaerahTerjangkit = OdpDaerahTerjangkit.empty(),
    @SerializedName("kondisi_odp")
    val odpKondisi: OdpKondisi = OdpKondisi.empty()
) : Parcelable{

    companion object{
        fun empty(): RequestODP{
            return RequestODP(
                OdpData.empty(),
                OdpTujuan.empty(),
                OdpDaerahTerjangkit.empty(),
                OdpKondisi.empty()
            )
        }
    }

    override fun toString(): String {
        return "RequestODP(dataOdp=$odpData, tujuanODP=$odpTujuan, daerahTerjangkit=$daerahTerjangkit, kondisiODP=$odpKondisi)"
    }
}
