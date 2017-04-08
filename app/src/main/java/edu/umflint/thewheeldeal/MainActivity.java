package edu.umflint.thewheeldeal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.GestureDetectorCompat;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureObject = new GestureDetectorCompat(this, new LearnGesture());
    }

    @Override
        public boolean onTouchEvent(MotionEvent event) {
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){
            if (event2.getX() > event1.getX()){
            //left to right swipe
                Intent intent = new Intent(
                        MainActivity.this, Main2Activity.class);
                finish();
                startActivity(intent);
            }
            else if (event2.getX() < event1.getX()){
            //right to left swipe
            }
            return true;
        }
    }
}
