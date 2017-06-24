package com.dalbo.sini.home_tab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dalbo.sini.detail.DetailIlang;
import com.dalbo.sini.R;
import com.dalbo.sini.db.service;

/**
 * Created by alkaaf on 5/21/2016.
 */
public class Ilang extends Activity implements ListView.OnItemClickListener {
    static ListView lv;
    static service svc;
    static Ilang ilang;
    static Context cilang;
    static String wsaddr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabilang);
        lv = (ListView) findViewById(R.id.ilanglistview);
//        svc = new service("http://192.168.173.1/sini/");
        ilang = this;
        cilang = this;
        wsaddr = getString(R.string.webservice_address);
        svc = new service(getString(R.string.webservice_address),cilang,ilang);
        svc.getRecentIlangData(lv);
        lv.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent detailIlang = new Intent(this, DetailIlang.class);
        detailIlang.putExtra("barang", svc.getSvc_data().get(position).getBarang());
        detailIlang.putExtra("nama", svc.getSvc_data().get(position).getNama());
        detailIlang.putExtra("deskripsi", svc.getSvc_data().get(position).getDetail());
        detailIlang.putExtra("telp", svc.getSvc_data().get(position).getTelp());
        detailIlang.putExtra("status", String.format("%d", svc.getSvc_data().get(position).getStatus()));
        detailIlang.putExtra("tanggal", svc.getSvc_data().get(position).getTanggal());
        detailIlang.putExtra("id", String.format("%d", svc.getSvc_data().get(position).getId()));
        detailIlang.putExtra("timestamp", String.format("%d", svc.getSvc_data().get(position).getTimestamp()));
        detailIlang.putExtra("lat", String.format("%f", svc.getSvc_data().get(position).getLat()));
        detailIlang.putExtra("lng", String.format("%f", svc.getSvc_data().get(position).getLng()));
        detailIlang.putExtra("alamat",svc.getSvc_data().get(position).getAlamat());
        startActivity(detailIlang);
    }
    public static void refresh(){
//        service sv = new service(wsaddr,cilang,ilang);
        if(svc != null) {
            svc.getRecentIlangData(lv);
        }
    }
}
