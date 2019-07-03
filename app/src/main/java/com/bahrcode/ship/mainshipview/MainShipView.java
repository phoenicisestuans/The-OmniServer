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

        // Sort by date of last sync. The more time since the last sync, th ehigher in the list.
        //ShipModel.getInstance().setComparator((ShipInfo a, ShipInfo b) ->
        //        a.getLastSync().compareTo(b.getLastSync()));

        // Sort by number of errors. The more errors, the higher in the list it goes.
        // FIXME hangs on the line where subtraction happens, I think. Not too sure about that.
        /*ShipModel.getInstance().setComparator((ShipInfo a, ShipInfo b) -> {
            Log.d("ShipSort", "Compare: " + a.getName() + " to " + b.getName());

            int diff = a.getErrorList().size() - b.getErrorList().size();

            if (diff == 0) {
                Log.d("ShipSort", "Finish: " + a.getName() + " to " + b.getName());

                // Default to alphabetical if needed.
                return a.compareTo(b);
            } else {

                Log.d("ShipSort", "Finish: " + a.getName() + " to " + b.getName());
                return diff;
            }
        });*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ship_view);
        Log.d(TAG, "mainShipView onCreate: started.");

        recyclerView = findViewById(R.id.recycler_view);
    }



}
