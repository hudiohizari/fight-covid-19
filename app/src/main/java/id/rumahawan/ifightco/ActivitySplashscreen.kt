package id.rumahawan.ifightco

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.rumahawan.ifightco.features.main.ui.activity.ActivityNavigation
import id.rumahawan.ifightco.utils.launchNewActivity
import kotlinx.android.synthetic.main.activity_splashscreen.*

class ActivitySplashscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val video = Uri.parse("android.resource://" +  packageName + "/" + R.raw.splash)
        vvMain.setVideoURI(video)
        vvMain.setOnCompletionListener { startNextActivity() }
        vvMain.start()
    }

    private fun startNextActivity() {
        if (isFinishing) return
        launchNewActivity(ActivityNavigation::class.java)
        finish()
    }
}
