package com.example.dam_weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.button_ft1);
        Button b2 = (Button) findViewById(R.id.button_ft2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ft1 = new Intent(MainActivity.this,MainFt1Activity.class);
                MainActivity.this.startActivity(ft1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ft2 = new Intent(MainActivity.this, MainFt2Activity.class);
                MainActivity.this.startActivity(ft2);
            }
        });

    }
}