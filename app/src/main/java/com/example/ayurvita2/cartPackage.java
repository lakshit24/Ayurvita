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

public class cartPackage extends AppCompatActivity {

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    TextView total;
    ListView l1;
    private DatePickerDialog datepick;
    private TimePickerDialog timepick;
    private Button datebtn,timebtn,btncheckout;
    private String[][] packages= {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_package);
        datebtn=findViewById(R.id.buttonDate);
        timebtn=findViewById(R.id.buttonTime);
        btncheckout=findViewById(R.id.buttonCheckout);
        total=findViewById(R.id.textViewTC);
        l1=findViewById(R.id.listViewCart);

        SharedPreferences sharedpref=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedpref.getString("username","").toString();

        Database db =new Database(getApplicationContext(),"my_db",null,1);

        float totalAmount=0;
        ArrayList dbData =db.getCartData(username,"package");
        packages= new String[dbData.size()][];
        for (int i = 0; i < packages.length; i++) {
            packages[i]=new String[5];
        }
//
        for (int i = 0; i < dbData.size(); i++) {
            String arrdata=dbData.get(i).toString();
            String[] strdata=arrdata.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strdata[0];
            packages[i][4]="Cost : "+strdata[1]+"/-";
            totalAmount=totalAmount+Float.parseFloat(strdata[1]);
        }
//
        total.setText("Total Amount to be paid :"+totalAmount);

        list=new ArrayList<>();
        for (int i = 0; i < packages.length; i++) {
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5",packages[i][4]);
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

        initTimePicker();
        timebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                timepick.show();
            }
        });
        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent i1=new Intent(cartPackage.this,packageBook.class);
               i1.putExtra("price",total.getText());
               i1.putExtra("date",datebtn.getText());
               i1.putExtra("time",timebtn.getText());
               startActivity(i1);

            }
        });


    }
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
//                timebtn.setText(hourOfDay+":"+minute);
                if (isTimeWithinLimits(hourOfDay, minute)) {
                    timebtn.setText(String.format("%02d:%02d", hourOfDay, minute));
                } else {
                    // Display an error message or handle it as needed
                    Toast.makeText(cartPackage.this, "Operational Hours : 9 to 18 ", Toast.LENGTH_SHORT).show();
                }
            }
        };
        Calendar cal=Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR_OF_DAY);
        int mins=cal.get(Calendar.MINUTE);
        //noinspection deprecation
        int style= THEME_HOLO_LIGHT;
        timepick = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
//        timepick.setInterval(30);

    }
    private boolean isTimeWithinLimits(int hourOfDay, int minute) {
        // Define your specific time limits here, for example, between 9 AM and 6 PM
        int lowerLimitHour = 9;
        int upperLimitHour = 18;

        // Check if the selected time is within the limits
        return hourOfDay >= lowerLimitHour && hourOfDay <= upperLimitHour;
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