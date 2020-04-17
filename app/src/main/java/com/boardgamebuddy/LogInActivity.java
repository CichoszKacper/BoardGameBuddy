package com.boardgamebuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;

import java.time.Instant;


public class LogInActivity extends AppCompatActivity {

    SignInButton signInButton;
    public GoogleSignInClient mGoogleSignInClient;
    private static final int SIGN_IN = 1;
    public GoogleSignInResult result;
    public EditText usernameEditText;
    public EditText passwordEditText;


    public void signIn (View view) {
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        if (usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")){
            Toast.makeText(this, "A username and password are required", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent login = new Intent(LogInActivity.this,ProfileActivity.class);
            startActivity(login);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton = findViewById(R.id.google_Sign_In);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(intent,SIGN_IN);

            }
        });

        // Build link to register when no account created before
        TextView registerLink = findViewById(R.id.registerBtn);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        //Build log in button option
        final EditText usernameEditText = findViewById(R.id.usernameEditText);
        final EditText passwordEditText = findViewById(R.id.passwordEditText);

        Button logInButton = findViewById(R.id.logInBtn);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")){
                    Toast.makeText(LogInActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent login = new Intent(LogInActivity.this,ProfileActivity.class);
                    startActivity(login);
                }
            }
        });
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        setContentView(R.layout.activity_log_in);
//
//        // Check for existing Google Sign In account, if the user is already signed in
//        // the GoogleSignInAccount will be non-null.
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        if (account!=null){
//            startActivity(new Intent(LogInActivity.this, ProfileActivity.class));
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN){
            result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()){
                startActivity(new Intent(LogInActivity.this, ProfileActivity.class));

            }else {
                Toast.makeText(this, "Login unsuccessful", Toast.LENGTH_SHORT).show();
            }
        }
    }



}
