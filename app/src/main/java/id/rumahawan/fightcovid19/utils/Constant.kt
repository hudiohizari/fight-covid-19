package id.rumahawan.fightcovid19.utils

import id.rumahawan.fightcovid19.R
import id.rumahawan.fightcovid19.navigation.model.data.MenuItem

object Constant {

    const val URL_EDUKASI = "https://www.prixa.ai/post/corona-covid-19/"
    const val URL_DIAGNOSA_MANDIRI = "https://covid19.prixa.ai/?pId=817d1193-f4ce-439e-b357-16466695d970&appId=9f9d9731-8331-4cb3-a441-95d5d8b44d7f/"
    const val URL_DATA_INTERNASIONAL = "https://www.worldometers.info/coronavirus/"
    const val URL_DT_PEDULI_DONASI = "https://dtpeduli.org/donasi/program/"
    const val URL_GRAFIK = "https://infocorona.id/chart/"
    const val URL_KEMENKES = "https://www.kemkes.go.id/"
    const val URL_BNBP = "https://bnpb.go.id/"
    const val URL_PRIXA = "https://www.prixa.ai/"
    const val URL_ID_CLOUDHOST = "https://idcloudhost.com/"
    const val URL_DRAMA_TELYU = "https://www.instagram.com/drama.telyu/"

    const val KEY_URL = "KEY_URL"
    const val KEY_TITLE = "KEY_TITLE"
    const val KEY_HOSPITAL_LIST = "KEY_HOSPITAL_LIST"

    fun getHomeMenu(): MutableList<MenuItem>{
        val list = mutableListOf<MenuItem>()
        list.add(
            MenuItem(
                R.drawable.ic_hospital,
                "RS. Rujukan",
                true
            )
        )
        list.add(
            MenuItem(
                R.drawable.ic_book,
                "Edukasi Covid 19",
                true
            )
        )
        list.add(
            MenuItem(
                R.drawable.ic_stethoscope,
                "Diagnosa Mandiri",
                true
            )
        )
        list.add(
            MenuItem(
                R.drawable.ic_database,
                "Data Internasional",
                true
            )
        )
        list.add(
            MenuItem(
                R.drawable.ic_phone,
                "Hotline",
                true
            )
        )
        list.add(
            MenuItem(
                R.drawable.img_dtpeduli,
                "Indonesia Peduli",
                true
            )
        )
        list.add(
            MenuItem(
                R.drawable.ic_map,
                "Lacak ODS/ODP Belitung",
                true
            )
        )
        list.add(
            MenuItem(
                R.drawable.ic_graphic,
                "Grafik",
                true
            )
        )

        return list
    }

    fun getHomeSponsor(): MutableList<MenuItem>{
        val list = mutableListOf<MenuItem>()
        list.add(
            MenuItem(
                R.drawable.img_kemenkes,
                "Kemenkes",
                true
            )
        )
        list.add(
            MenuItem(
                R.drawable.img_bnbp,
                "BNBP",
                true
            )
        )
        list.add(
            MenuItem(
                R.drawable.img_prixa,
                "Prixa",
                true
            )
        )
        list.add(
            MenuItem(
                R.drawable.img_idcloudhost,
                "ID CloudHost",
                true
            )
        )
        list.add(
            MenuItem(
                R.drawable.img_dramatelyu,
                "Drama Tel-U",
                true
            )
        )

        return list
    }

    fun getMenuOdp(): MutableList<MenuItem>{
        val list = mutableListOf<MenuItem>()
        list.add(
            MenuItem(
                R.drawable.ic_hospital,
                "RS. Rujukan",
                true
            )
        )
        list.add(
            MenuItem(
                R.drawable.ic_book,
                "Edukasi Covid 19",
                true
            )
        )
        list.add(
            MenuItem(
                R.drawable.ic_pin,
                "Pos COVID-19",
                false
            )
        )

        return list
    }
}