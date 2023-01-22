package com.example.dam_weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MainActivityFt2_extended extends AppCompatActivity {

    private TextView tDate;
    private TextView tTempMax;
    private TextView tTempMin;
    private TextView tPrep;
    private TextView tVelMax;
    private ImageView tIcon;
    private ImageView share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ft2_extended);
        Bundle extras = getIntent().getExtras();
        tDate = (TextView) findViewById(R.id.fecha_ft2_extended);
        tTempMax = (TextView) findViewById(R.id.t_max_ft2_extended);
        tTempMin = (TextView) findViewById(R.id.t_min_ft2_extended);
        tPrep = (TextView) findViewById(R.id.prep_ft2_extended);
        tVelMax = (TextView) findViewById(R.id.vel_viento_ft2_extended);
        tIcon = (ImageView) findViewById(R.id.icon_ft2_extended);
        share = (ImageView) findViewById(R.id.imageView_ft3_extended);

        String date = extras.getString("date");
        String t_max = extras.getString("temp_max");
        String t_min = extras.getString("temp_min");
        String prep = extras.getString("prep");
        String vel_wind = extras.getString("vel_max");
        String icon_path = extras.getString("icon_path");

        Picasso.get().load("https:".concat(icon_path)).into(tIcon);
        tDate.setText(date);
        tTempMax.setText(t_max + " º C");
        tTempMin.setText(t_min + " º C");
        tPrep.setText(prep + " mm");
        tVelMax.setText(vel_wind + " kph");

        //enviar predicción
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prediccion = "La prediccion del tiempo del dia " + date + " es la siguiente: " +
                        "la temperatura máxima es de " + t_max + " grados y la temperatura minima es de " +
                        t_min + " grados. En cuanto a las precipitaciones caerán a lo largo del dia un total de " +
                        prep + " milimetros cúbicos de agua y en cuanto a la velocidad, será de media de " +
                        vel_wind + " kph.";
                Intent intentSender = new Intent(android.content.Intent.ACTION_SEND);
                intentSender.setType("text/plain");
                intentSender.putExtra(Intent.EXTRA_TEXT,prediccion);
                Intent shareIntent = Intent.createChooser(intentSender, "Envia la prediccion...");
                startActivity(shareIntent);
            }
        });

    }
}