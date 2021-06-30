package com.example.fastmilk_costumer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {

    private ArrayList<ListItem> dataItem;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView namaProduk;
        TextView hargaProduk;
        ImageView imageIcon;


        ViewHolder(View v) {

            super(v);

            namaProduk = v.findViewById(R.id.namaProduk);
            hargaProduk = v.findViewById(R.id.hargaProduk);
            imageIcon = v.findViewById(R.id.ImageView);
        }
    }

    AdapterRecyclerView(ArrayList<ListItem> data) {

        this.dataItem = data;
    }

    @NonNull
    @Override
    public AdapterRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        //myonClickListener
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TextView namaProduk = holder.namaProduk;
        TextView hargaProduk = holder.hargaProduk;
        ImageView imageIcon = holder.imageIcon;

        namaProduk.setText(dataItem.get(position).getNamaProduk());
        hargaProduk.setText(dataItem.get(position).getHarga());
        imageIcon.setImageResource(dataItem.get(position).getImage());

    }

    @Override
    public int getItemCount() {

        return dataItem.size();
    }
}
