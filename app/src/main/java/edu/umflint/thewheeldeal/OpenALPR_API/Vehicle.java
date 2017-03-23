package edu.umflint.thewheeldeal.OpenALPR_API;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by NSammut on 3/23/2017.
 */

public class Vehicle {

    private String make;
    private String model;
    private String color;

    public Vehicle(JSONObject json)
    {
        try {
            this.make = json.getJSONArray("results").getJSONObject(0).getJSONObject("vehicle").getJSONArray("make_model").getJSONObject(0).getString("name").split("_")[0];
            this.model = json.getJSONArray("results").getJSONObject(0).getJSONObject("vehicle").getJSONArray("make_model").getJSONObject(0).getString("name").split("_")[1];
            this.color = json.getJSONArray("results").getJSONObject(0).getJSONObject("vehicle").getJSONArray("color").getJSONObject(0).getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
