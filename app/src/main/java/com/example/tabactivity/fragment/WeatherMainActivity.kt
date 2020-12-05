package com.example.tabactivity.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tabactivity.R

class WeatherMainActivity: AppCompatActivity() {
    lateinit var data:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_weather)
        data = intent.getStringExtra("name").toString()
    fun getData(): String {
        return data
    }
    }
}
