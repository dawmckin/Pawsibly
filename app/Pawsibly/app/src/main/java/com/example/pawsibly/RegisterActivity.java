package com.example.pawsibly;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.Calendar;
import java.util.ResourceBundle;

public class RegisterActivity extends AppCompatActivity {

    EditText lname, fname, dob, phone;
    String personEmail, personId, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        lname = (EditText) findViewById(R.id.lname_et);
        fname = (EditText) findViewById(R.id.fname_et);
        dob = (EditText) findViewById(R.id.dob_et);
        phone = (EditText) findViewById(R.id.phone_et);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .build();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(RegisterActivity.this);
        if (acct != null) {
            personEmail = acct.getEmail();
            personId = acct.getId();
        }

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_male:
                if (checked) {
                    gender = "M";
                }
                break;
            case R.id.radio_female:
                if (checked) {
                    gender = "F";
                }
                break;
        }
    }

    public void OnReg(View view) {
        String str_lname = lname.getText().toString();
        String str_fname = fname.getText().toString();
        String str_dob = dob.getText().toString();
        String str_gender = gender;
        String str_email = personEmail;
        String str_phone = phone.getText().toString();
        String str_gid = personId;
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_lname, str_fname, str_dob, str_gender,str_email, str_phone, str_gid);

        Intent intent = new Intent(RegisterActivity.this, LocationActivity.class);
        startActivity(intent);
    }
}
