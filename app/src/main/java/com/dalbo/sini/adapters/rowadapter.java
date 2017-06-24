package com.dalbo.sini.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dalbo.sini.R;
import com.dalbo.sini.db.svc_data;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by alkaaf on 5/20/2016.
 */
public class rowadapter extends ArrayAdapter<svc_data>{
    Context ctx;
    ArrayList<svc_data> data;
    LayoutInflater li;
    public rowadapter(Context context, ArrayList<svc_data> data) {
        super(context, R.layout.rowview, data);
        this.ctx = context;
        this.data = data;
        li = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = li.inflate(R.layout.rowview, parent, false);
        int imgres = 0;
        TextView ljudul = (TextView)v.findViewById(R.id.L_judul);
        ImageView imgicon = (ImageView)v.findViewById(R.id.L_ico);
        TextView ldesk = (TextView)v.findViewById(R.id.L_deskripsi);
        TextView lalamat = (TextView)v.findViewById(R.id.L_alamat);
        RelativeLayout rvlayout = (RelativeLayout)v.findViewById(R.id.rowlayout);
        ljudul.setText(data.get(position).getBarang());
        lalamat.setText(data.get(position).getAlamat());
        ldesk.setText(data.get(position).getDetail());
        if(data.get(position).getJenis() == 1 || data.get(position).getStatus() == 1){
            imgres = R.drawable.ic_message_black_24dp;
        }
        else if(data.get(position).getJenis() == 2 || data.get(position).getStatus() == 2){
            imgres = R.drawable.ic_assistant_black_24dp;

        } else if(data.get(position).getStatus() == 3){
            imgres = R.mipmap.ic_clear;
        }

        imgicon.setImageResource(imgres);
        return v;
    }
}

