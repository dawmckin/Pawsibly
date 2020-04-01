package com.example.pawsibly;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AdminHomeActivity extends AppCompatActivity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_homepage);
    }
    public void onLogout(View view) {
        Intent intent = new Intent(AdminHomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
