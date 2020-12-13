package com.example.tabactivity.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tabactivity.R

class WeatherMainActivity: AppCompatActivity() {
    lateinit var person_name:String
    lateinit var person_phone:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_weather)
        person_name = intent.getStringExtra("name").toString()
        person_phone = intent.getStringExtra("phone").toString()
    }


}
