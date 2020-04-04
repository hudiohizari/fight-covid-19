package id.rumahawan.ifightco.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

fun Context.getBitmap(drawableRes: Int): Bitmap {
    val drawable = ContextCompat.getDrawable(this, drawableRes)!!
    val canvas = Canvas()
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    canvas.setBitmap(bitmap)
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    drawable.draw(canvas)
    return bitmap
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