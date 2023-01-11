package com.example.dam_weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainFt3Activity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView temp;
    private TextView hum;
    private Sensor tempSensor;
    private boolean isTempAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ft3);


        temp = (TextView) findViewById(R.id.temp_ft3);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTempAvailable = true;
        }
        else {
            //no hay sensor de temperatura -- mensaje al usuario
            isTempAvailable = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        temp.setText(sensorEvent.values[0] + " ºC");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isTempAvailable) {
            sensorManager.registerListener(this,tempSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    protected void onPause(){
        super.onPause();
        if(isTempAvailable) {
            sensorManager.unregisterListener(this);
        }
    }
}