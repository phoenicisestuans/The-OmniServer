package com.bahrcode.ship.mainshipview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.threeten.bp.temporal.ChronoUnit.HOURS;

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
        notifyDataSetChanged();
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

        // these colours are for changing text
        int yellow = ContextCompat.getColor(viewHolder.itemView.getContext(), R.color.yellow);
        int red = ContextCompat.getColor(viewHolder.itemView.getContext(), R.color.red);
        int lightBlue = ContextCompat.getColor(viewHolder.itemView.getContext(), R.color.lightBlue);

        Drawable blueBubble = ContextCompat.getDrawable(viewHolder.itemView.getContext(), R.drawable.rounded_blue_fill);
        Drawable yellowBubble = ContextCompat.getDrawable(viewHolder.itemView.getContext(), R.drawable.rounded_yellow_fill);
        Drawable redBubble = ContextCompat.getDrawable(viewHolder.itemView.getContext(), R.drawable.rounded_red_fill);
        //int blueBubble = R.drawable.rounded_blue_fill;
        //int yellowBubble = R.drawable.rounded_yellow_fill;
        //int redBubble = R.drawable.rounded_red_fill;


        ShipInfo ship = data.get(position);

        //viewHolder.itemView.getContext();

        viewHolder.shipName.setText(ship.getName());

        viewHolder.xpsScore.setText(ship.getXpsScore().toString());
        viewHolder.accessible.setText(ship.getAccessible().toString());
        viewHolder.pingTime.setText(ship.getPingTime().toString());
        viewHolder.pingRate.setText(ship.getPingRate().toString());

        long lastSyncInHours = ship.getLastSync().until(LocalDateTime.now(), HOURS);
        lastSyncInHours = (lastSyncInHours > 999) ? 999 : lastSyncInHours;
        viewHolder.lastSync.setText(lastSyncInHours + " hours ago");

        if ((lastSyncInHours > 24) && (lastSyncInHours <= 48))
            viewHolder.lastSyncSmear.setBackgroundColor(yellow);
            //viewHolder.lastSyncSmear.setImageDrawable(redBubble);
        else if (lastSyncInHours > 48)
            viewHolder.lastSyncSmear.setBackgroundColor(red);
        else if (lastSyncInHours <= 24)
            viewHolder.lastSyncSmear.setBackgroundColor(lightBlue);


        List<String> errorList = ship.getErrorList();
        String tempErrorList = "";

        if (errorList == null) {
            viewHolder.errorList.setText("No errors.");
        } else {
            for (String name : errorList){
                tempErrorList += (name + "\n");
            }

            //viewHolder.errorList.setText(errorList.toString());
            viewHolder.errorList.setText(tempErrorList);
            viewHolder.errorListNumber.setText(ship.getErrorList().size() + "");

            if (errorList.size() > 0)
                viewHolder.errorListNumberSmear.setBackgroundColor(red);
        }

        //if ((ship.getXpsScore() < 3.2) && (ship.getXpsScore() >= 2.5))
            //viewHolder.xpsScore.setTextColor(yellow);
        //else if (ship.getXpsScore() < 2.5)
            //viewHolder.xpsScore.setTextColor(red);

        if (ship.getAccessible().equals(false))
            viewHolder.accessibleSmear.setBackgroundColor(red);

        //TODO: get last sync colours all checked out
        // they still need to be set with math.

        if ((ship.getPingTime() < 300) && (ship.getPingTime() >= 100))
            viewHolder.pingTimeSmear.setBackgroundColor(yellow);
        else if (ship.getPingTime() >= 300)
            viewHolder.pingTimeSmear.setBackgroundColor(red);

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
        ImageView accessibleSmear;
        TextView accessibleText;
        TextView accessible;
        ImageView pingTimeSmear;
        TextView pingTimeText;
        TextView pingTime;
        TextView pingRateText;
        TextView pingRate;
        ImageView lastSyncSmear;
        TextView lastSyncText;
        TextView lastSync;
        ImageView errorListNumberSmear;
        TextView errorListNumber;
        TextView errorListText;
        TextView errorList;

        RelativeLayout parentLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            shipName = itemView.findViewById(R.id.ship_name);

            xpsScoreText = itemView.findViewById(R.id.xps_score_text);
            xpsScore = itemView.findViewById(R.id.xps_score);

            accessibleSmear = itemView.findViewById(R.id.accessible_smear);
            accessibleText = itemView.findViewById(R.id.accessible_text);
            accessible = itemView.findViewById(R.id.accessible);

            pingTimeSmear = itemView.findViewById(R.id.ping_time_smear);
            pingTimeText = itemView.findViewById(R.id.ping_time_text);
            pingTime = itemView.findViewById(R.id.ping_time);

            pingRateText = itemView.findViewById(R.id.ping_rate_text);
            pingRate = itemView.findViewById(R.id.ping_rate);

            lastSyncSmear = itemView.findViewById(R.id.last_sync_smear);
            lastSyncText = itemView.findViewById(R.id.last_sync_text);
            lastSync = itemView.findViewById(R.id.last_sync);

            errorListNumberSmear = itemView.findViewById(R.id.error_list_number_smear);
            errorListNumber = itemView.findViewById(R.id.error_list_number);
            errorListText = itemView.findViewById(R.id.error_list_text);
            errorList = itemView.findViewById(R.id.error_list);

            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
