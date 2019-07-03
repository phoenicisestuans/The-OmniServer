package com.bahrcode.ship.mainshipview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainShipView extends AppCompatActivity {

    private static final String TAG = "MainShipView";

    private RecyclerView recyclerView;

    @Override
    protected void onResume(){
        super.onResume();

        ShipModel.getInstance().fetchShipInfo();

        ShipModel.getInstance().connectRecyclerView(recyclerView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ship_view);
        Log.d(TAG, "mainShipView onCreate: started.");

        recyclerView = findViewById(R.id.recycler_view);

    }



}
