package com.example.pawsibly;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

public class DistanceActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng currentLocation = new LatLng();
    LatLng breederLocation = new LatLng();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        distance= SphericalUtil.computeDistanceBetween(currentLocation,breederLocation);
        Toast.makeText(this,distance/1000+"km",Toast.LENGTH_SHORT).show();
        Log.e("distance","distance in km = "+distance/1000);
    }

}