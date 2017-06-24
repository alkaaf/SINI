package com.dalbo.sini;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.dalbo.sini.db.service;
import com.dalbo.sini.db.svc_data;

/**
 * Created by alkaaf on 5/20/2016.
 */
public class PostActivity extends Activity implements View.OnClickListener{
    Spinner jenis;
    EditText nama, telp, desk, alamat,tgl, barang;
    Button submit;
    TextView coord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);
        jenis = (Spinner)findViewById(R.id.spinjenis);
        nama = (EditText)findViewById(R.id.fi_nama);
        barang = (EditText)findViewById(R.id.fi_barang);
        telp = (EditText)findViewById(R.id.fi_telp);
        desk = (EditText)findViewById(R.id.fi_deskripsi);
        alamat = (EditText)findViewById(R.id.fi_alamat);
        tgl = (EditText)findViewById(R.id.fi_tgl);
        submit = (Button)findViewById(R.id.fi_button);
        coord = (TextView)findViewById(R.id.coord);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        svc_data d = new svc_data(
                nama.getText().toString(),
                barang.getText().toString(),
                desk.getText().toString(),
                alamat.getText().toString(),
                tgl.getText().toString(),
                jenis.getSelectedItemPosition()+1 ,
                jenis.getSelectedItemPosition()+1,
                0,
                MainActivity.lat,
                MainActivity.lng,
                0,
                telp.getText().toString()
        );
        service svc = new service(getString(R.string.webservice_address), this,this);
        svc.insertData(d);
    }
}
