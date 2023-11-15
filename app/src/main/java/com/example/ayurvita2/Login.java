package com.example.ayurvita2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText edUsername,edPassword;
    Button btn1;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUsername=findViewById(R.id.editTextRegisterUsername);
        edPassword=findViewById(R.id.editTextRegisterEmail);
        btn1=findViewById(R.id.buttonRegister);
        tv=findViewById(R.id.textViewToLogin);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edUsername.getText().toString();
                String password=edPassword.getText().toString();
                Database db =new Database(getApplicationContext(),"my_db",null,1);
                if (username.length()==0||password.length()==0){
                    Toast.makeText(getApplicationContext(), "Please Fill All Details", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (db.login(username,password)==1){
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedpref=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed =sharedpref.edit();
                        ed.putString("username",username);
                        ed.apply();//to save key and value
                        startActivity(new Intent(Login.this,Home.class));
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid username and password", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }
}