package com.example.dam_weather.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class WeatherDatabaseHelper extends SQLiteOpenHelper {


    private static final int VERSION_BASE_DATOS = 1;
    private static final String NOMBRE_BASE_DATOS = "db_weather.sqlite";

    public WeatherDatabaseHelper(@Nullable Context context) {
         super(context,NOMBRE_BASE_DATOS,null,VERSION_BASE_DATOS);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // ejecutar script de creación (actual) de la base de datos
        sqLiteDatabase.execSQL("CREATE TABLE weather( " +
                "nombre_ciudad TEXT, " +
                "temperatura REAL, " +
                "fecha TEXT," +
                "icon_path TEXT " +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        switch (i){
            case 1:
                // actualizacion de v1 a v2 (actual)
                sqLiteDatabase.execSQL("ALTER TABLE weather ADD COLUMN nuevo TEXT;");
        }

    }
}
