package com.example.pawsibly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class SelectedAnimalUser extends AppCompatActivity {

    Toolbar toolbar;
    TextView name, gender, phone, bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_animal_user);

        toolbar = findViewById(R.id.toolbar);
        name = findViewById(R.id.name_tv);
        gender = findViewById(R.id.gender_tv);
        phone = findViewById(R.id.phone_tv);
        bio = findViewById(R.id.bio_tv);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            toolbar.setTitle(bundle.getString("Fullname") + "'s " + "Profile");
            name.setText("Name: " + bundle.getString("Fullname"));
            gender.setText("Gender: " + bundle.getString("Gender"));
            phone.setText("Phone #: " + bundle.getString("Phone"));
            bio.setText("Bio: " + bundle.getString("Bio"));


        }
    }
}
