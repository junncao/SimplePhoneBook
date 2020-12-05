package com.sunnyweather.android.ui.place

import androidx.lifecycle.*
import com.example.tabactivity.logic.Repository
import com.example.tabactivity.logic.model.Place

class PlaceViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(name:String,place: Place) = Repository.savePlace(name,place)

    fun getSavedPlace(name:String) = Repository.getSavedPlace(name)

    fun isPlaceSaved(name:String) = Repository.isPlaceSaved(name)

}