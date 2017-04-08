package edu.umflint.thewheeldeal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);


        mAuth = FirebaseAuth.getInstance();

        Button createaccountbtn = (Button)findViewById(R.id.signUpButton);
        Button loginbtn = (Button)findViewById(R.id.loginButton);

         createaccountbtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startActivity(new Intent(LoginScreen.this, CreateAccountScreen.class));
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    startActivity(new Intent(LoginScreen.this, MainActivity.class));
                } else {
                    // User is signed out
                }
            }
        };

        loginbtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                EditText email = (EditText) findViewById(R.id.emailText);
                EditText password = (EditText) findViewById(R.id.passwordText);

                if(!email.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(LoginScreen.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginScreen.this, "Logging in!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginScreen.this, MainActivity.class));
                                    }
                                }
                            });
                } else {
                    Toast.makeText(LoginScreen.this, "Username or password is blank!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
