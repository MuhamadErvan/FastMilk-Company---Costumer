package com.example.fastmilk_costumer;

public class ListItem {
    int image;
    String namaProduk, harga;

    public ListItem(int image, String namaProduk, String harga) {
        this.image = image;
        this.namaProduk = namaProduk;
        this.harga = harga;
    }

    public int getImage() {
        return image;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public String getHarga() {
        return harga;
    }
}
