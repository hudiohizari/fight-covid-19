package id.rumahawan.fightcovid19.utils

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

abstract class BaseActivity:
    AppCompatActivity(),
    KodeinAware
{

    override val kodein by kodein()

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

}