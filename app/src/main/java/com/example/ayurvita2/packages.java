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

public class packages extends AppCompatActivity {

    private String[][] packages={
            {"Package 1 : Rejuvenation Package","","","","1499"},
            {"Package 2 : Body Purification Package","","","","999"},
            {"Package 3 : Body Immunisation Package","","","","1199"},
            {"Package 4 : Stress Management Package","","","","999"},
            {"Package 5 : Beauty Care Package","","","","1299"},
    };
;

    private String[] package_details={
            "•This package is designed to relax and vitalize the body.\n•In addition to medical steam baths, herbal medicine and soothing face masks, it also includes two highlights of Ayurvedic treatment: full-body massages (Abhyanga) and pouring of warm liquids onto the forehead (Shirodhara). \n•Their base are warm herbal oils, which are mixed according to centuries-old recipes.\n\n"+"DURATION OF TREATMENT: 90-120 MIN",

            "•This Ayurveda package cleanses the body and mind.The focus is on cleansing procedures (Panchakarma), oil applications (Snehana), herbal steam baths (Swendanam), pouring of warm oils onto the forehead (Shirodhara) and - as a climax - the royal oil bath treatment (Pizhichil).\n•In their treatments, doctors and therapists draw on the effectiveness of traditional remedies - such as Ayurvedic oils, herbs and ghee.\n\n"+"DURATION OF TREATMENT: 90-120 MIN",

            "•Getting older and still not aging: With the Body Immunisation Package we come very close to this wish.\n•The focus is on the knowledge of the Rasayanas.\n•These are primarily rejuvenating remedies.\n•The treatment leads to a transformation of the body tissue (dhatus) and counteracts negative influences.\n\n"+"DURATION OF TREATMENT: 90-120 MIN",

            "•If you suffer from stress or burn-out symptoms, the Stress Management Package is the right choice.\n•Among other things, this package includes pouring of warm oil onto the forehead (Shirodhara), herbal steam baths as well as yoga and meditation.\n•It is the goal to let body and soul come to rest.\n\n"+"DURATION OF TREATMENT: 180-240 MIN",

            "•The success of these treatments is obvious: your complexion is visibly more radiant, your skin smoother, your body firmer.\n•The Beauty Care Package delivers on what it promises: it provides your skin with protection and beauty.\n•This treatment pursues one goal above all else: you are to feel completely relaxed.\n\n"+"DURATION OF TREATMENT: 90-120 MIN"
    };


    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;
    Button gtc;
    ListView l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
        gtc=findViewById(R.id.buttonGoToCartBM);
        l1=findViewById(R.id.listViewBM);

        list=new ArrayList<>();
        for (int i = 0; i < packages.length; i++) {
            item=new HashMap<String, String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3","Click to know more"+packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost : "+packages[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,R.layout.multilines,new String[] {"line1","line2","line3","line4","line5"},new int[] {R.id.lineA,R.id.lineB,R.id.lineC,R.id.lineD,R.id.lineE});
        l1.setAdapter(sa);

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent i1=new Intent(packages.this,package_detail.class);
                i1.putExtra("text1",packages[i][0]);
                i1.putExtra("text2",package_details[i]);
                i1.putExtra("text3",packages[i][4]);
                startActivity(i1);

            }
        });

        gtc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(packages.this, cartPackage.class));
            }
        });


    }
}