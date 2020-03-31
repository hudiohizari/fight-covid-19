package id.rumahawan.fightcovid19.navigation.ui.activity

import android.os.Bundle
import android.os.Handler
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import id.rumahawan.fightcovid19.R
import id.rumahawan.fightcovid19.utils.BaseActivity
import id.rumahawan.fightcovid19.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class ActivityMain : BaseActivity() {

    private lateinit var navController: NavController
    private var twice = false

    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.fMain)
        NavigationUI.setupWithNavController(bnvMain, navController)
    }

    override fun onBackPressed() {
        when (navController.graph.startDestination) {
            navController.currentDestination?.id -> {
                if (twice) {
                    super.onBackPressed()
                    return
                }
                twice = true
                toast("Tekan sekali lagi untuk keluar")
                Handler().postDelayed({ twice = false }, 2000)
            }
            else -> super.onBackPressed()
        }
    }
}
