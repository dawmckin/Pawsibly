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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class UserProfileActivity extends AppCompatActivity {

    final Context context = this;
    SeekBar filter_age, filter_radius;
    String personId, edit_gender, filter_type, filter_size, filter_gender;
    EditText lnameInput, fnameInput, phoneInput, bioInput;
    RadioButton radioSelectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .build();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(UserProfileActivity.this);
        if (acct != null) {
            personId = acct.getId();
        }
    }


    public void onEditUserProfile(final View view) {
        LayoutInflater li = LayoutInflater.from(context);
        final View edit_profile = li.inflate(R.layout.edit_user_profile_prompts, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(edit_profile);
        alert.setTitle("Edit Profile");

        alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final RadioGroup group = edit_profile.findViewById(R.id.radio_edtGender);
                int selectedId = group.getCheckedRadioButtonId();
                radioSelectedButton = (RadioButton) group.findViewById(selectedId);
                switch (selectedId) {
                    case R.id.radio_edtMale:
                        edit_gender="M";
                        break;
                    case R.id.radio_edtFemale:
                        edit_gender="F";
                        break;
                }
                lnameInput = edit_profile.findViewById(R.id.edtLastName_et);
                fnameInput = edit_profile.findViewById(R.id.edtFirstName_et);
                phoneInput = edit_profile.findViewById(R.id.edtPhone_et);
                bioInput = edit_profile.findViewById(R.id.edtBio_et);

                onEditUserProfilePos(edit_profile);
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.create().show();
    }

    public void onEditUserProfilePos(View view) {
        String str_lname = lnameInput.getText().toString();
        String str_fname = fnameInput.getText().toString();
        String str_gender = edit_gender;
        String str_phone = phoneInput.getText().toString();
        String str_bio = bioInput.getText().toString();
        String str_gid = personId;
        String type = "edit_profile";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_lname, str_fname, str_gender, str_phone, str_bio, str_gid);
    }

    public void onSetFilters(View view) {
        LayoutInflater li = LayoutInflater.from(context);
        final View set_filters = li.inflate(R.layout.set_filter_prompts, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(set_filters);
        alert.setTitle("Set Filters");

        alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                filter_age = set_filters.findViewById(R.id.filterAge_sb);
                filter_radius = set_filters.findViewById(R.id.filterRadius_sb);
                final RadioGroup group_type = set_filters.findViewById(R.id.radio_filterType);
                int selectedId_type = group_type.getCheckedRadioButtonId();
                radioSelectedButton = (RadioButton) group_type.findViewById(selectedId_type);
                switch (selectedId_type) {
                    case R.id.radio_filterDog:
                        filter_type = "Dog";
                        break;
                    case R.id.radio_filterCat:
                        filter_type = "Cat";
                        break;
                }
                final RadioGroup group_size = set_filters.findViewById(R.id.radio_filterSize);
                int selectedId_size = group_size.getCheckedRadioButtonId();
                radioSelectedButton = (RadioButton) group_type.findViewById(selectedId_size);
                switch (selectedId_size) {
                    case R.id.radio_filterLarge:
                        filter_size = "Large";
                        break;
                    case R.id.radio_filterMedium:
                        filter_size = "Medium";
                        break;
                    case R.id.radio_filterSmall:
                        filter_size = "Small";
                        break;
                }
                final RadioGroup group_Gender = set_filters.findViewById(R.id.radio_filterGender);
                int selectedId_gender = group_Gender.getCheckedRadioButtonId();
                radioSelectedButton = (RadioButton) group_type.findViewById(selectedId_gender);
                switch (selectedId_gender) {
                    case R.id.radio_filterMale:
                        filter_gender = "M";
                        break;
                    case R.id.radio_filterFemale:
                        filter_gender = "F";
                        break;
                }
                onSetFiltersPos(set_filters);
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.create().show();
    }

    public void onSetFiltersPos(View view) {
        String str_radius = filter_radius.getProgress()+"";
        String str_age = filter_age.getProgress()+"";
        String str_type = filter_type;
        String str_size = filter_size;
        String str_gender = filter_gender;
        String str_gid = personId;
        String type = "set_filters";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_age, str_radius, str_type, str_size, str_gender, str_gid);
    }

    public void onLogout(View view) {
        Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void onHome(View view) {
        Intent intent = new Intent(UserProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void onDeleteAccount(final View view) {
        LayoutInflater li = LayoutInflater.from(context);
        final View delete_account = li.inflate(R.layout.delete_account_prompts, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(delete_account);
        alert.setTitle("Alert!");
        alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onDeleteAccountPos(delete_account);
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.create().show();
    }

    public void onDeleteAccountPos(View view) {
        String str_gid = personId;
        String type = "delete_account";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_gid);

        Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
