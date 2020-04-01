package com.example.pawsibly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class UserProfileActivity extends AppCompatActivity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

    }
    public void onEditUserProfile(View view) {
        LayoutInflater li = LayoutInflater.from(context);
        View edit_profile = li.inflate(R.layout.edit_user_profile_prompts, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(edit_profile);
        alert.setTitle("Edit Profile");

        final EditText lnameInput = edit_profile.findViewById(R.id.edtLastName_et);
        final EditText fnameInput = edit_profile.findViewById(R.id.edtLastName_et);
        final RadioGroup genderInput = edit_profile.findViewById(R.id.radio_edtGender);
        final EditText phoneInput = edit_profile.findViewById(R.id.edtPhone_et);
        final EditText bioInput = edit_profile.findViewById(R.id.edtBio_et);

        alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.create().show();
    }

    public void onSetFilters(View view) {
        LayoutInflater li = LayoutInflater.from(context);
        View set_filters = li.inflate(R.layout.set_filter_prompts, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(set_filters);
        alert.setTitle("Set Filters");

        final SeekBar filter_age = set_filters.findViewById(R.id.filterAge_sb);
        final SeekBar filter_radius = set_filters.findViewById(R.id.filterRadius_sb);
        final RadioGroup filter_type = set_filters.findViewById(R.id.radio_filterType);
        final RadioGroup filter_size = set_filters.findViewById(R.id.radio_filterSize);
        final RadioGroup filter_gender = set_filters.findViewById(R.id.radio_filterGender);

        alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.create().show();
    }

    public void onLogout(View view) {
        Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void onHome(View view) {
        Intent intent = new Intent(UserProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
