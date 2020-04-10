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
    String personId, gender;
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
                        gender="M";
                        break;
                    case R.id.radio_edtFemale:
                        gender="F";
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
        String str_gender = gender;
        String str_phone = phoneInput.getText().toString();
        String str_bio = bioInput.getText().toString();
        String str_gid = personId;
        String type = "edit_profile";
        Toast.makeText(context,str_gid,Toast.LENGTH_SHORT).show();
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_lname, str_fname, str_gender, str_phone, str_bio, str_gid);
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
