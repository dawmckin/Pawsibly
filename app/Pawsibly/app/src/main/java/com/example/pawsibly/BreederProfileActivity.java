package com.example.pawsibly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BreederProfileActivity extends AppCompatActivity {

    final Context context = this;
    String personId, breederID, add_gender, add_type, add_size;
    EditText nameInput, bioInput, dobInput, pictureInput;
    RadioButton radioSelectedButtonGender, radioSelectedButtonType, radioSelectedButtonSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_profile);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .build();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(BreederProfileActivity.this);
        if (acct != null) {
            personId = acct.getId();
        }

        getJSON("https://cgi.sice.indiana.edu/~team53/shelter_breeder.php?gid="+ personId);

    }

    private void getJSON(final String urlWebServices) {

        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebServices);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return e.toString().trim();
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                 String required_string = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
                 breederID = required_string;
            }
        }

        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    public void onAddAnimal(View view) {
        LayoutInflater li = LayoutInflater.from(context);
        final View add_animal = li.inflate(R.layout.add_animal_prompts, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(add_animal);
        alert.setTitle("Add Animal");

        alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final RadioGroup gender = add_animal.findViewById(R.id.radio_addGender);
                int selectedIdGender = gender.getCheckedRadioButtonId();
                radioSelectedButtonGender = (RadioButton) gender.findViewById(selectedIdGender);
                switch (selectedIdGender) {
                    case R.id.radio_addMale:
                        add_gender="M";
                        break;
                    case R.id.radio_addFemale:
                        add_gender="F";
                        break;
                }

                final RadioGroup type = add_animal.findViewById(R.id.radio_addType);
                int selectedIdType = type.getCheckedRadioButtonId();
                radioSelectedButtonType = (RadioButton) type.findViewById(selectedIdType);
                switch (selectedIdType) {
                    case R.id.radio_addCat:
                        add_type="Cat";
                        break;
                    case R.id.radio_addDog:
                        add_type="Dog";
                        break;
                }

                final RadioGroup size = add_animal.findViewById(R.id.radio_addSize);
                int selectedIdSize = size.getCheckedRadioButtonId();
                radioSelectedButtonSize = (RadioButton) size.findViewById(selectedIdSize);
                switch (selectedIdSize) {
                    case R.id.radio_addLarge:
                        add_size="Large";
                        break;
                    case R.id.radio_addMedium:
                        add_size="Meduim";
                        break;
                    case R.id.radio_addSmall:
                        add_size="Small";
                        break;
                }
                nameInput = add_animal.findViewById(R.id.addName_et);
                dobInput = add_animal.findViewById(R.id.addDob_et);
                bioInput = add_animal.findViewById(R.id.addBio_et);
                pictureInput = add_animal.findViewById(R.id.addPicture_et);

                onAddAnimalPos(add_animal);
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.create().show();
    }

    public void onAddAnimalPos(View view) {
        String str_name = nameInput.getText().toString();
        String str_dob = dobInput.getText().toString();
        String str_gender = add_gender;
        String str_type = add_type;
        String str_size = add_size;
        String str_bio = pictureInput.getText().toString();
        String str_picture = bioInput.getText().toString();
        String str_sbid = breederID;
        String type = "add_animal";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_name, str_dob, str_gender, str_type, str_size, str_bio, str_picture, str_sbid);
    }

    public void onLogout(View view) {
        Intent intent = new Intent(BreederProfileActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void onAnimalList(View view) {
        Intent intent = new Intent(BreederProfileActivity.this, AnimalList.class);
        startActivity(intent);
    }

    public void onMessaging(View view) {
        Intent intent = new Intent(BreederProfileActivity.this, Messaging.class);
        startActivity(intent);
    }
}
