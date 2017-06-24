package com.dalbo.sini;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String slat, slng, snama, salamat, stelp, sico_id;
    double lat, lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        slat = getIntent().getExtras().getString("lat");
        slng = getIntent().getExtras().getString("lng");
        lat = Double.parseDouble(slat);
        lng = Double.parseDouble(slng);
        snama = getIntent().getExtras().getString("nama");
        salamat = getIntent().getExtras().getString("alamat");
        stelp = getIntent().getExtras().getString("telp");
        sico_id = getIntent().getExtras().getString("status");

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.setBuildingsEnabled(true);
        // Add a marker in Sydney and move the camera

        LatLng pos = new LatLng(lat,lng);
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                int id;
                View v = getLayoutInflater().inflate(R.layout.infow_view,null);
                TextView judul = (TextView)v.findViewById(R.id.L_judul);
                TextView alamat = (TextView)v.findViewById(R.id.L_alamat);
                TextView telp = (TextView)v.findViewById(R.id.L_telp);
                ImageView ico = (ImageView)v.findViewById(R.id.L_ico);

                judul.setText(snama);
                alamat.setText(salamat);
                telp.setText(stelp);
                if(sico_id.equalsIgnoreCase("2")){
                    ico.setImageResource(R.drawable.ic_assistant_black_24dp);
                }else if(sico_id.equalsIgnoreCase("1")){
                    ico.setImageResource(R.drawable.ic_message_black_24dp);
                }else{
                    ico.setImageResource(R.mipmap.ic_clear);
                }
                return v;
            }
        });
        mMap.addMarker(new MarkerOptions().position(pos).icon(BitmapDescriptorFactory.fromResource(R.drawable.markpoint)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,18));
//        mMap.loca
    }
}
