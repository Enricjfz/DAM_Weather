package com.example.dam_weather;

import androidx.appcompat.app.AppCompatActivity;

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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;


public class MainFt1Activity extends AppCompatActivity {

    private WeatherDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ft1);
        TextView city = (TextView) findViewById(R.id.editTextCity);
        TextView temperatura = (TextView) findViewById(R.id.idTemperatura);
        ImageView icon = (ImageView) findViewById(R.id.icon);
        RequestQueue queue = Volley.newRequestQueue(this);
        TextView fecha = (TextView) findViewById(R.id.Fecha);
        dbHelper = new WeatherDatabaseHelper(this);


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
                                    temperatura.setText("Temperatura actual: " + temperature + " ºC");
                                    Picasso.get().load("http:".concat(response.getJSONObject("current").getJSONObject("condition").getString("icon"))).into(icon);
                                    String date = response.getJSONObject("current").getString("last_updated");
                                    fecha.setText("Medición " + date);
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