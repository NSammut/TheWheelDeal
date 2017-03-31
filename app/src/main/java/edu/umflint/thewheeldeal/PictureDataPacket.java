package edu.umflint.thewheeldeal;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Luke on 3/31/2017.
 */

public class PictureDataPacket {
    private LatLng location;
    private String make;
    private String model;
    private String color;

    public PictureDataPacket(LatLng GPS, String carMake, String carModel, String carColor)
    {
        this.location = GPS;
        this.make = carMake;
        this.model = carModel;
        this.color = carColor;
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

    public void seColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
