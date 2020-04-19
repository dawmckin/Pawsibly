package com.example.pawsibly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VerificationRequestListActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification_request_list);

        toolbar = findViewById(R.id.toolbar);
        listView = findViewById(R.id.listViewVerify);
        toolbar.setTitle("Verification Requests");

        getJSON("https://cgi.sice.indiana.edu/~team53/verification_request.php");
    }

    private void getJSON(final String urlWebService) {
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    loadIntoListView(s);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        final String[] gids = new String[jsonArray.length()];
        final String[] requests = new String[jsonArray.length()];
        final String[] emails = new String[jsonArray.length()];
        final String[] locations = new String[jsonArray.length()];
        final String[] websites = new String[jsonArray.length()];
        final String[] phones = new String[jsonArray.length()];
        final String[] bios = new String[jsonArray.length()];


        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            gids[i] = object.getString("gid");
            requests[i] = object.getString("name");
            emails[i] = object.getString("email");
            locations[i] = object.getString("location");
            websites[i] = object.getString("website");
            phones[i] = object.getString("phone");
            bios[i] = object.getString("bio");
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, requests);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VerificationRequestListActivity.this, SelectedVerificationRequest.class);
                intent.putExtra("SB_GID", gids[position]);
                intent.putExtra("SB_Name", requests[position]);
                intent.putExtra("SB_Email", emails[position]);
                intent.putExtra("SB_Location", locations[position]);
                intent.putExtra("SB_Website", websites[position]);
                intent.putExtra("SB_Phone", phones[position]);
                intent.putExtra("SB_Bio", bios[position]);

                startActivity(intent);
            }
        });
        listView.setAdapter(arrayAdapter);
    }
}
