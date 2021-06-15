package com.example.fastmilk_costumer.entity;

public class User {
    private String key;
    private String email;
    private String namaLengkap;
    private String notelp;
    private String alamat;

    public User() {}

    public User(String key, String Email, String nama, String notelp, String alamat) {
        this.key = key;
        this.email = Email;
        this.namaLengkap = nama;
        this.notelp = notelp;
        this.alamat = alamat;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
