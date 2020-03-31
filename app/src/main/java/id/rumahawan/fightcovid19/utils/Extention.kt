package id.rumahawan.fightcovid19.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlin.math.roundToInt

fun Context.launchNewActivity(cls: Class<out AppCompatActivity>){
    startActivity(Intent(this, cls))
}

fun Context.launchNewActivityReturn(cls: Class<out AppCompatActivity>): Intent{
    return Intent(this, cls)
}

fun Context.launchNewActivity(cls: Class<out AppCompatActivity>, flags: Int){
    Intent(this, cls).also {
        it.flags = flags
        startActivity(it)
    }
}

fun Context.launchExternalIntent(data: String){
    Intent(Intent.ACTION_VIEW).also {
        it.type = "*/*"
        it.data = Uri.parse(data)
        startActivity(it)
    }
}

fun Context.toast(message: String?){
    Toast.makeText(this, message ?: "Text empty", Toast.LENGTH_SHORT).show()
}

fun Context.getPx(dp: Int): Int {
    return (dp * this.resources.displayMetrics.density).roundToInt()
}

fun View.snackbar(message: String?){
    Snackbar.make(this, message ?: "Text empty", Snackbar.LENGTH_SHORT).show()
}

fun View.snackbarLong(message: String?){
    Snackbar.make(this, message ?: "Text empty", Snackbar.LENGTH_INDEFINITE).also {snackbar ->
        snackbar.setAction("OK") {
            snackbar.dismiss()
        }
    }.show()
}