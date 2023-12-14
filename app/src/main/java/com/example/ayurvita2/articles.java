package com.example.ayurvita2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class articles extends AppCompatActivity {

    private String[][] ayurved_details={
            {"What is Ayurveda ?","","","","Click to View"},
            {"Types of Ayurveda","","","","Click to View"},
            {"Myths and Facts","","","","Click to View"},
            {"Benifits of Ayurveda","","","","Click to View"},
            {"Rules for Eating","","","","Click to View"},
            {"Ayurvedic Treatment","","","","Click to View"}

    };

    private int[] imgs={
            R.drawable.what,
            R.drawable.types,
            R.drawable.mvf,
            R.drawable.benifits,
            R.drawable.myth,
            R.drawable.treatment
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView l1;

    Button h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        l1=findViewById(R.id.listViewA);
        h=findViewById(R.id.imageButton1);

        list=new ArrayList<>();
        for (int i = 0; i < ayurved_details.length; i++) {
            item=new HashMap<String, String>();
            item.put("line1",ayurved_details[i][0]);
            item.put("line2",ayurved_details[i][1]);
            item.put("line3",ayurved_details[i][2]);
            item.put("line4",ayurved_details[i][3]);
            item.put("line5",ayurved_details[i][4]);
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.multilines,new String[] {"line1","line2","line3","line4","line5"},new int[] {R.id.lineA,R.id.lineB,R.id.lineC,R.id.lineD,R.id.lineE});
        l1.setAdapter(sa);

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent i1=new Intent(articles.this,articles_detail.class);
                i1.putExtra("text1",ayurved_details[i][0]);
                i1.putExtra("text2",imgs[i]);
                startActivity(i1);
            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(articles.this, Home.class));
            }
        });
    }
}