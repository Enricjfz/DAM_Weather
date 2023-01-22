package com.example.dam_weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;



public class MainFt3Activity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView temp;
    private TextView hum;
    private Sensor tempSensor;
    private Sensor humSensor;
    private boolean isTempAvailable;
    private boolean isHumAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ft3);


        temp = findViewById(R.id.temp_ft3);
        hum =  findViewById(R.id.hum_ft3);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTempAvailable = true;
        }
        else {
            //no hay sensor de temperatura -- mensaje al usuario
            Toast.makeText(this, "No hay sensor de Temperatura en el Movil", Toast.LENGTH_SHORT).show();
            isTempAvailable = false;
            temp.setText("N/A");
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null) {
            humSensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            isHumAvailable = true;
        }
        else {
            //no hay sensor de humedad -- mensaje al usuario
            Toast.makeText(this, "No hay sensor de Humedad en el Movil", Toast.LENGTH_SHORT).show();
            isHumAvailable = false;
            hum.setText("N/A");
        }

    }

    //actualización de UI con nuevos datos
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            temp.setText(sensorEvent.values[0] + " ºC");
        }

        if(sensorEvent.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            hum.setText(sensorEvent.values[0] + " %");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    //eliminar el registro de sensores
    @Override
    protected void onResume() {
        super.onResume();
        if(isTempAvailable) {
            sensorManager.registerListener(this,tempSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(isHumAvailable) {
            sensorManager.registerListener(this,humSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }


    }

    @Override
    protected void onPause(){
        super.onPause();
        if(isTempAvailable || isHumAvailable) {
            sensorManager.unregisterListener(this);
        }
    }
}