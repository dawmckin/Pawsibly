package com.example.pawsibly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class report_page extends AppCompatActivity {

    Toolbar toolbar;
    TextView report_tv;
    EditText report_et;
    String gid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_page);

        toolbar = findViewById(R.id.toolbar);
        report_tv = findViewById(R.id.report_tv);
        report_et = findViewById(R.id.report_et);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            toolbar.setTitle("Report User");
            gid = bundle.getString("gid");
        }
    }
    public void SubmitReport(View view){
        String str_reportReason = report_et.getText().toString();
        String str_gid = gid;
        String type = "submit_report";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_reportReason, str_gid);

        Intent intent = new Intent(report_page.this, AnimalList.class);
        startActivity(intent);
    }

}