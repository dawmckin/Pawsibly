package com.example.pawsibly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class LoginActivity extends AppCompatActivity {

    SignInButton signinbtn;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 1;
    String personEmail, personId, googleID_RU, googleID_SB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        signinbtn = findViewById(R.id.sign_in_button);
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(LoginActivity.this);
        if (acct != null) {
            personEmail = acct.getEmail();
            personId = acct.getId();
        }

        getJSONUsers("https://cgi.sice.indiana.edu/~team53/login.php?gid="+ personId);
        getJSONShelterBreeder("https://cgi.sice.indiana.edu/~team53/login_sb.php?gid="+ personId);
    }

    private void getJSONUsers(final String urlWebServices) {

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
                googleID_RU = s;
                /*Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();*/
            }
        }

        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void getJSONShelterBreeder(final String urlWebServices) {

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
                googleID_SB = s;
                /*Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();*/
            }
        }

        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            if (googleID_RU.contains(personId)) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            } else if (googleID_SB.contains(personId)) {
                Intent intent = new Intent(LoginActivity.this, BreederProfileActivity.class);
                startActivity(intent);
            } else {
                acctRegister();
            }

        } catch (ApiException e) {
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void acctRegister() {
        Intent intent = new Intent(LoginActivity.this, ChooseAccountActivity.class);
        startActivity(intent);
    }

}
