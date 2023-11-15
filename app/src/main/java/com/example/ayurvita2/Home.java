package com.example.ayurvita2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedpref=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedpref.getString("username","").toString();
        Toast.makeText(getApplicationContext(),"Welcome "+username,Toast.LENGTH_SHORT).show();

        CardView med=findViewById(R.id.card3);
        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, medicines.class));
            }
        });

        CardView art=findViewById(R.id.card4);
        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,articles.class));
            }
        });

        CardView logout=findViewById(R.id.card6);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sharedpref.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(Home.this,Login.class));
            }
        });

        CardView packages=findViewById(R.id.card1);
        packages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,packages.class));
            }
        });

        CardView orderdet=findViewById(R.id.card5);
        orderdet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,orderDetails.class));
            }
        });

    }
}