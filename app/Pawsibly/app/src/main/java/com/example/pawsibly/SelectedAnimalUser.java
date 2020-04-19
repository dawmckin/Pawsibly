package com.example.pawsibly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SelectedAnimalUser extends AppCompatActivity {

    Toolbar toolbar;
    TextView name, gender, phone, bio;
    String gid, rid, pid;

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
            gid = bundle.getString("gid");
            pid = bundle.getString("pid");
        }

        getJSON("https://cgi.sice.indiana.edu/~team53/reg_user_id.php?gid="+gid);

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
                rid = required_string;
                /*Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();*/
            }
        }

        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    public void ReportUser(View view) {
        Intent intent = new Intent(SelectedAnimalUser.this, report_page.class);
        intent.putExtra("gid", gid);
        startActivity(intent);
    }

    public void onMessageUser(View view) {
        String str_pid = pid;
        String str_rid = rid;
        String type = "create_match";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_pid, str_rid);
        Intent intent = new Intent(SelectedAnimalUser.this, Messaging.class);
        startActivity(intent);
    }

    public void onProfile(View view) {
        Intent intent = new Intent(SelectedAnimalUser.this, BreederProfileActivity.class);
        startActivity(intent);
    }
}
