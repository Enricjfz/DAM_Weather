package com.example.dam_weather.threads;

import android.view.View;

import com.example.dam_weather.database.WeatherDB;
import com.example.dam_weather.database.WeatherDatabaseHelper;
import com.example.dam_weather.models.ModelWeather;

import java.util.concurrent.Callable;

public class MyThread implements Callable<ModelWeather> {

    private WeatherDatabaseHelper dbHelper;
    private String city;

    public MyThread (WeatherDatabaseHelper dbHelper, String city) {
      this.dbHelper = dbHelper;
      this.city = city;
    }

    //check if the raw exits and gets it, otherwise returns a null object
    @Override
    public ModelWeather call() throws Exception {
        ModelWeather p = null;
        if(WeatherDB.existsRow(this.dbHelper,this.city)) {
            //existe entrada previa
            p = WeatherDB.getWeather(this.dbHelper,this.city);

        }
        return p;
    }
}
