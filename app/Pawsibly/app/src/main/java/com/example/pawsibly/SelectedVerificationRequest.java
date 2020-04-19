package com.example.pawsibly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;

public class SelectedVerificationRequest extends AppCompatActivity {

    Toolbar toolbar;
    TextView name, email, location, website, phone, bio;
    String sb_gid, admin_gid, adminID;

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

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .build();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(SelectedVerificationRequest.this);
        if (acct != null) {
            admin_gid = acct.getId();
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            toolbar.setTitle(bundle.getString("SB_Name")+"'s Verification Request");
            name.setText("Name: " + bundle.getString("SB_Name"));
            email.setText("Email: " + bundle.getString("SB_Email"));
            location.setText("Location: " + bundle.getString("SB_Location"));
            website.setText("Website: " + bundle.getString("SB_Website"));
            phone.setText("Phone: " + bundle.getString("SB_Phone"));
            bio.setText("Bio: " + bundle.getString("SB_Bio"));
            sb_gid = bundle.getString("SB_GID");
        }

        getJSON("https://cgi.sice.indiana.edu/~team53/admin.php?gid="+admin_gid);
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
                adminID = required_string;
            }
        }

        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
    public void VerifySBAccount(View view) {
        String str_gid = sb_gid;
        String str_aid = adminID;
        String type = "SBverify";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_gid,str_aid);
    }
    public void SBCancel(View view){
        Intent intent = new Intent(SelectedVerificationRequest.this, VerificationRequestListActivity.class);
        startActivity(intent);
    }
}
