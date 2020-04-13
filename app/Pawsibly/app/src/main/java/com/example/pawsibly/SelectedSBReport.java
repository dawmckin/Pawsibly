package com.example.pawsibly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

public class SelectedSBReport extends AppCompatActivity {

    Toolbar toolbar;
    TextView name, date, phone, reportReason;
    String reported_gid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_s_b_report);

        toolbar = findViewById(R.id.toolbar);
        name = findViewById(R.id.report_name_tv);
        phone = findViewById(R.id.report_phone_tv);
        date = findViewById(R.id.report_date_tv);
        reportReason = findViewById(R.id.report_reason_tv);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            toolbar.setTitle("Report For " + bundle.getString("Report_Name"));
            name.setText("Name: " + bundle.getString("Report_Name"));
            phone.setText("Phone #: " + bundle.getString("Report_Phone"));
            date.setText("Date Reported: " + bundle.getString("Report_Date"));
            reportReason.setText("Reason For Report: " + bundle.getString("Report_Reason"));
            reported_gid = bundle.getString("Report_GID");
        }
    }
    public void TerminateSBAccount(View view) {
        String str_gid = reported_gid;
        String type = "SBterminate";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_gid);
    }
    public void onSBCancel(View view){
        Intent intent = new Intent(SelectedSBReport.this, ReportedSBList.class);
        startActivity(intent);
    }
}
