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

public class medicines extends AppCompatActivity {

    private String[][] med={
            {"Ashwagandha Tab","","","","199"},
            {"Giloy Ghan Vati","","","","99"},
            {"Swasari Vati","","","","99"},
            {"Kutajghan Vati","","","","79"},
            {"Arjun vati","","","","49"},
            {"Amla Churnav","","","","89"},
            {"Liv-Amrit Syrup","","","","159"},
            {"Herbal Tea","","","","69"},
            {"Triphala Churana","","","","69"},
            {"All in one Oil","","","","199"},
    };

    private String[] med_details={
            "It is beneficial for fatigue, restiveness and general weakness. \nIt also treats muscles deficiency, gastric problems, arthritis and others. Helps to increase energy of body cells naturally.\nAshwagandha is a natural herbal tonic for increasing memory and brain power.\nContains - 25 Caps.",

            "Giloy ghan vati is used as a treatment for general fever and immunity.\nUseful in generalized debility, fever, skin & urinary disorders.\nIt is also beneficial in general weakness, fever, dengue, chicken guinea\n Contains - 15 Tabs",

            "Swasari vati treats cold, cough and other such diseases.\nThe purity, quantity, and quality of the herbs utilized are the topmost priority.\nContains - 15 Tabs",

            "Kutajghan vati tablet cures diarrhea, stomach infections, stomach ulcers and irritable bowel syndrome (IBS). Harmful viruses and microorganisms that find their way into your body through contaminated food and drinks do untold harm to your stomach and intestines.\nKutajghan vati eliminates these viruses and organisms from the digestive tract, soothes your stomach ulcers and aids in digestion.\nContains - 15 Tabs",

            "Arjun Ghanvati is a very effective cure for all heart-related ailments. It fortifies the organ, strengthens heart muscles, controls heart rate, etc.\nArjun Ghanvati is a completely natural product and has no side effects. Get a healthy heart with arjun Ghanvati.\nContains - 15 Tabs",

            "Ayurvita brings to you Amla Churna to aid in digestion, to detoxify your system, to help you fight respiratory problems and boost your immunity.\nAmla Churna contains the natural goodness of amla and restores damages caused by toxins.\nIts anti-oxidant properties nourish and rejuvenate your body.\nContains - 100gms",

            "It is a liver tonic and indicated in the liver diseases.\nThis medicine is useful in fatty the liver, Hepatitis, Loss of appetite, Anemia and Jaundice.\nContains - 200ml",

            "The component herbs are enriched in vitamin C so they help you recover from cough and cold. It nourishes and relaxes your body and mind.\nIt improves your hepatic and digestive balance. This herbal tea also boosts your immunity and keeps diseases at bay.\nContains - 15 Sachets",

            "Triphala Churna is an ayurvedic product, beneficial in vision related troubles, constipation and stomach problems. Base, salt and acid mixed powder is warm in nature, digestible, tasteful and ignites hunger.\nContains - 100gm",

            "This is a unique ayurvedic formulation of  100% pure olive oil, alomond oil, walnut oil, sunflower oil, sesame oil, soybean oil, mustard oil and castor oils.\nThis blend penetrates deep into the skin, muscles, and scalp and hair shafts and keep it nourish and strengthen.\nThe ayurvedic formulation is also valued for its ability to improve muscle activity, treat headaches, and moisturize skin and hair.\nIdeal for healing winter dryness and itchiness, this oil help to maintain water loss and skin hydration.\nContains - 100 ml"
    };


    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button gtc,h;
    ListView lstv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);
        lstv=findViewById(R.id.listViewBM);
        gtc=findViewById(R.id.buttonGoToCartBM);
        h=findViewById(R.id.imageButton1);

        gtc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(medicines.this,cart_medicines.class));
            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(medicines.this,Home.class));
            }
        });

        list=new ArrayList<>();
        for (int i = 0; i < med.length; i++) {
            item=new HashMap<String, String>();
            item.put("line1",med[i][0]);
            item.put("line2",med[i][1]);
            item.put("line3","Click to know more"+med[i][2]);
            item.put("line4",med[i][3]);
            item.put("line5","Total Cost : "+med[i][4]+"/-");
            list.add(item);
        }

        sa=new SimpleAdapter(this,list,R.layout.multilines,new String[] {"line1","line2","line3","line4","line5"},new int[] {R.id.lineA,R.id.lineB,R.id.lineC,R.id.lineD,R.id.lineE});
        lstv.setAdapter(sa);

        lstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent i1=new Intent(medicines.this,medicines_detail.class);
                i1.putExtra("text1",med[i][0]);
                i1.putExtra("text2",med_details[i]);
                i1.putExtra("text3",med[i][4]);
                startActivity(i1);

            }
        });

    }
}