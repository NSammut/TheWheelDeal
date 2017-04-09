package edu.umflint.thewheeldeal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Challenges extends AppCompatActivity {

    //counter for vehicles
    //retrieve vehicles user has analyzed from firebase and store in vehiclecounter
    int vehiclecounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);


        TextView bronzetext = (TextView)findViewById(R.id.Bronze_text);
        TextView bronzedesctext = (TextView)findViewById(R.id.bronze_text_description);
        TextView silvertext = (TextView)findViewById(R.id.Silver_text);
        TextView silverdesctext = (TextView)findViewById(R.id.silver_text_description);
        TextView goldtext = (TextView)findViewById(R.id.gold_text);
        TextView golddesctext = (TextView)findViewById(R.id.gold_text_description);
        ImageView bronze = (ImageView)findViewById(R.id.bronze_vehicle);
        ImageView silver = (ImageView)findViewById(R.id.silver_vehicle);
        ImageView gold = (ImageView)findViewById(R.id.gold_vehicle);
        TextView vehiclecountertxt = (TextView)findViewById(R.id.vehicle_analyzed_number);
        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //update vehiclecounter from users stored info
        firebaseDatabase.child(user.getUid()).child("vehicleCounter").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                vehiclecounter = snapshot.getValue(int.class);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        String counter = String.valueOf(vehiclecounter);
        vehiclecountertxt.setText(counter);

        if (vehiclecounter >= 10 && vehiclecounter < 50)
        {
            bronze.setVisibility(View.INVISIBLE);
            bronzetext.setVisibility(View.INVISIBLE);
            bronzedesctext.setVisibility(View.INVISIBLE);
        }
        else if (vehiclecounter >= 50 && vehiclecounter < 100)
        {
            bronze.setVisibility(View.INVISIBLE);
            bronzetext.setVisibility(View.INVISIBLE);
            bronzedesctext.setVisibility(View.INVISIBLE);
            silver.setVisibility(View.INVISIBLE);
            silvertext.setVisibility(View.INVISIBLE);
            silverdesctext.setVisibility(View.INVISIBLE);
        }
        else if (vehiclecounter >= 100)
        {
            bronze.setVisibility(View.INVISIBLE);
            bronzetext.setVisibility(View.INVISIBLE);
            bronzedesctext.setVisibility(View.INVISIBLE);
            silver.setVisibility(View.INVISIBLE);
            silvertext.setVisibility(View.INVISIBLE);
            silverdesctext.setVisibility(View.INVISIBLE);
            gold.setVisibility(View.INVISIBLE);
            goldtext.setVisibility(View.INVISIBLE);
            golddesctext.setVisibility(View.INVISIBLE);
        }


    }
}
