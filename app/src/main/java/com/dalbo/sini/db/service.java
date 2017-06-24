package com.dalbo.sini.db;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.dalbo.sini.MainActivity;
import com.dalbo.sini.adapters.rowadapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alkaaf on 5/20/2016.
 */
public class service extends Activity {
    String srv;
    String urlmenemukan = "menemukan.php";
    String urlkehilangan = "kehilangan.php";
    String urlgetdata = "getdata.php?q=";
    String urlsearch = "search.php?q=";
    String urlinsert = "insertdata.php?";
    String urlgantistatus = "changestatus.php?";
    ArrayList<svc_data> d;
    String result = null;
    ProgressDialog pd;
    int request_status;
    int dummy;
    Context c;
    Activity caller;
    boolean err;

    public service(String srv, Context c, Activity caller) {
        this.c = c;
        this.caller = caller;
        this.srv = srv;
    }

    public ArrayList<svc_data> getSvc_data() {
        return this.d;
    }

    public void getData(final int id, ListView v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                svc_data gd = null;
                BufferedReader br = null;
                String buff, json;
                JSONObject jo;
                StringBuilder rawjson = new StringBuilder();
                try {
                    br = new BufferedReader(new InputStreamReader(new URL(srv + urlgetdata + id).openStream()));
                    while ((buff = br.readLine()) != null) {
                        rawjson.append(buff);
                    }
                    json = rawjson.toString();
                    jo = new JSONObject(json);
                    gd = new svc_data(jo.getString("nama"), jo.getString("barang"), jo.getString("detail"), jo.getString("alamat"), jo.getString("tanggal"), jo.getInt("jenis"), jo.getInt("id"), jo.getInt("status"), jo.getDouble("latitude"), jo.getDouble("longitude"), jo.getLong("timestamp"), jo.getString("telp"));
                    // set goes here
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    // end of view set
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void searchData(final String q, final ListView v) {
        final Context context = c;
        new Thread(new Runnable() {
            @Override
            public void run() {
                d = new ArrayList<svc_data>();
                BufferedReader br = null;
                String buff, json;
                JSONArray jo;
                StringBuilder rawjson = new StringBuilder();
                try {
                    br = new BufferedReader(new InputStreamReader(new URL(srv + urlsearch + q).openStream()));
                    while ((buff = br.readLine()) != null) {
                        rawjson.append(buff);
                    }
                    json = rawjson.toString();
                    jo = new JSONArray(json);
                    for (int i = 0; i < jo.length(); i++) {
                        d.add(new svc_data(
                                jo.getJSONObject(i).getString("nama"),
                                jo.getJSONObject(i).getString("barang"),
                                jo.getJSONObject(i).getString("detail"),
                                jo.getJSONObject(i).getString("alamat"),
                                jo.getJSONObject(i).getString("tanggal"),
                                jo.getJSONObject(i).getInt("jenis"),
                                jo.getJSONObject(i).getInt("id"),
                                jo.getJSONObject(i).getInt("status"),
                                jo.getJSONObject(i).getDouble("latitude"),
                                jo.getJSONObject(i).getDouble("longitude"),
                                jo.getJSONObject(i).getLong("timestamp"),
                                jo.getJSONObject(i).getString("telp")
                        ));
                    }
                    // set goes here

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rowadapter ra = new rowadapter(context, d);
                            v.setAdapter(ra);
                        }
                    });
                    // end of view set
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getRecentNemuData(final ListView v) {
        final Context context = c;
        new Thread(new Runnable() {
            @Override
            public void run() {
                d = new ArrayList<svc_data>();
                BufferedReader br = null;
                String buff, json;
                JSONArray jo;
                StringBuilder rawjson = new StringBuilder();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd = new ProgressDialog(c).show(c, "Me-Refresh", "Tunggu sebentar", false, true);
                    }
                });
                try {
                    br = new BufferedReader(new InputStreamReader(new URL(srv + urlmenemukan).openStream()));
                    while ((buff = br.readLine()) != null) {
                        rawjson.append(buff);
                    }
                    json = rawjson.toString();
                    jo = new JSONArray(json);
                    for (int i = 0; i < jo.length(); i++) {
                        d.add(new svc_data(
                                jo.getJSONObject(i).getString("nama"),
                                jo.getJSONObject(i).getString("barang"),
                                jo.getJSONObject(i).getString("detail"),
                                jo.getJSONObject(i).getString("alamat"),
                                jo.getJSONObject(i).getString("tanggal"),
                                jo.getJSONObject(i).getInt("jenis"),
                                jo.getJSONObject(i).getInt("id"),
                                jo.getJSONObject(i).getInt("status"),
                                jo.getJSONObject(i).getDouble("latitude"),
                                jo.getJSONObject(i).getDouble("longitude"),
                                jo.getJSONObject(i).getLong("timestamp"),
                                jo.getJSONObject(i).getString("telp")
                        ));
                    }
                    // set goes here

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rowadapter ra = new rowadapter(context, d);
                            v.setAdapter(null);
                            v.setAdapter(ra);
                            pd.dismiss();
                        }
                    });

                    // end of view set
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public void getRecentIlangData(final ListView v) {
        final Context context = c;
        new Thread(new Runnable() {
            @Override
            public void run() {
                d = new ArrayList<svc_data>();
                BufferedReader br = null;
                String buff, json;
                JSONArray jo;
                StringBuilder rawjson = new StringBuilder();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd = new ProgressDialog(c).show(c, "Me-Refresh", "Tunggu sebentar", false, true);
                    }
                });
                try {

                    br = new BufferedReader(new InputStreamReader(new URL(srv + urlkehilangan).openStream()));
                    while ((buff = br.readLine()) != null) {
                        rawjson.append(buff);
                    }
                    json = rawjson.toString();
                    jo = new JSONArray(json);
                    for (int i = 0; i < jo.length(); i++) {
                        d.add(new svc_data(
                                jo.getJSONObject(i).getString("nama"),
                                jo.getJSONObject(i).getString("barang"),
                                jo.getJSONObject(i).getString("detail"),
                                jo.getJSONObject(i).getString("alamat"),
                                jo.getJSONObject(i).getString("tanggal"),
                                jo.getJSONObject(i).getInt("jenis"),
                                jo.getJSONObject(i).getInt("id"),
                                jo.getJSONObject(i).getInt("status"),
                                jo.getJSONObject(i).getDouble("latitude"),
                                jo.getJSONObject(i).getDouble("longitude"),
                                jo.getJSONObject(i).getLong("timestamp"),
                                jo.getJSONObject(i).getString("telp")
                        ));
                    }
                    // set goes here

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rowadapter ra = new rowadapter(context, d);
                            v.setAdapter(null);
                            v.setAdapter(ra);
                            pd.dismiss();
                        }
                    });
                    // end of view set
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public String insertData(final svc_data d) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuilder param = new StringBuilder();
                StringBuilder res = new StringBuilder();
                param.append("nama=" + urlencoder(d.getNama()) + "&");
                param.append("barang=" + urlencoder(d.getBarang()) + "&");
                param.append("detail=" + urlencoder(d.getDetail()) + "&");
                param.append("alamat=" + urlencoder(d.getAlamat()) + "&");
                param.append("telp=" + d.getTelp() + "&");
                param.append("latitude=" + d.getLat() + "&");
                param.append("longitude=" + d.getLng() + "&");
                param.append("jenis=" + d.getJenis() + "&");
                param.append("status=" + d.getJenis() + "&");
                param.append("tanggal=" + d.getTanggal() + "&");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd = new ProgressDialog(c).show(c, "Memposting pengumuman", "Tunggu sebentar...", false, true);
                    }
                });
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new URL(srv + urlinsert + param.toString()).openStream()));
                    res.append(br.readLine());
                    result = res.toString();
                    request_status = new JSONObject(result).getInt("status");
                    dummy = 0;
                    err = false;
                } catch (IOException e) {
                    err = true;
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                        if (request_status == 1) {
                            Toast.makeText(c, "Posting pengumuman sukses!", Toast.LENGTH_SHORT);
                            caller.finish();
                        } else if (request_status == 1 && err) {
                            Toast.makeText(c, "Posting pengumuman gagal, coba tekan submit sekali lagi", Toast.LENGTH_SHORT);
                        }
                    }
                });
            }
        }).start();
        return result;
    }

    private String urlencoder(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void changestatus(final int id, final int status) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new URL(srv + urlgantistatus + "id=" + id + "&status=" + status).openStream()));
                    String res = br.readLine();
                    int stat = new JSONObject(res).getInt("status");
                    if(stat == 1){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(c,"Status "+id+" diubah",Toast.LENGTH_SHORT);
                            }
                        });
                    }
                    else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(c,"Status "+id+" gagal diubah",Toast.LENGTH_SHORT);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}