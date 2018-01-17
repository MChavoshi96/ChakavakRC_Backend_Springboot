package com.mkyong.model;

import java.lang.*;

public class GeoLocationModel
{
    private double latitude;
    private double longitude;
    private String placeDescription;

    public GeoLocationModel()
    {
        latitude = 0;
        longitude = 0;
    }

    public GeoLocationModel(double latitude, double longitude, String placeDescription)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.placeDescription = placeDescription;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) { this.placeDescription = placeDescription; }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
