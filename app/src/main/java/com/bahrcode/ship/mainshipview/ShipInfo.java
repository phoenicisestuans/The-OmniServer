package com.bahrcode.ship.mainshipview;

import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ShipInfo implements Comparable<ShipInfo> {

    @SerializedName("ErrorList")
    private List<String> errorList;

    @SerializedName("IsAccessible")
    private Boolean isAccessible;

    @SerializedName("LastSync")
    private LocalDateTime lastSync;

    @SerializedName("Name")
    private String name;

    @SerializedName("PingRate")
    private Double pingRate;

    @SerializedName("PingTime")
    private Double pingTime;

    @SerializedName("XPSScore")
    private Double xpsScore;


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

        Log.v("lastSync", lastSync.toString());

    }

    public List<String> getErrorList() {
        return errorList;
    }

    public Boolean getAccessible() {
        return isAccessible;
    }

    public LocalDateTime getLastSync() {
        return lastSync;
    }

    public String getName() {
        return name;
    }

    public Double getPingRate() {
        return pingRate;
    }

    public Double getPingTime() {
        return pingTime;
    }

    public Double getXpsScore() {
        return xpsScore;
    }

    /**
     * Implements a default sort for when no sort is specified or a tie breaker is needed.
     * Sorts ships alphabetically by name.
     * @param o - The ship to compare to.
     * @return an integer used to sort the ship.
     */
    @Override
    public int compareTo(ShipInfo o) {
        return getName().compareTo(o.getName());
    }
}
