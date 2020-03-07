package com.example.pawsibly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class HomeActivity extends AppCompatActivity {

    ImageView pet_profile_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pet_profile_pic = (ImageView) findViewById(R.id.pet_profile_iv);

    }

    public void OnLike(View view) {
        String type = "like";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type);
    }

    public void OnDislike(View view) {
        String type = "dislike";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type);
    }

    public void OnUserProfile(View view) {
        Intent intent = new Intent(HomeActivity.this, UserProfileActivity.class);
        startActivity(intent);
    }

    public void OnMessage(View view) {
        Intent intent = new Intent(HomeActivity.this, MessageActivity.class);
        startActivity(intent);
    }

    public void OnInfo(View view) {
        Intent intent = new Intent(HomeActivity.this, UserProfileActivity.class);
        startActivity(intent);
    }
}
