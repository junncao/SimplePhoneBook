package com.sunnyweather.android.logic.dao

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.example.tabactivity.WeatherApplication
import com.example.tabactivity.logic.model.Place



object PlaceDao {

    fun savePlace(name:String,place: Place) {
        sharedPreferences().edit {
            putString(name, Gson().toJson(place))
        }
    }

    fun getSavedPlace(name: String): Place {
        val placeJson = sharedPreferences().getString(name, "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved(name:String) = sharedPreferences().contains(name)

    private fun sharedPreferences() =
        WeatherApplication.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)

//    fun SharedPreferences.edit(
//        commit: Boolean = false,
//        action: SharedPreferences.Editor.() -> Unit
//    ) {
//        val editor = edit()
//        action(editor)
//        if (commit) {
//            editor.commit()
//        } else {
//            editor.apply()
//        }
//    }
}