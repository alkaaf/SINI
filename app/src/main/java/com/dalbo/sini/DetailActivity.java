package com.dalbo.sini;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dalbo.sini.db.service;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alkaaf on 5/22/2016.
 */
public class DetailActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener{
    TextView det_id, det_tglpost, det_barang, det_deskripsi, det_nama, det_telp, det_status, det_tgl;
    Button bopenmap, changestatus;
    String slat, slng;
    String status;
    int id;
    Intent i;
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        // map declaration
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.dmap);
        mapFragment.getMapAsync(this);

        i = getIntent();
        det_id = (TextView)findViewById(R.id.det_id);
        det_tglpost = (TextView)findViewById(R.id.det_tglpost);
        det_barang = (TextView)findViewById(R.id.det_barang);
        det_deskripsi = (TextView)findViewById(R.id.det_desk);
        det_nama = (TextView)findViewById(R.id.det_atasnama);
        det_telp = (TextView)findViewById(R.id.det_telp);
        det_status = (TextView)findViewById(R.id.det_status);
        det_tgl = (TextView)findViewById(R.id.det_tgl);
        bopenmap = (Button)findViewById(R.id.openmap);
        changestatus = (Button)findViewById(R.id.changestatus);
        bopenmap.setOnClickListener(this);
        det_telp.setOnClickListener(this);
        changestatus.setOnClickListener(this);

        String stat = null;
        id = Integer.parseInt(i.getExtras().getString("id"));
        det_id.setText("ID: "+i.getExtras().getString("id"));
        det_tglpost.setText(new SimpleDateFormat("MM/dd/yyyy").format(new Date(Long.parseLong(i.getExtras().getString("timestamp"))*1000)));
        det_barang.setText(i.getExtras().getString("barang"));
        det_deskripsi.setText(i.getExtras().getString("deskripsi"));
        det_nama.setText(i.getExtras().getString("nama"));
        det_telp.setText(i.getExtras().getString("telp"));
        status = i.getExtras().getString("status");
        if(status.equalsIgnoreCase("1")){
            det_status.setText("Hilang");
            changestatus.setText("Sudah dikembalikan");
        }
        else if(status.equalsIgnoreCase("2")){
            det_status.setText("Ditemukan");
            changestatus.setText("Sudah ditemukan");
        }
        else if(status.equalsIgnoreCase("3")){
            det_status.setText("Terselesaikan");
            changestatus.setEnabled(false);
        }

        det_tgl.setText(i.getExtras().getString("tanggal"));
        slat = i.getExtras().getString("lat");
        slng = i.getExtras().getString("lng");
    }

    @Override
    public void onClick(View v) {
        if(v == bopenmap) {
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("lat", slat);
            intent.putExtra("lng", slng);
            intent.putExtra("nama", i.getExtras().getString("nama"));
            intent.putExtra("alamat", i.getExtras().getString("alamat"));
            intent.putExtra("telp",i.getExtras().getString("telp"));
            intent.putExtra("status",i.getExtras().getString("status"));
            startActivity(intent);
        }
        else if (v == det_telp){
            String phone = det_telp.getText().toString();
            Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(i);
        }
        else if(v == changestatus){
            service s = new service(getString(R.string.webservice_address),this,this);
            s.changestatus(id,3);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng pos = new LatLng(Double.parseDouble(slat),Double.parseDouble(slng));
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.addMarker(new MarkerOptions().position(pos).icon(BitmapDescriptorFactory.fromResource(R.drawable.markpoint)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,18));
    }
}