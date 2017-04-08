package edu.umflint.thewheeldeal;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Luke on 3/31/2017.
 */

public class PictureDataPacket {
    private double latitude;
    private double longitude;
    private String make;
    private String model;
    private String color;

    public PictureDataPacket()
    {
        latitude = 0;
        longitude = 0;
        make = "blank";
        model = "blank";
        color = "blank";
    }

    public PictureDataPacket(double lat, double longi, String carMake, String carModel, String carColor)
    {
        this.latitude = lat;
        this.longitude = longi;
        this.make = carMake;
        this.model = carModel;
        this.color = carColor;
    }

    public void setLat(double lat) {
        this.latitude = lat;
    }

    public double getLat() {
        return latitude;
    }

    public void setLong(double longi) {
        this.longitude = longi;
    }

    public double getLong() {
        return longitude;
    }

    public void setName(String make) {
        this.make = make;
    }

    public String getMake() {
        return make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
