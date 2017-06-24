package com.dalbo.sini;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.dalbo.sini.db.service;
import com.dalbo.sini.detail.DetailSearch;

import java.util.List;

/**
 * Created by alkaaf on 5/21/2016.
 */
public class SearchActivity extends AppCompatActivity implements ListView.OnItemClickListener, TextWatcher{

//    Intent intent;
    ImageButton searchButton;
    service svc;
    EditText query;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        query = (EditText)findViewById(R.id.searchInput);
        query.addTextChangedListener(this);
        lv = (ListView)findViewById(R.id.searchlistview);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent detailsearch = new Intent(this, DetailSearch.class);
        detailsearch.putExtra("barang",svc.getSvc_data().get(position).getBarang());
        detailsearch.putExtra("nama",svc.getSvc_data().get(position).getNama());
        detailsearch.putExtra("deskripsi",svc.getSvc_data().get(position).getDetail());
        detailsearch.putExtra("telp",svc.getSvc_data().get(position).getTelp());
        detailsearch.putExtra("status",String.format("%d",svc.getSvc_data().get(position).getStatus()));
        detailsearch.putExtra("tanggal",svc.getSvc_data().get(position).getTanggal());
        detailsearch.putExtra("id",String.format("%d",svc.getSvc_data().get(position).getId()));
        detailsearch.putExtra("timestamp",String.format("%d",svc.getSvc_data().get(position).getTimestamp()));
        detailsearch.putExtra("lat",String.format("%f",svc.getSvc_data().get(position).getLat()));
        detailsearch.putExtra("lng",String.format("%f",svc.getSvc_data().get(position).getLng()));
        detailsearch.putExtra("alamat",svc.getSvc_data().get(position).getAlamat());
        startActivity(detailsearch);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String q = query.getText().toString();
        lv.setAdapter(null);
        if(!q.isEmpty()) {
            svc = new service(getString(R.string.webservice_address), this,this);
            svc.searchData(q, lv);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
