package com.example.dam_weather.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dam_weather.models.ModelWeather;

public class WeatherDB {
    //operations with the DB
    public static void save(WeatherDatabaseHelper dbHelper, ModelWeather p){
        SQLiteDatabase conn = dbHelper.getWritableDatabase();
        conn.execSQL("INSERT INTO weather VALUES ('"+ p.getName_city()+"'," +
                p.getTemperature() + ", " + ", " + p.getDate() + ", " + p.getIcon_path() +")");
    }

    public static ModelWeather getWeather(WeatherDatabaseHelper dbHelper, String city){
        String  [] arg = {city};
        SQLiteDatabase dbConnection = dbHelper.getReadableDatabase();
        Cursor c = dbConnection.rawQuery("SELECT * FROM weather WHERE nombre_ciudad = ?;", arg);
        ModelWeather p = null;
        if(c != null && c.getCount() > 0) {
            c.moveToFirst();
            p = new ModelWeather(c.getString(0),c.getString(3),c.getDouble(1),c.getString(2));
        }
        c.close();
        return p;
    }

    public static boolean existsRow(WeatherDatabaseHelper dbHelper, String city) {
        boolean exits = false;
        String  [] arg = {city};
        SQLiteDatabase dbConnection = dbHelper.getReadableDatabase();
        Cursor c = dbConnection.rawQuery("SELECT * FROM weather WHERE nombre_ciudad = ?;", arg);
        if(c != null && c.getCount() > 0) {
            exits = true;
        }
        c.close();
        return exits;

    }

    public static void updateRow(WeatherDatabaseHelper dbHelper, ModelWeather p ) {
        SQLiteDatabase conn = dbHelper.getWritableDatabase();
        conn.execSQL("UPDATE weather SET temperatura=" + p.getTemperature() + ", " + "fecha=" + p.getDate() + ", " + "icon_path=" + p.getIcon_path() +
                "WHERE nombre_ciudad=" + p.getName_city() + ";");

    }
}
