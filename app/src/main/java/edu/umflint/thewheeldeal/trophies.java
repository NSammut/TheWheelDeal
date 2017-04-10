package edu.umflint.thewheeldeal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Trophies extends AppCompatActivity {

    //counter for vehicles
    //retrieve vehicles user has analyzed from firebase and store in vehiclecounter
    int vehiclecounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophies);

        final TextView bronzetext = (TextView)findViewById(R.id.Bronze_text);
        final TextView silvertext = (TextView)findViewById(R.id.Silver_text);
        final TextView goldtext = (TextView)findViewById(R.id.gold_text);

        final ImageView bronze = (ImageView)findViewById(R.id.bronze_trophy);
        final ImageView silver = (ImageView)findViewById(R.id.silver_trophy);
        final ImageView gold = (ImageView)findViewById(R.id.gold_trophy);

        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase.child(user.getUid()).child("vehicleCounter").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                vehiclecounter = snapshot.getValue(int.class);

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
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}
