package com.dalbo.sini.home_tab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dalbo.sini.detail.DetailNemu;
import com.dalbo.sini.R;
import com.dalbo.sini.db.service;

/**
 * Created by alkaaf on 5/21/2016.
 */
public class Nemu extends Activity implements ListView.OnItemClickListener {
    static ListView lv;
    static service svc;
    static Nemu nemu;
    static Context cnemu;
    static String wsaddr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabnemu);
        nemu = this;
        cnemu = this;
        lv = (ListView) findViewById(R.id.nemulistview);
        wsaddr = getString(R.string.webservice_address);
        svc = new service(wsaddr, this, this);
        svc.getRecentNemuData(lv);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent detailnemu = new Intent(this, DetailNemu.class);
        detailnemu.putExtra("barang", svc.getSvc_data().get(position).getBarang());
        detailnemu.putExtra("nama", svc.getSvc_data().get(position).getNama());
        detailnemu.putExtra("deskripsi", svc.getSvc_data().get(position).getDetail());
        detailnemu.putExtra("telp", svc.getSvc_data().get(position).getTelp());
        detailnemu.putExtra("status", String.format("%d", svc.getSvc_data().get(position).getStatus()));
        detailnemu.putExtra("tanggal", svc.getSvc_data().get(position).getTanggal());
        detailnemu.putExtra("id", String.format("%d", svc.getSvc_data().get(position).getId()));
        detailnemu.putExtra("timestamp", String.format("%d", svc.getSvc_data().get(position).getTimestamp()));
        detailnemu.putExtra("lat", String.format("%f", svc.getSvc_data().get(position).getLat()));
        detailnemu.putExtra("lng", String.format("%f", svc.getSvc_data().get(position).getLng()));
        detailnemu.putExtra("alamat",svc.getSvc_data().get(position).getAlamat());
        startActivity(detailnemu);
    }
    public static void refresh(){
        if(svc != null){
            svc.getRecentNemuData(lv);
        }
    }
}
