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


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReportedSBList extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reported_s_b_list);

        toolbar = findViewById(R.id.toolbar);
        listView = findViewById(R.id.listViewReportedSB);
        toolbar.setTitle("Reported Shelter/Breeders");

        getJSON("https://cgi.sice.indiana.edu/~team53/reported_sb.php");
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
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
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
        final String[] names = new String[jsonArray.length()];
        final String[] phones = new String[jsonArray.length()];
        final String[] dates = new String[jsonArray.length()];
        final String[] reports = new String[jsonArray.length()];



        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            names[i] = object.getString("name");
            phones[i] = object.getString("phone");
            dates[i] = object.getString("reported_date");
            reports[i] = object.getString("reported_reason");

        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ReportedSBList.this, SelectedSBReport.class);
                intent.putExtra("Report_Name", names[position]);
                intent.putExtra("Report_Phone", phones[position]);
                intent.putExtra("Report_Date", dates[position]);
                intent.putExtra("Report_Reason", reports[position]);

                startActivity(intent);
            }
        });
        listView.setAdapter(arrayAdapter);
    }
}
