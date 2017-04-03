package edu.umflint.thewheeldeal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);


        //taylors placeholder intents until database works
        Button createaccountbtn = (Button)findViewById(R.id.signUpButton);
        Button loginbtn = (Button)findViewById(R.id.loginButton);

         createaccountbtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startActivity(new Intent(LoginScreen.this, CreateAccountScreen.class));
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener()
        {
            //stole lukes code
            public void onClick(View v)
            {
                EditText editText = (EditText) findViewById(R.id.usernameText);
                String username = editText.getText().toString();
                EditText editText1 = (EditText) findViewById(R.id.passwordText);
                String password = editText1.getText().toString();
                String message = "Wrong, type admin in username";
                String testAdmin = "admin";

                if (username.equals(testAdmin))
                {

                    message =  "Hello Admin!";
                    Toast.makeText(LoginScreen.this, message, Toast.LENGTH_LONG).show();
                    //intent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(new Intent(LoginScreen.this, Main2Activity.class));
                }
                else
                    Toast.makeText(LoginScreen.this, message, Toast.LENGTH_LONG).show();

            }
        });



    }
}
