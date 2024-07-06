package com.example.ayurvita2;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.ayurvita2.databinding.ActivityMyaboutBinding;

public class Myabout extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myabout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapfrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng ayurvita = new LatLng(28.62214257738779,  77.29547233958938);
        mMap.addMarker(new MarkerOptions().position(ayurvita).title("Ayurvita"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ayurvita));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ayurvita,16f));
    }
}