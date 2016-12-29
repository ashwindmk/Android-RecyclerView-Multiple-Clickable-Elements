package com.example.ashwin.recyclerviewmultipleclicklistenersinitem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemRecyclerViewAdapter.ItemClickListener {

    private ArrayList<Item> mItemsList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createItemsList();

        initRecyclerView();
    }

    private void createItemsList()
    {
        mItemsList = new ArrayList<>();

        Item item = new Item("Item 1");
        mItemsList.add(item);

        item = new Item("Item 2");
        mItemsList.add(item);

        item = new Item("Item 3");
        mItemsList.add(item);

        item = new Item("Item 4");
        mItemsList.add(item);

        item = new Item("Item 5");
        mItemsList.add(item);

        item = new Item("Item 6");
        mItemsList.add(item);
    }

    private void initRecyclerView()
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ItemRecyclerViewAdapter(this, this, mItemsList);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void onButton1Clicked(View v, int position) {
        Toast.makeText(getApplicationContext(), mItemsList.get(position).getText()+" Button 1 clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButton2Clicked(View v, int position) {
        Toast.makeText(getApplicationContext(), mItemsList.get(position).getText()+" Button 2 clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImageClicked(View v, int position) {
        Toast.makeText(getApplicationContext(), mItemsList.get(position).getText()+" Image clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(View v, int position) {
        Toast.makeText(getApplicationContext(), mItemsList.get(position).getText()+" clicked", Toast.LENGTH_SHORT).show();
    }
}
