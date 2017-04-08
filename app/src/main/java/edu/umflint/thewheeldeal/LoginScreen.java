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
                    Log.d("SignIn", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("SignIn", "onAuthStateChanged:signed_out");
                }
            }
        };

        loginbtn.setOnClickListener(new View.OnClickListener()
        {
            //stole lukes code
            public void onClick(View v)
            {
                EditText email = (EditText) findViewById(R.id.emailText);
                EditText password = (EditText) findViewById(R.id.passwordText);

                mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("SignIn", "signInWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w("SignIn", "signInWithEmail:failed", task.getException());
                                    Toast.makeText(LoginScreen.this, R.string.auth_failed,
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginScreen.this, "Logging in!",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginScreen.this, MainActivity.class));
                                }
                            }
                        });
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
