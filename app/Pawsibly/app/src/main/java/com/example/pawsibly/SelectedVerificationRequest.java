package com.example.pawsibly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

public class SelectedVerificationRequest extends AppCompatActivity {

    Toolbar toolbar;
    TextView name, email, location, website, phone, bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_verification_request);

        toolbar = findViewById(R.id.toolbar);
        name = findViewById(R.id.verify_name_tv);
        email = findViewById(R.id.verify_email_tv);
        location = findViewById(R.id.verify_location_tv);
        website = findViewById(R.id.verify_website_tv);
        phone = findViewById(R.id.verify_phone_tv);
        bio = findViewById(R.id.verify_bio_tv);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            toolbar.setTitle(bundle.getString("SB_Name")+"'s Verification Request");
            name.setText("Name: " + bundle.getString("SB_Name"));
            email.setText("Email: " + bundle.getString("SB_Email"));
            location.setText("Location: " + bundle.getString("SB_Location"));
            website.setText("Website: " + bundle.getString("SB_Website"));
            phone.setText("Phone: " + bundle.getString("SB_Phone"));
            bio.setText("Bio: " + bundle.getString("SB_Bio"));
        }
    }
}
