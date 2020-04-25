package com.boardgamebuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;

import java.io.IOException;


public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView username;
    private TextView email;
    private TextView userId;
    private Button signOutButton;
    private TextView changePicture;

    private GoogleSignInClient googleSignInClient;
    private GoogleSignInOptions gso;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImage = findViewById(R.id.profile_image);
        username = findViewById(R.id.user_name);
        email = findViewById(R.id.email);
        userId = findViewById(R.id.user_id);
        signOutButton = findViewById(R.id.sign_out_button);
        changePicture = findViewById(R.id.change_pictre);

        //Sign out user from app using singOutGoogle
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        signOutButton.setOnClickListener(v -> googleSignInClient.signOut().addOnSuccessListener(aVoid -> goToMainActivity()));

        //Choose picture from phone
        changePicture.setOnClickListener(v -> {
            Intent choosePicture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(choosePicture,1);
        });

        activateGameHistoryBtn();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Change profile picture for selected before using option "Change profile picture"
        if (requestCode == 1 && resultCode == RESULT_OK && data!=null) {
            Uri selectedImage = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                profileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //Function redirecting from Profile to Main Activity
    private void goToMainActivity() {
        startActivity(new Intent(ProfileActivity.this,MainActivity.class));
        finish();
    }
    //Function to set information in Profile for using information from Google account
    public void handleSignInResults (Task<GoogleSignInAccount> googleSignInResult){
        if (googleSignInResult.isComplete()){
            GoogleSignInAccount account = googleSignInResult.getResult();

            username.setText(account.getDisplayName());
            email.setText(account.getEmail());
            userId.setText(account.getId());

        }else {
            goToMainActivity();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        googleSignInClient.silentSignIn().addOnCompleteListener(this, task -> handleSignInResults(task));

    }

    //Function which activates Game History Activity using button in Profile
    public void activateGameHistoryBtn(){
        Button gameHistoryActivityBtn = findViewById(R.id.gameHistoryBtn);

        gameHistoryActivityBtn.setOnClickListener(v -> { //make an on click event
            Intent startIntent = new Intent(getApplicationContext(), GameHistoryActivity.class);//make new intent which launches the .xml file you want
            startActivity(startIntent);//start the activity
        });
    }
}
