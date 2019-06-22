package com.bahrcode.ship.mainshipview;

import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ShipInfo {

    @SerializedName("ErrorList")
    private List<String> errorList;

    @SerializedName("IsAccessible")
    private boolean isAccessible;

    @SerializedName("LastSync")
    private LocalDateTime lastSync;

    @SerializedName("Name")
    private String name;

    @SerializedName("PingRate")
    private double pingRate;

    @SerializedName("PingTime")
    private double pingTime;

    @SerializedName("XPSScore")
    private double xpsScore;


    public ShipInfo(List<String> errorList, boolean isAccessible, String name, double pingRate, double pingTime, double xpsScore, LocalDateTime lastSync) {
        this.errorList = errorList;
        this.isAccessible = isAccessible;
        this.name = name;
        this.pingRate = pingRate;
        this.pingTime = pingTime;
        this.xpsScore = xpsScore;
        this.lastSync = lastSync;
    }

    public void display(){
        if (!(errorList == null))
            Log.v("errorList", errorList.toString());

        Log.v("isAccessible", isAccessible + "");
        Log.v("name", name);
        Log.v("pingRate", pingRate + "");
        Log.v("pingTime", pingTime + "");
        Log.v("xpsScore", xpsScore + "");

        //TODO: check if this works
        //Log.v("lastSync", lastSync.atZone(ZoneId.of()).toString();
        Log.v("lastSync", lastSync.toString());
        //Log.v("lastSync", lastSync.toLocalTime().toString());
        //Log.v("lastSync", lastSync.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        //DateTimeFormatter FORMATTER = DateTimeFormatter.RFC_1123_DATE_TIME;
        //ZonedDateTime.now().format(FORMATTER);
        //Log.v("timeNow", ZonedDateTime.now().format(FORMATTER));
        //Log.v("lastSync", lastSync.now().format(FORMATTER));
        //ddd, dd MMM yyyy HH':'mm':'ss 'GMT'
        //Log.v("lastSync", LocalDateTime.parse(lastSync.toString()));

    }

}
