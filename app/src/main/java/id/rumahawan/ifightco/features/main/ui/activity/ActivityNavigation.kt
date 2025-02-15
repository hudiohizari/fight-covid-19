package id.rumahawan.ifightco.features.main.ui.activity

import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.rumahawan.ifightco.R
import id.rumahawan.ifightco.utils.BaseActivity
import id.rumahawan.ifightco.utils.toast
import kotlinx.android.synthetic.main.activity_navigation.*

class ActivityNavigation : BaseActivity() {

    private lateinit var navController: NavController

    private var twice = false
    private var lastId = R.id.menuHome

    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        navController = Navigation.findNavController(this, R.id.fMain)
        NavigationUI.setupWithNavController(bnvMain, navController)

        activateButton(btnHome)
        bnvMain.setOnNavigationItemSelectedListener {
            if(lastId != it.itemId) {
                when (it.itemId) {
                    R.id.menuHome -> {
                        navController.navigate(it.itemId)
                        lastId = it.itemId
                    }
                    R.id.menuRefresh -> navController.navigate(lastId)
                    R.id.menuInbox -> {
                        navController.navigate(it.itemId)
                        lastId = it.itemId
                    }
                }
            }
            true
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.menuHome -> activateButton(btnHome)
                R.id.menuInbox -> activateButton(btnInbox)
            }
            lastId = destination.id
        }

        btnHome.setOnClickListener { navController.navigate(R.id.menuHome) }
        btnRefresh.setOnClickListener { navController.navigate(lastId) }
        btnInbox.setOnClickListener { navController.navigate(R.id.menuInbox) }
    }

    private fun activateButton(fab: FloatingActionButton){
        fab.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary))
        if (fab != btnHome) btnHome.setColorFilter(ContextCompat.getColor(this, R.color.lightGray))
        if (fab != btnInbox) btnInbox.setColorFilter(ContextCompat.getColor(this, R.color.lightGray))
    }

    override fun onBackPressed() {
        when (fMain.childFragmentManager.backStackEntryCount) {
            0 -> {
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
