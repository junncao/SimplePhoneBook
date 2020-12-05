package com.sunnyweather.android.logic.dao

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.example.tabactivity.WeatherApplication
import com.example.tabactivity.logic.model.Place



object PlaceDao {

    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

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