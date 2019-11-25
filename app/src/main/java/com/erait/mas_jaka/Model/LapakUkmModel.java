package com.erait.mas_jaka.Model;

public class LapakUkmModel {
    private String id_user_ukm, nama_ukm, image;

    public LapakUkmModel() {
    }

    public LapakUkmModel(String id_user_ukm, String nama_ukm, String image) {
        this.id_user_ukm = id_user_ukm;
        this.nama_ukm = nama_ukm;
        this.image = image;
    }

    public String getId_user_ukm() {
        return id_user_ukm;
    }

    public void setId_user_ukm(String id_user_ukm) {
        this.id_user_ukm = id_user_ukm;
    }

    public String getNama_ukm() {
        return nama_ukm;
    }

    public void setNama_ukm(String nama_ukm) {
        this.nama_ukm = nama_ukm;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
