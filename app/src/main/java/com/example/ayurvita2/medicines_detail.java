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

public class medicines_detail extends AppCompatActivity {

    TextView tvMed_name,tvTotal_cost;
    EditText et1;
    Button atc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines_detail);

        tvMed_name=findViewById(R.id.textViewMedicineName);
        tvTotal_cost=findViewById(R.id.textViewTotalCost);
        et1=findViewById(R.id.editTextTextMultiLineM);
        atc=findViewById(R.id.buttonAddtocartM);
        et1.setKeyListener(null);

        Intent inte=getIntent();
        tvMed_name.setText(inte.getStringExtra("text1"));
        et1.setText(inte.getStringExtra("text2"));
        tvTotal_cost.setText("Total Cost : "+inte.getStringExtra("text3")+" /-");



        atc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpref=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedpref.getString("username","").toString();
                String product=tvMed_name.getText().toString();
                float price=Float.parseFloat(inte.getStringExtra("text3").toString());

                Database db =new Database(getApplicationContext(),"my_db",null,1);
                if(db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Already Added",Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addToCart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(),"Added to cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(medicines_detail.this,medicines.class));
                }
            }
        });


    }
}