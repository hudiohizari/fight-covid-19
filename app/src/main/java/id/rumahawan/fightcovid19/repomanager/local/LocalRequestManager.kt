package id.rumahawan.fightcovid19.repomanager.local

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class LocalRequestManager(
    context: Context
) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun setString(tag: String, value: String?){
        preference.edit().putString(tag, value).apply()
    }
    fun getString(tag: String): String{
        return preference.getString(tag, "") ?: ""
    }

    fun setInt(tag: String, value: Int?){
        preference.edit().putInt(tag, value ?: 0).apply()
    }
    fun getInt(tag: String): Int{
        return preference.getInt(tag, 0)
    }

    fun setBoolean(tag: String, value: Boolean?){
        preference.edit().putBoolean(tag, value ?: false).apply()
    }
    fun getBoolean(tag: String): Boolean{
        return preference.getBoolean(tag, false)
    }

    fun resetSession() {
        preference.edit().clear().apply()
    }

}