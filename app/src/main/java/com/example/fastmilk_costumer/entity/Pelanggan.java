package com.example.fastmilk_costumer.entity;

public class Pelanggan extends User {

    public Pelanggan() {}

    public Pelanggan(String key, String email, String nama, String notelp, String alamat) {
        super(key, email, nama, notelp, alamat);
    }

    @Override
    public String getKey() {
        return super.getKey();
    }

    @Override
    public void setKey(String key) {
        super.setKey(key);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getNamaLengkap() {
        return super.getNamaLengkap();
    }

    @Override
    public void setNamaLengkap(String namaLengkap) {
        super.setNamaLengkap(namaLengkap);
    }

    @Override
    public String getNotelp() {
        return super.getNotelp();
    }

    @Override
    public void setNotelp(String notelp) {
        super.setNotelp(notelp);
    }

    @Override
    public String getAlamat() {
        return super.getAlamat();
    }

    @Override
    public void setAlamat(String alamat) {
        super.setAlamat(alamat);
    }
}
