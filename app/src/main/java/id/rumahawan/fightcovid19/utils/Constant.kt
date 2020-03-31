package id.rumahawan.fightcovid19.utils

import id.rumahawan.fightcovid19.R
import id.rumahawan.fightcovid19.navigation.model.data.MenuItem

object Constant {

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
}