package com.dalbo.sini.db;

/**
 * Created by alkaaf on 5/20/2016.
 */
public class svc_data {
    String nama, barang, detail, alamat, tanggal, telp;
    int jenis, id, status;
    double lat, lng;
    long timestamp;

    public svc_data(String nama, String barang, String detail, String alamat, String tanggal, int jenis, int id, int status, double lat, double lng, long timestamp, String telp) {
        this.nama = nama;
        this.barang = barang;
        this.detail = detail;
        this.alamat = alamat;
        this.tanggal = tanggal;
        this.jenis = jenis;
        this.id = id;
        this.status = status;
        this.lat = lat;
        this.lng = lng;
        this.timestamp = timestamp;
        this.telp = telp;
    }

    public String getNama() {
        return nama;
    }

    public String getBarang() {
        return barang;
    }

    public String getDetail() {
        return detail;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public int getJenis() {
        return jenis;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getTelp() {
        return telp;
    }
}
