package com.example.ayurvita2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class medicineBook extends AppCompatActivity {

    EditText etName,etAddress,etPincode,etContact;
    Button btnBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_book);

        etAddress=findViewById(R.id.editTextBookAddress);
        etName=findViewById(R.id.editTextBookFullName);
        etContact=findViewById(R.id.editTextBookContact);
        etPincode=findViewById(R.id.editTextBookPincode);
        btnBook=findViewById(R.id.buttonBook);



        // The phone number is valid
        Intent intent=getIntent();
        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");
//        String time=intent.getStringExtra("time");

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpref=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedpref.getString("username","").toString();
                Database db =new Database(getApplicationContext(),"my_db",null,1);
                db.addOrder(username,etName.getText().toString(),etAddress.getText().toString(),etContact.getText().toString(),Integer.parseInt(etPincode.getText().toString()),date.toString(),"",Float.parseFloat(price[1]),"medicine");
                db.removeCart(username,"medicine");
                Toast.makeText(getApplicationContext(), "Booking Successful!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(medicineBook.this, Home.class));
            }
        });
    }
}