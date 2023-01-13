package com.example.dam_weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dam_weather.adapters.Ft2Adapter;
import com.example.dam_weather.adapters.ModelFt2;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainFt2Activity extends AppCompatActivity {

    private Ft2Adapter adapter;
    private ArrayList<ModelFt2> weatherModelList;
    private RecyclerView lista;
    private TextView ciudad;
    private TextView text_pred;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ft2);
        lista = findViewById(R.id.listWeather);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        lista.setLayoutManager(manager);
        lista.setHasFixedSize(true);
        weatherModelList = new ArrayList<>();
        adapter = new Ft2Adapter(this,weatherModelList);
        lista.setAdapter(adapter);
        ciudad =  (TextView) findViewById(R.id.editTextCiudadFt2);
        text_pred = (TextView) findViewById(R.id.textView_ft2);
        RequestQueue queue = Volley.newRequestQueue(this);


        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelFt2 p = weatherModelList.get(lista.getChildAdapterPosition(view));
                Intent p3 = new Intent(MainFt2Activity.this, MainActivityFt2_extended.class);
                p3.putExtra("date",p.getFecha());
                p3.putExtra("temp_max",p.getMax_temp());
                p3.putExtra("temp_min",p.getMin_temp());
                p3.putExtra("prep",p.getPrecipitaciones());
                p3.putExtra("vel_max",p.getMaxwind_kph());
                p3.putExtra("icon_path",p.getIcon_path());
                MainFt2Activity.this.startActivity(p3);
            }
        });





        ciudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://api.weatherapi.com/v1/forecast.json?key=6da5c2e18536409fb4d162040230101&q=" + ciudad.getText().toString() + "&days=7&aqi=no&alerts=no";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                weatherModelList.clear();
                                try {
                                    JSONObject forecast = response.getJSONObject("forecast");
                                    JSONArray lista_pred = forecast.getJSONArray("forecastday");
                                    for(int i = 0; i < lista_pred.length(); i++) {
                                        JSONObject dateObj = lista_pred.getJSONObject(i);
                                        String fecha = dateObj.getString("date");
                                        String max_temp = dateObj.getJSONObject("day").getString("maxtemp_c");
                                        String min_temp = dateObj.getJSONObject("day").getString("mintemp_c");
                                        String vel_temp = dateObj.getJSONObject("day").getString("maxwind_kph");
                                        String precip = dateObj.getJSONObject("day").getString("totalprecip_mm");
                                        String icon = dateObj.getJSONObject("day").getJSONObject("condition").getString("icon");
                                        weatherModelList.add(new ModelFt2(fecha,max_temp,min_temp,precip,vel_temp,icon));
                                    }
                                    adapter.notifyDataSetChanged();
                                    text_pred.setVisibility(View.VISIBLE);
                                    text_pred.setText("La predicción a 3 dias de la población " + ciudad.getText().toString());

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