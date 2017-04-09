package edu.umflint.thewheeldeal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Trophies extends AppCompatActivity {

    //counter for vehicles
    //retrieve vehicles user has analyzed from firebase and store in vehiclecounter
    int vehiclecounter = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophies);

        TextView bronzetext = (TextView)findViewById(R.id.Bronze_text);

        TextView silvertext = (TextView)findViewById(R.id.Silver_text);

        TextView goldtext = (TextView)findViewById(R.id.gold_text);

        ImageView bronze = (ImageView)findViewById(R.id.bronze_trophy);
        ImageView silver = (ImageView)findViewById(R.id.silver_trophy);
        ImageView gold = (ImageView)findViewById(R.id.gold_trophy);


        if (vehiclecounter < 10)
        {
            bronze.setVisibility(View.INVISIBLE);
            bronzetext.setVisibility(View.INVISIBLE);

            silver.setVisibility(View.INVISIBLE);
            silvertext.setVisibility(View.INVISIBLE);

            gold.setVisibility(View.INVISIBLE);
            goldtext.setVisibility(View.INVISIBLE);

        }
        else if (vehiclecounter >= 10 && vehiclecounter < 50)
        {
            silver.setVisibility(View.INVISIBLE);
            silvertext.setVisibility(View.INVISIBLE);

            gold.setVisibility(View.INVISIBLE);
            goldtext.setVisibility(View.INVISIBLE);

        }
        else if (vehiclecounter >= 50 && vehiclecounter < 100)
        {

            gold.setVisibility(View.INVISIBLE);
            goldtext.setVisibility(View.INVISIBLE);

        }

    }
}
