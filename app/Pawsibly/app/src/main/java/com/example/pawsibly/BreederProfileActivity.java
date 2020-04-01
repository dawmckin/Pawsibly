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

public class BreederProfileActivity extends AppCompatActivity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_profile);
    }

    public void onAddAnimal(View view) {
        LayoutInflater li = LayoutInflater.from(context);
        View add_animal = li.inflate(R.layout.add_animal_prompts, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(add_animal);
        alert.setTitle("Add Animal");

        final EditText add_name = add_animal.findViewById(R.id.addName_et);
        final EditText add_dob = add_animal.findViewById(R.id.addDob_et);
        final RadioGroup add_gender = add_animal.findViewById(R.id.radio_addGender);
        final RadioGroup add_type = add_animal.findViewById(R.id.radio_addType);
        final RadioGroup add_size = add_animal.findViewById(R.id.radio_addSize);
        final EditText add_bio = add_animal.findViewById(R.id.addBio_et);
        final EditText add_picture = add_animal.findViewById(R.id.addPicture_et);

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
        Intent intent = new Intent(BreederProfileActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
