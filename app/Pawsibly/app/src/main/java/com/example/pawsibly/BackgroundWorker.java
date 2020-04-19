package com.example.pawsibly;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    BackgroundWorker (Context ctx) {context = ctx;}
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String register_url = "http://cgi.sice.indiana.edu/~team53/register.php";
        String register_sb_url = "http://cgi.sice.indiana.edu/~team53/register_sb.php";
        String edit_user_info_url = "http://cgi.sice.indiana.edu/~team53/edit_user_info.php";
        String edit_sb_info_url = "http://cgi.sice.indiana.edu/~team53/edit_sb_info.php";
        String set_filters_url = "http://cgi.sice.indiana.edu/~team53/set_filters.php";
        String terminate_user_url = "http://cgi.sice.indiana.edu/~team53/terminate_user.php";
        String terminate_sb_url = "http://cgi.sice.indiana.edu/~team53/terminate_sb.php";
        String verify_sb_url = "http://cgi.sice.indiana.edu/~team53/verify_sb.php";
        String add_animal_url = "http://cgi.sice.indiana.edu/~team53/add_animal.php";
        String submit_report_url = "http://cgi.sice.indiana.edu/~team53/submit_report.php";
        String get_verified_url = "http://cgi.sice.indiana.edu/~team53/get_verified.php";
        if (type.equals("register")) {
            try {
                String lname = params[1];
                String fname = params[2];
                String dob = params[3];
                String gender = params[4];
                String email = params[5];
                String phone = params[6];
                String gid = params[7];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("lastname","UTF-8")+"="+URLEncoder.encode(lname,"UTF-8")+"&"+URLEncoder.encode("firstname","UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")+"&"+URLEncoder.encode("dob","UTF-8")+"="+URLEncoder.encode(dob,"UTF-8")+"&"+URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8")+"&"+URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"+URLEncoder.encode("gid","UTF-8")+"="+URLEncoder.encode(gid,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("register_sb")) {
            try {
                String name = params[1];
                String manager = params[2];
                String dob = params[3];
                String email = params[4];
                String location = params[5];
                String website = params[6];
                String phone = params[7];
                String gid = params[8];
                URL url = new URL(register_sb_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+URLEncoder.encode("manager","UTF-8")+"="+URLEncoder.encode(manager,"UTF-8")+"&"+URLEncoder.encode("dob","UTF-8")+"="+URLEncoder.encode(dob,"UTF-8")+"&"+URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+URLEncoder.encode("location","UTF-8")+"="+URLEncoder.encode(location,"UTF-8")+"&"+URLEncoder.encode("website","UTF-8")+"="+URLEncoder.encode(website,"UTF-8")+"&"+URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"+URLEncoder.encode("gid","UTF-8")+"="+URLEncoder.encode(gid,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("edit_profile")) {
            try {
                String lname = params[1];
                String fname = params[2];
                String gender = params[3];
                String phone = params[4];
                String bio = params[5];
                String gid = params[6];
                URL url = new URL(edit_user_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("lastname","UTF-8")+"="+URLEncoder.encode("'"+lname+"'","UTF-8")+"&"+URLEncoder.encode("firstname","UTF-8")+"="+URLEncoder.encode("'"+fname+"'","UTF-8")+"&"+URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode("'"+gender+"'","UTF-8")+"&"+URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode("'"+phone+"'","UTF-8")+"&"+URLEncoder.encode("bio","UTF-8")+"="+URLEncoder.encode("'"+bio+"'","UTF-8")+"&"+URLEncoder.encode("gid","UTF-8")+"="+URLEncoder.encode(gid,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("edit_sb_profile")) {
            try {
                String name = params[1];
                String manager = params[2];
                String location = params[3];
                String website = params[4];
                String phone = params[5];
                String bio = params[6];
                String gid = params[7];
                URL url = new URL(edit_sb_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode("'"+name+"'","UTF-8")+"&"+URLEncoder.encode("manager","UTF-8")+"="+URLEncoder.encode("'"+manager+"'","UTF-8")+"&"+URLEncoder.encode("location","UTF-8")+"="+URLEncoder.encode("'"+location+"'","UTF-8")+"&"+URLEncoder.encode("website","UTF-8")+"="+URLEncoder.encode("'"+website+"'","UTF-8")+"&"+URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode("'"+phone+"'","UTF-8")+"&"+URLEncoder.encode("bio","UTF-8")+"="+URLEncoder.encode("'"+bio+"'","UTF-8")+"&"+URLEncoder.encode("gid","UTF-8")+"="+URLEncoder.encode(gid,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("add_animal")) {
            try {
                String name = params[1];
                String dob = params[2];
                String gender = params[3];
                String animal_type = params[4];
                String size = params[5];
                String bio = params[6];
                String picture = params[7];
                String sbid = params[8];
                URL url = new URL(add_animal_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+URLEncoder.encode("dob","UTF-8")+"="+URLEncoder.encode(dob,"UTF-8")+"&"+URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8")+"&"+URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode(animal_type,"UTF-8")+"&"+URLEncoder.encode("size","UTF-8")+"="+URLEncoder.encode(size,"UTF-8")+"&"+URLEncoder.encode("'"+"bio"+"'","UTF-8")+"="+URLEncoder.encode(bio,"UTF-8")+"&"+URLEncoder.encode("picture","UTF-8")+"="+URLEncoder.encode(picture,"UTF-8")+"&"+URLEncoder.encode("sbid","UTF-8")+"="+URLEncoder.encode(sbid,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("set_filters")) {
            try {
                String filter_age = params[1];
                String filter_radius = params[2];
                String filter_type = params[3];
                String filter_size = params[4];
                String filter_gender = params[5];
                String gid = params[6];
                URL url = new URL(set_filters_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(filter_age, "UTF-8") + "&" + URLEncoder.encode("radius", "UTF-8") + "=" + URLEncoder.encode(filter_radius, "UTF-8") + "&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(filter_type, "UTF-8") + "&" + URLEncoder.encode("size", "UTF-8") + "=" + URLEncoder.encode(filter_size, "UTF-8") + "&" + URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(filter_gender, "UTF-8") + "&" + URLEncoder.encode("gid", "UTF-8") + "=" + URLEncoder.encode(gid, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("get_verified")) {
            try {
                String gid = params[1];
                URL url = new URL(get_verified_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("gid","UTF-8")+"="+URLEncoder.encode(gid,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (type.equals("terminate")) {
            try {
                String gid = params[1];
                URL url = new URL(terminate_user_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("gid","UTF-8")+"="+URLEncoder.encode(gid,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("SBterminate")) {
            try {
                String gid = params[1];
                URL url = new URL(terminate_sb_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("gid","UTF-8")+"="+URLEncoder.encode(gid,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }  else if (type.equals("SBverify")) {
            try {
                String gid = params[1];
                String aid = params[2];
                URL url = new URL(verify_sb_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("gid", "UTF-8") + "=" + URLEncoder.encode(gid, "UTF-8") + "&" + URLEncoder.encode("aid", "UTF-8") + "=" + URLEncoder.encode(aid, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("submit_report")) {
            try {
                String reportReason = params[1];
                String gid = params[2];
                URL url = new URL(submit_report_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("reported_reason","UTF-8")+"="+URLEncoder.encode("'"+ reportReason +"'","UTF-8") + "&" + URLEncoder.encode("gid", "UTF-8") + "=" + URLEncoder.encode(gid, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

}
