package com.bahrcode.ship.mainshipview;

import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.reactivex.disposables.Disposable;
import networking.NetworkManager;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

//This is a singleton object, which is only created once.

public class ShipModel {

    private RecyclerViewAdapter viewAdapter = new RecyclerViewAdapter();

    private static ShipModel mShipModel;

    /**
     * Sets the RecyclerViewAdapter's comparator.
     * @param comparator - The comparator to use.
     * @see RecyclerViewAdapter#setComparator(Comparator)
     */
    public void setComparator(Comparator<ShipInfo> comparator) {
        viewAdapter.setComparator(comparator);
    }

    public static ShipModel getInstance() {
        if (mShipModel == null){
            mShipModel = new ShipModel();
        }
        return mShipModel;
    }

    private ShipModel() {

    }


    public void fetchShipInfo(){
        //NOTE: the second lambda in a subscribe call is meant to
        // handle errors and exceptions. we are passing a throwable
        // in here to check if there was an issue creating our ships.
        Disposable x = NetworkManager
                .singleton
                .getNetwork()
                .getShips()
                .observeOn(mainThread())
                .subscribe(viewAdapter::setData, Throwable::printStackTrace);

    }

    public void connectRecyclerView(RecyclerView recyclerView){
        recyclerView.setAdapter(viewAdapter);

    }
}


