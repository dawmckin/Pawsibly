package com.example.pawsibly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);
    }

    public void onRegAsUser(View view) {
        Intent intent = new Intent(ChooseAccountActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onRegAsShelterBreeder(View view) {
        Intent intent = new Intent(ChooseAccountActivity.this, RegisterSBActivity.class);
        startActivity(intent);
    }
}
