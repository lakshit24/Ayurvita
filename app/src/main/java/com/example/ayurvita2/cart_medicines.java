package com.example.ayurvita2;

import static android.app.AlertDialog.THEME_HOLO_LIGHT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class cart_medicines extends AppCompatActivity {

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    TextView total;
    ListView l1;
    private DatePickerDialog datepick;
    private TimePickerDialog timepick;
    private Button datebtn,btncheckout;
    private String[][] meds= {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_medicines);

        datebtn=findViewById(R.id.buttonDate);
        btncheckout=findViewById(R.id.buttonCheckout);
        total=findViewById(R.id.textViewTC);
        l1=findViewById(R.id.listViewCart);

        SharedPreferences sharedpref=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedpref.getString("username","").toString();

        Database db =new Database(getApplicationContext(),"my_db",null,1);

        float totalAmount=0;
        ArrayList dbData =db.getCartData(username,"medicine");
        meds= new String[dbData.size()][];
        for (int i = 0; i < meds.length; i++) {
            meds[i]=new String[5];
        }
//
        for (int i = 0; i < dbData.size(); i++) {
            String arrdata=dbData.get(i).toString();
            String[] strdata=arrdata.split(java.util.regex.Pattern.quote("$"));
            meds[i][0]=strdata[0];
            meds[i][4]="Cost : "+strdata[1]+"/-";
            totalAmount=totalAmount+Float.parseFloat(strdata[1]);
        }
//
        total.setText("Total Cost :"+totalAmount);

        list=new ArrayList<>();
        for (int i = 0; i < meds.length; i++) {
            item=new HashMap<String,String>();
            item.put("line1",meds[i][0]);
            item.put("line2",meds[i][1]);
            item.put("line3",meds[i][2]);
            item.put("line4",meds[i][3]);
            item.put("line5",meds[i][4]);
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.cartmultilines,new String[] {"line1","line2","line3","line4","line5"},new int[] {R.id.lineA,R.id.lineB,R.id.lineC,R.id.lineD,R.id.lineE});
        l1.setAdapter(sa);

        initDatePicker();
        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepick.show();
            }
        });


        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(cart_medicines.this,medicineBook.class);
                i1.putExtra("price",total.getText());
                i1.putExtra("date",datebtn.getText());
                startActivity(i1);

            }
        });


    }


    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month=month+1;
                datebtn.setText(dayOfMonth+"/"+month+"/"+year);
            }
        };

        Calendar cal=Calendar.getInstance();
        int yr=cal.get(Calendar.YEAR);
        int mnth=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        //noinspection deprecation
        int style= THEME_HOLO_LIGHT;
        datepick = new DatePickerDialog(this,style,dateSetListener,yr,mnth,day);
        datepick.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }
    }
