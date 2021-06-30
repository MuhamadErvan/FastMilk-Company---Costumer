package com.example.fastmilk_costumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

public class KategoriActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager recylerViewLayoutManager;
    List<ListItem> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recylerViewLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(recylerViewLayoutManager);

        listItem = new ArrayList<>();

        listItem.add(new ListItem(R.drawable.logo_fastmilk, "Pasteurize milk Alpukat", "Rp. 7500"));
        listItem.add(new ListItem(R.drawable.logo_fastmilk, "Pasteurize milk Alpukat", "Rp. 7500"));
        listItem.add(new ListItem(R.drawable.logo_fastmilk, "Pasteurize milk Alpukat", "Rp. 7500"));
        listItem.add(new ListItem(R.drawable.logo_fastmilk, "Pasteurize milk Alpukat", "Rp. 7500"));

        AdapterRecyclerView recyclerViewAdapter = new AdapterRecyclerView((ArrayList<ListItem>) listItem);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}