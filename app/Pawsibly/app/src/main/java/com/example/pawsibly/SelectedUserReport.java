package com.example.pawsibly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

public class SelectedUserReport extends AppCompatActivity {

    Toolbar toolbar;
    TextView name, date, reportReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_user_report);

        toolbar = findViewById(R.id.toolbar);
        name = findViewById(R.id.report_name_tv);
        date = findViewById(R.id.report_date_tv);
        reportReason = findViewById(R.id.report_reason_tv);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            toolbar.setTitle("Report For " + bundle.getString("Report_FirstName") +" " + bundle.getString("Report_LastName"));
            name.setText("Name: " + bundle.getString("Report_FirstName")+ " " + bundle.getString("Report_LastName"));
            date.setText("Date Reported: " + bundle.getString("Report_Date"));
            reportReason.setText("Reason For Report: " + bundle.getString("Report_Reason"));


        }
    }
}
