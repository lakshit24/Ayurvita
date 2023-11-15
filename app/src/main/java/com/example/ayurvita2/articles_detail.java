package com.example.ayurvita2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class articles_detail extends AppCompatActivity {

    TextView t1;
    ImageView i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_detail);
        t1=findViewById(R.id.textViewAD);
        i1=findViewById(R.id.imageView);
        Intent intent=getIntent();
        t1.setText(intent.getStringExtra("text1"));

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            int resID=bundle.getInt("text2");
            i1.setImageResource(resID);
        }
    }
}