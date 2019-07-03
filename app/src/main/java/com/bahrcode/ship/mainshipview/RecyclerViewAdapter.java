package com.bahrcode.ship.mainshipview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";


    private List<ShipInfo> data = new ArrayList<>();

    private Comparator<ShipInfo> comparator = null;

    /**
     * Sets the ship's comparator. Will run the comparator immediately.
     * @param comparator - A comparator to sort the ships with.
     */
    public void setComparator(Comparator<ShipInfo> comparator) {
        this.comparator = comparator;
        data.sort(comparator);
    }

    /**'
     * Changes the view's list of ships. If a comparator is set, it will be used on this list
     * immediately.
     * @param data - A list of ships to be displayed.
     */
    public void setData(List<ShipInfo> data) {
        data.sort(comparator);
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        ShipInfo ship = data.get(position);

        //viewHolder.itemView.getContext();


        viewHolder.shipName.setText(ship.getName());

        viewHolder.xpsScore.setText(ship.getXpsScore().toString());
        viewHolder.accessible.setText(ship.getAccessible().toString());
        viewHolder.pingTime.setText(ship.getPingTime().toString());
        viewHolder.pingRate.setText(ship.getPingRate().toString());
        viewHolder.lastSync.setText(ship.getLastSync().toString());
        //viewHolder.errorList.setText(ship.getErrorList().toString());


        int yellow = ContextCompat.getColor(viewHolder.itemView.getContext(), R.color.yellow);
        int red = ContextCompat.getColor(viewHolder.itemView.getContext(), R.color.red);

        if ((ship.getXpsScore() < 3.2) && (ship.getXpsScore() >= 2.5))
            viewHolder.xpsScore.setTextColor(yellow);
        else if (ship.getXpsScore() < 2.5)
            viewHolder.xpsScore.setTextColor(red);

        if (ship.getAccessible().equals(false))
            viewHolder.xpsScore.setTextColor(red);

        //TODO: get last sync colours all checked out
        // they still need to be set with math.

        if ((ship.getPingTime() < 300) && (ship.getPingTime() >= 100))
            viewHolder.pingTime.setTextColor(yellow);
        else if (ship.getPingTime() >= 300)
            viewHolder.pingTime.setTextColor(red);

        //TODO: figure out how pingtime should be colored??
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView shipName;
        TextView xpsScoreText;
        TextView xpsScore;
        TextView accessibleText;
        TextView accessible;
        TextView pingTimeText;
        TextView pingTime;
        TextView pingRateText;
        TextView pingRate;
        TextView lastSyncText;
        TextView lastSync;
        TextView errorListText;
        TextView errorList;

        RelativeLayout parentLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            shipName = itemView.findViewById(R.id.ship_name);

            xpsScoreText = itemView.findViewById(R.id.xps_score_text);
            xpsScore = itemView.findViewById(R.id.xps_score);

            accessibleText = itemView.findViewById(R.id.accessible_text);
            accessible = itemView.findViewById(R.id.accessible);

            pingTimeText = itemView.findViewById(R.id.ping_time_text);
            pingTime = itemView.findViewById(R.id.ping_time);

            pingRateText = itemView.findViewById(R.id.ping_rate_text);
            pingRate = itemView.findViewById(R.id.ping_rate);

            lastSyncText = itemView.findViewById(R.id.last_sync_text);
            lastSync = itemView.findViewById(R.id.last_sync);

            errorListText = itemView.findViewById(R.id.error_list_text);
            errorList = itemView.findViewById(R.id.error_list);

            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
