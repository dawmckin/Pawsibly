package com.example.pawsibly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class RegisterSBActivity extends AppCompatActivity {

    EditText name, manager, dob, location, website, phone;
    String personEmail, personId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_s_b);

        name = (EditText) findViewById(R.id.orgName_et);
        manager = (EditText) findViewById(R.id.manager_et);
        dob = (EditText) findViewById(R.id.dob_sb_et);
        location = (EditText) findViewById(R.id.location_et);
        website = (EditText) findViewById(R.id.website_et);
        phone = (EditText) findViewById(R.id.phone_et);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .build();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(RegisterSBActivity.this);
        if (acct != null) {
            personEmail = acct.getEmail();
            personId = acct.getId();
        }
    }

    public void OnRegSB(View view) {
        String str_name = name.getText().toString();
        String str_manager = manager.getText().toString();
        String str_dob = dob.getText().toString();
        String str_email = personEmail;
        String str_location = location.getText().toString();
        String str_website = website.getText().toString();
        String str_phone = phone.getText().toString();
        String str_gid = personId;
        String type = "register_sb";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_name, str_manager, str_dob, str_email ,str_location, str_website, str_phone, str_gid);

        Intent intent = new Intent(RegisterSBActivity.this, BreederProfileActivity.class);
        startActivity(intent);
    }
}
