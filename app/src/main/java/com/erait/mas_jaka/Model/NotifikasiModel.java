package com.erait.mas_jaka.Model;

public class NotifikasiModel {
    String tipe_notifikasi, date, time;
    Integer jumlah_poin;

    public NotifikasiModel() {
    }

    public NotifikasiModel(String tipe_notifikasi, String date, String time, Integer jumlah_poin) {
        this.tipe_notifikasi = tipe_notifikasi;
        this.date = date;
        this.time = time;
        this.jumlah_poin = jumlah_poin;
    }

    public String getTipe_notifikasi() {
        return tipe_notifikasi;
    }

    public void setTipe_notifikasi(String tipe_notifikasi) {
        this.tipe_notifikasi = tipe_notifikasi;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getJumlah_poin() {
        return jumlah_poin;
    }

    public void setJumlah_poin(Integer jumlah_poin) {
        this.jumlah_poin = jumlah_poin;
    }
}
