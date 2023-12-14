package com.example.ayurvita2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class packageBook extends AppCompatActivity {

    EditText etName,etAddress,etPincode,etContact;
    Button btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_book);
        etAddress=findViewById(R.id.editTextBookContact);
        etName=findViewById(R.id.editTextBookFullName);
        etContact=findViewById(R.id.editTextBookContact2);
        etPincode=findViewById(R.id.editTextBookPincode);
        btnBook=findViewById(R.id.buttonBook);


                Intent intent=getIntent();
                String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
                String date=intent.getStringExtra("date");
                String time=intent.getStringExtra("time");

                btnBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(etPincode.getText().toString().length()<6||etContact.getText().toString().length()<10||etAddress.getText().toString().length()==0||etName.getText().toString().length()==0)
                        {
                            Toast.makeText(getApplicationContext(), "Please Fill All Details Correctly", Toast.LENGTH_SHORT).show();
                        }
                        
                        else
                        {
                        SharedPreferences sharedpref=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        String username=sharedpref.getString("username","").toString();
                        Database db =new Database(getApplicationContext(),"my_db",null,1);
                        db.addOrder(username,etName.getText().toString(),etAddress.getText().toString(),etContact.getText().toString(),Integer.parseInt(etPincode.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1]),"package");
                        db.removeCart(username,"package");
                        Toast.makeText(getApplicationContext(), "Booking Successful!!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(packageBook.this, Home.class));
                        }
                    }
                });
            }




}

