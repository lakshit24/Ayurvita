package com.example.ayurvita2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    EditText edRusername,edRemail,edRpassword,edRconfirm;
    TextView tvToLogin;
    Button btnR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edRusername=findViewById(R.id.editTextRegisterUsername);
        edRemail=findViewById(R.id.editTextRegisterEmail);
        edRpassword=findViewById(R.id.editTextRegisterPassword);
        edRconfirm=findViewById(R.id.editTextRegisterConfirmPassword);
        btnR=findViewById(R.id.buttonRegister);
        tvToLogin=findViewById(R.id.textViewToLogin);

        tvToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edRusername.getText().toString();
                String email=edRemail.getText().toString();
                String password=edRpassword.getText().toString();
                String confirmPassword=edRconfirm.getText().toString();
                Database db =new Database(getApplicationContext(),"my_db",null,1);
                int flagEmail=0,flagPass=0;


                if(username.length()==0||email.length()==0||password.length()==0||confirmPassword.length()==0){
                    Toast.makeText(getApplicationContext(), "Please Fill All Details", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(isValidEmailId(email.trim())){
//                        Toast.makeText(getApplicationContext(), "Valid Email Address.", Toast.LENGTH_SHORT).show();
                        flagEmail=1;
                    }else{
                        Toast.makeText(getApplicationContext(), "InValid Email Address.", Toast.LENGTH_SHORT).show();
                    }

                    if(password.compareTo(confirmPassword)==0){
                        if (isValid(password)){
                            flagPass=1;
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Must contain a Number,Alphabet and a Special Character", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
                if(flagPass==1 && flagEmail==1) {
                    db.register(username,email,password);
                    Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, Login.class));
                }
            }
        });
    }

    private boolean isValidEmailId(String emailId){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(emailId).matches();
    }
    public  static  boolean isValid(String passwordHere){
        int f1=0,f2=0,f3=0;
        if(passwordHere.length()<0){
            return  false;
        }
        else{
            for (int i = 0; i < passwordHere.length(); i++) {
                if(Character.isLetter(passwordHere.charAt(i))){
                    f1=1;
                }
            }
            for (int i = 0; i < passwordHere.length(); i++) {
                if(Character.isDigit(passwordHere.charAt(i))){
                    f2=1;
                }
            }
            for (int i = 0; i < passwordHere.length(); i++) {
                char c=passwordHere.charAt(i);
                if(c>=33 && c<=46 || c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1){
                return true;
            }
            return false;
        }
    }


}