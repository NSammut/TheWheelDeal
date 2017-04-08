package edu.umflint.thewheeldeal.Classes;

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
            String make = json.getJSONArray("results").getJSONObject(0).getJSONObject("vehicle").getJSONArray("make_model").getJSONObject(0).getString("name").split("_")[0];
            String makeFirstLetterCapatalized = make.substring(0, 1).toUpperCase();
            this.make = makeFirstLetterCapatalized + make.substring(1);

            String model = json.getJSONArray("results").getJSONObject(0).getJSONObject("vehicle").getJSONArray("make_model").getJSONObject(0).getString("name").split("_")[1];
            String modelFirstLetterCapatalized = model.substring(0, 1).toUpperCase();
            this.model = modelFirstLetterCapatalized + model.substring(1);

            String color = json.getJSONArray("results").getJSONObject(0).getJSONObject("vehicle").getJSONArray("color").getJSONObject(0).getString("name");
            String colorFirstLetterCapatalized = color.substring(0, 1).toUpperCase();
            this.color = colorFirstLetterCapatalized + color.substring(1);

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
