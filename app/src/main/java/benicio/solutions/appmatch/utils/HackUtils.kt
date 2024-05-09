package benicio.solutions.appmatch.utils

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import benicio.solutions.appmatch.model.UserModel
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HackUtils {
    companion object {

        private const val PREFS_USER: String = "user_prefs"
        private const val KEY_LIST: String = "user_list"

        fun configWindows(a: Activity, removeWindow:Boolean = true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            if ( removeWindow ){
                a.window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }

        }

        private fun getSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(PREFS_USER, Context.MODE_PRIVATE)
        }

        fun savePersistence(context: Context, nomeStr: String = "") =
            getSharedPreferences(context).edit().putString("userAtual", nomeStr).apply()

        fun returnPersistence(context: Context): String =
            getSharedPreferences(context).getString("userAtual", "") ?: ""


        fun saveList(context: Context, list: List<UserModel>) {
            val prefs = getSharedPreferences(context)
            val editor = prefs.edit()
            val gson = Gson()
            val json = gson.toJson(list)
            editor.putString(KEY_LIST, json)
            editor.apply()
        }

        fun getList(context: Context): MutableList<UserModel> {
            val prefs = getSharedPreferences(context)
            val gson = Gson()
            val json = prefs.getString(KEY_LIST, null)
            val type = object : TypeToken<MutableList<UserModel>>() {}.type
            return gson.fromJson(json, type) ?: mutableListOf()
        }
    }
}