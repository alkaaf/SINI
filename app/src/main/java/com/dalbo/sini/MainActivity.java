package com.dalbo.sini;

import android.Manifest;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.dalbo.sini.home_tab.Ilang;
import com.dalbo.sini.home_tab.Nemu;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends TabActivity implements NavigationView.OnNavigationItemSelectedListener, LocationListener {
    //    service svc;
//    ListView lv;
    TabHost thost;
    TabHost.TabSpec tnemu, tilang;
    LocationManager lm;
    static double lat, lng;
    static Location loc;
    static LatLng ll;
    boolean gps_enabled = false, network_enabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Refreshing. Lat "+lat+" Lng"+lng, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Ilang.refresh();
                Nemu.refresh();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // configure tab
        thost = (TabHost) findViewById(android.R.id.tabhost);
        tnemu = thost.newTabSpec("Nemu");
        tilang = thost.newTabSpec("Ilang");
        tnemu.setIndicator("meNEMUkan");
        tnemu.setContent(new Intent(this, Nemu.class));
        tilang.setIndicator("kehILANGan");
        tilang.setContent(new Intent(this, Ilang.class));
        thost.addTab(tilang);
        thost.addTab(tnemu);

        // get location manager service
        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        // get network and gps enabled
        gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        // check manifest permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // request from gps then network provider

        if (!gps_enabled) {
            Intent gpsOptionsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(gpsOptionsIntent);
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cari) {
            Intent sv = new Intent(this, SearchActivity.class);
            this.startActivity(sv);
        } else if (id == R.id.nav_post) {
            Intent po = new Intent(this, PostActivity.class);
            this.startActivity(po);
        }  else if (id == R.id.nav_pp) {
            Intent tos = new Intent(this, TOSActivity.class);
            this.startActivity(tos);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static double getLat() {
        return lat;
    }

    public static double getLng() {
        return lng;
    }

    public static LatLng getLl() {
        return ll;
    }

    @Override
    public void onLocationChanged(Location location) {
        loc = location;
        lat = loc.getLatitude();
        lng = loc.getLongitude();
        ll = new LatLng(lat,lng);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
