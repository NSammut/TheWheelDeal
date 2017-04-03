package edu.umflint.thewheeldeal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CreateAccountScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_screen);


        //placeholder intent until database works
        Button createaccountbtn = (Button)findViewById(R.id.signUpButton);


        createaccountbtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String message =  "This doesnt do anything yet";
                Toast.makeText(CreateAccountScreen.this, message, Toast.LENGTH_LONG).show();
                startActivity(new Intent(CreateAccountScreen.this, LoginScreen.class));
            }
        });
    }
}
