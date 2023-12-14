package com.example.ayurvita2;

import static com.google.android.material.datepicker.DateValidatorPointBackward.before;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Calendar;

public class orderDetails extends AppCompatActivity {


    public String[][] order_details={};
    HashMap<String,String> items;
    ArrayList list;
    Button h;
    SimpleAdapter sa;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        lst=findViewById(R.id.listViewBM);
        h=findViewById(R.id.imageButton1);

        Database db =new Database(getApplicationContext(),"my_db",null,1);
        SharedPreferences sharedpref=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedpref.getString("username","").toString();
        ArrayList dbData=db.getOrderData(username);

        order_details= new String[dbData.size()][];
        for (int i = 0; i < order_details.length; i++) {
            order_details[i]=new String[5];
            String arrData= dbData.get(i).toString();
            String[] strData= arrData.split(java.util.regex.Pattern.quote("$"));
            order_details[i][0]="Name :"+strData[0];
            order_details[i][1]="Number :"+strData[1];
            if(strData[7].compareTo("medicine")==0){
                order_details[i][3]="Delivery On :"+strData[4];
            }else {
                order_details[i][3]="Scheduled On :"+strData[4]+" "+strData[5];
            }
            order_details[i][2]="Rs."+strData[6];
            order_details[i][4]="Type :"+strData[7];
        }

        list=new ArrayList<>();
        for (int i = 0; i < order_details.length; i++) {
            items=new HashMap<String, String>();
            items.put("line1",order_details[i][0]);
            items.put("line2",order_details[i][1]);
            items.put("line3",order_details[i][2]);
            items.put("line4",order_details[i][3]);
            items.put("line5",order_details[i][4]);
            list.add(items);
        }

        sa=new SimpleAdapter(this,list,R.layout.multilines1,new String[] {"line1","line2","line3","line4","line5"},new int[] {R.id.lineA,R.id.lineB,R.id.lineC,R.id.lineD,R.id.lineE});
        lst.setAdapter(sa);
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(orderDetails.this, Home.class));
            }
        });


    }
}