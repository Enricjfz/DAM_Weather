package com.example.dam_weather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dam_weather.database.WeatherDB;
import com.example.dam_weather.database.WeatherDatabaseHelper;
import com.example.dam_weather.models.ModelWeather;
import com.example.dam_weather.notifications.NotificationHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;


public class MainFt1Activity extends AppCompatActivity {

    private WeatherDatabaseHelper dbHelper;
    private NotificationHandler handler;
    private TextView txCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ft1);
        TextView city = (TextView) findViewById(R.id.editTextCity);
        TextView temperatura = (TextView) findViewById(R.id.idTemperatura);
        ImageView icon = (ImageView) findViewById(R.id.icon);
        RequestQueue queue = Volley.newRequestQueue(this);
        TextView fecha = (TextView) findViewById(R.id.Fecha);
        txCity = (TextView) findViewById(R.id.nombreCiudad_ft1);
        dbHelper = new WeatherDatabaseHelper(this);
        handler = new NotificationHandler(this);


        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://api.weatherapi.com/v1/forecast.json?key=6da5c2e18536409fb4d162040230101&q=" + city.getText().toString() + "&aqi=yes";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Double temperature = response.getJSONObject("current").getDouble("temp_c");
                                    temperatura.setText(temperature + " ºC");
                                    Picasso.get().load("http:".concat(response.getJSONObject("current").getJSONObject("condition").getString("icon"))).into(icon);
                                    String date = response.getJSONObject("current").getString("last_updated");
                                    fecha.setText("Medición " + date);
                                    temperatura.setVisibility(View.VISIBLE);
                                    fecha.setVisibility(View.VISIBLE);
                                    icon.setVisibility(View.VISIBLE);
                                    txCity.setText(city.getText().toString());
                                    int air_quality = response.getJSONObject("current").getJSONObject("air_quality").getInt("us-epa-index");
                                    if(air_quality >= 3) {
                                        //alerta de calidad de aire -- se lanza notificacion
                                        String titleString = "Alerta de Calidad del Aire de Nivel " + air_quality;
                                        String msgString = "";
                                        switch(air_quality) {
                                            case 3: msgString = "La calidad del aire de la localidad de " +  city.getText().toString() + " es insaluble para los grupos sensitivos (asma, alergias,enfermedades respiratorias, ...)"; break;
                                            case 4: msgString = "La calidad del aire de la localidad de " +  city.getText().toString() + " es dañina para todo el mundo, se debe evitar cualquier tipo de actividad fisica en el aire libre"; break;
                                            case 5: msgString = "La calidad del aire de la localidad de " +  city.getText().toString() + " es muy dañina para todo el mundo, todo el mundo especialmente los niños y personas mayores, deben limitar la actividad en el exterior"; break;
                                            case 6: msgString = "La calidad del aire de la localidad de " +  city.getText().toString() + " es peligrosa, alerta sanitaria de emergencia. Toda la población se ve afectada"; break;

                                        }
                                        Notification.Builder nBuilder = handler.createNotification(titleString, msgString, false);
                                        handler.getManager().notify(1,nBuilder.build());

                                    }
                                    //ModelWeather p = new ModelWeather(city.getText().toString(),response.getJSONObject("current").getJSONObject("condition").getString("icon"),temperature,date);
                                    //añadir datos a  la db
                                    /*
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if(!WeatherDB.existsRow(dbHelper,p.getName_city())) {
                                                WeatherDB.save(dbHelper, p);
                                            }
                                        }
                                    }).start();
                                   */
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("connection","error");
                                Log.e("connection_error","" + error.networkResponse.statusCode);

                            }
                        });
                queue.add(jsonObjectRequest);
            }
        });

    }
}