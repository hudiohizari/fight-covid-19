package id.rumahawan.fightcovid19.navigation.ui.activity

import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.rumahawan.fightcovid19.R
import id.rumahawan.fightcovid19.utils.BaseActivity
import id.rumahawan.fightcovid19.utils.toast
import kotlinx.android.synthetic.main.activity_navigation.*

class ActivityNavigation : BaseActivity() {

    private lateinit var navController: NavController
    private var twice = false

    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        navController = Navigation.findNavController(this, R.id.fMain)
        NavigationUI.setupWithNavController(bnvMain, navController)

        activateButton(btnHome)
        bnvMain.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menuHome -> {
                    navController.navigate(it.itemId)
                    activateButton(btnHome)
                }
                R.id.menuRefresh -> toast("Refresh pressed")
                R.id.menuMessage -> {
                    navController.navigate(it.itemId)
                    activateButton(btnInbox)
                }
            }
            true
        }
    }

    private fun activateButton(fab: FloatingActionButton){
        fab.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
        if (fab != btnHome) btnHome.setColorFilter(ContextCompat.getColor(this, R.color.lightGray))
        if (fab != btnInbox) btnInbox.setColorFilter(ContextCompat.getColor(this, R.color.lightGray))
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
