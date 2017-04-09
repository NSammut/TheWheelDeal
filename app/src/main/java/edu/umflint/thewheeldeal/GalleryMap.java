package edu.umflint.thewheeldeal;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GalleryMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference firebaseDatabase;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //***retrieve locations arraylist from firebase***
        firebaseDatabase.child(user.getUid()).child("savedcars").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                LatLng lastCar = new LatLng(70, 70);
                //List<String> yourStringArray = dataSnapshot.getValue(t);
                for(DataSnapshot child : snapshot.getChildren())
                {
                    PictureDataPacket temp = child.getValue(PictureDataPacket.class);
                    lastCar = new LatLng(temp.getLat(), temp.getLong());
                    mMap.addMarker(new MarkerOptions().position(lastCar).title(
                            temp.getColor() + " " + temp.getMake() + " " + temp.getModel()+lastCar.latitude+ " " +lastCar.longitude));
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastCar, 13));

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
/*
        // Add a marker for each picture taken and move the camera
        for(int i = locations.size(); i > 0; i--) {
            //LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(locations.get(i-1).getLocation()).title(
                    locations.get(i-1).getColor() + " "
                    + locations.get(i-1).getMake() + " "
                    + locations.get(i-1).getModel()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locations.get(i-1).getLocation()));

        }
        */
    }
}
