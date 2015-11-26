package com.mentobile.marriageties;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mentobile.utility.CProgressDialog;
import com.mentobile.utility.DBHandler;
import com.mentobile.utility.WebService;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener, GetDataUsingWService.GetWebServiceData {

    private Button btnRegister;
    private final String TAG = "LoginActivity";
    private Button btnLogin;
    private Button btnSignup;
    private EditText edUserName;
    private EditText edPassword;
    private TextView tvForgetPass;
    private ArrayList<NameValuePair> listValue;
    WebService webService;
    private CProgressDialog cProgressDialog;
    public static LoginActivity loginActivity;
    public static int LOGIN_TYPE = 0;

    private final int SERVICE_ID_RELIGION = 0;
    private final int SERVICE_ID_MOTHER_TONGUE = 1;
    private final int SERVICE_ID_COUNTRY = 2;
    private final int SERVICE_ID_EDUCATION = 3;
    private final int SERVICE_ID_OCCUPATION = 4;
    private final int SERVICE_ID_DESIGNATION = 5;
    private final int SERVICE_ID_LOGIN = 6;
    ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Login Account");

        //       Get Data Religion data from Web Service
        if (SplashActivity.dbHandler.getDataFromTable(DBHandler.TBL_RELIGION).getCount() < 1) {
            GetDataUsingWService wsReligion = new GetDataUsingWService(this, Application.URL_RELIGION, SERVICE_ID_RELIGION, nameValuePairs, this);
            Application.StartAsyncTaskInParallel(wsReligion);
        }

        //       Get Data Mother Tongue data from Web Service

        if (SplashActivity.dbHandler.getDataFromTable(DBHandler.TBL_MOTHER_TONGUE).getCount() < 1) {
            GetDataUsingWService motherTongue = new GetDataUsingWService(this, Application.URL_MOTHER_TONGUE, SERVICE_ID_MOTHER_TONGUE, nameValuePairs, this);
            Application.StartAsyncTaskInParallel(motherTongue);
        }

        //       Get Data Country data from Web Service

        if (SplashActivity.dbHandler.getDataFromTable(DBHandler.TBL_COUNTRY).getCount() < 1) {
            GetDataUsingWService country = new GetDataUsingWService(this, Application.URL_COUNTRY, SERVICE_ID_COUNTRY, nameValuePairs, this);
            Application.StartAsyncTaskInParallel(country);
        }

        //       Get Data Education data from Web Service
        if (SplashActivity.dbHandler.getDataFromTable(DBHandler.TBL_EDUCATION).getCount() < 1) {
            GetDataUsingWService education = new GetDataUsingWService(this, Application.URL_EDUCATION, SERVICE_ID_EDUCATION, nameValuePairs, this);
            Application.StartAsyncTaskInParallel(education);
        }

        //       Get Data Occupation data from Web Service
        if (SplashActivity.dbHandler.getDataFromTable(DBHandler.TBL_OCCUPATION).getCount() < 1) {
            GetDataUsingWService occupation = new GetDataUsingWService(this, Application.URL_OCCUPATION, SERVICE_ID_OCCUPATION, nameValuePairs, this);
            Application.StartAsyncTaskInParallel(occupation);
        }

        //       Get Data Designation data from Web Service
        if (SplashActivity.dbHandler.getDataFromTable(DBHandler.TBL_DESIGNATION).getCount() < 1) {
            GetDataUsingWService designation = new GetDataUsingWService(this, Application.URL_DESIGNATION, SERVICE_ID_DESIGNATION, nameValuePairs, this);
            Application.StartAsyncTaskInParallel(designation);
        }

        loginActivity = this;
        cProgressDialog = new CProgressDialog(LoginActivity.this);
        btnLogin = (Button) findViewById(R.id.login_btn_login);
        btnLogin.setOnClickListener(this);
        btnSignup = (Button) findViewById(R.id.login_btn_register);
        btnSignup.setOnClickListener(this);

        edUserName = (EditText) findViewById(R.id.login_ed_username);
        edPassword = (EditText) findViewById(R.id.login_ed_password);

        tvForgetPass = (TextView) findViewById(R.id.login_tv_forgetpassword);
        tvForgetPass.setOnClickListener(this);

        webService = new WebService();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    JSONObject json = null;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn_login:
                String username = edUserName.getText().toString().trim();
                String password = edPassword.getText().toString().trim();
                if (username.length() < 1) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_username), Toast.LENGTH_SHORT).show();
                } else if (password.length() < 1) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_password), Toast.LENGTH_SHORT).show();
                } else {
                    listValue = new ArrayList<NameValuePair>();
                    listValue.add(new BasicNameValuePair("username", username));
                    listValue.add(new BasicNameValuePair("password", password));

                    GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, Application.URL_LOGIN, SERVICE_ID_LOGIN, listValue, this);
                    getDataUsingWService.execute();
                }
                break;
            case R.id.login_btn_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.login_tv_forgetpassword:
                forgetPassword();
                break;
        }
    }

    private void forgetPassword() {
        final Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.custom_forgotpassword);
        dialog.setTitle("Forgot Password");
        //dialog.getWindow().getAttributes().windowAnimations = R.style.PopupWindowAnimation;
        final EditText edForgotPassword = (EditText) dialog.findViewById(R.id.forgotpass_ed_email);
        Button btnSubmit = (Button) dialog.findViewById(R.id.forgot_btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edForgotPassword.getText().toString().trim();
                Log.d(TAG, ":::::Username " + username);
                if (username.length() < 1) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_username), Toast.LENGTH_SHORT).show();
                } else if (!Application.isValidEmail(username)) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_email_verify), Toast.LENGTH_SHORT).show();
                } else {
                    MyAsynchTask_ForgotPassword myAsynchTask_forgotPassword = new MyAsynchTask_ForgotPassword();
                    myAsynchTask_forgotPassword.execute(username, "4");
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private class MyAsynchTask_ForgotPassword extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cProgressDialog.setMessage(getString(R.string.progress_reset_password));
            cProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            listValue = new ArrayList<NameValuePair>();
            listValue.add(new BasicNameValuePair("email", params[0]));
            listValue.add(new BasicNameValuePair("type", params[1]));
            JSONObject json = webService.makeHttpRequest("signup", listValue);
            try {
                String success = json.getString("description");
                return success;
            } catch (JSONException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            cProgressDialog.hide();
            Log.d(TAG, "::::::Result " + result);
            Toast.makeText(getApplicationContext(), "" + result, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getWebServiceData_JSON(JSONObject jsonObject, int serviceCounter) {

        switch (serviceCounter) {

            case SERVICE_ID_RELIGION:
                try {
                    // Locate the NodeList name
                    JSONArray jsonarray = jsonObject.getJSONArray("religion");
                    for (int i = 0; i < jsonarray.length(); i++) {
                        jsonObject = jsonarray.getJSONObject(i);
                        // Populate spinner with country names
                        String id = jsonObject.getString("religion_id");
                        String name = jsonObject.getString("religion_name");
                        String status = jsonObject.getString("status");
                        ContentValues values = new ContentValues();
                        values.put("ID", id);
                        values.put("NAME", name);
                        values.put("STATUS", status);
                        SplashActivity.dbHandler.insertData(DBHandler.TBL_RELIGION, values);
                    }
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                break;

            case SERVICE_ID_MOTHER_TONGUE:
                try {
                    JSONArray jsonarray = jsonObject.getJSONArray("mtongue");
                    for (int i = 0; i < jsonarray.length(); i++) {
                        jsonObject = jsonarray.getJSONObject(i);
                        // Populate spinner with country names
                        String id = jsonObject.getString("mtongue_id");
                        String name = jsonObject.getString("mtongue_name");
                        String status = jsonObject.getString("status");
                        ContentValues values = new ContentValues();
                        values.put("ID", id);
                        values.put("NAME", name);
                        values.put("STATUS", status);
                        SplashActivity.dbHandler.insertData(DBHandler.TBL_MOTHER_TONGUE, values);
                    }

                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                break;
            case SERVICE_ID_COUNTRY:
                try {
                    JSONArray jsonarray = jsonObject.getJSONArray("Country");
                    for (int i = 0; i < jsonarray.length(); i++) {
                        jsonObject = jsonarray.getJSONObject(i);
                        // Populate spinner with country names
                        String id = jsonObject.getString("country_id");
                        String name = jsonObject.getString("country_name");
                        String status = jsonObject.getString("status");
                        ContentValues values = new ContentValues();
                        values.put("ID", id);
                        values.put("NAME", name);
                        values.put("STATUS", status);
                        SplashActivity.dbHandler.insertData(DBHandler.TBL_COUNTRY, values);
                    }
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                break;
            case SERVICE_ID_EDUCATION:
                try {
                    JSONArray jsonarray = jsonObject.getJSONArray("edu");
                    Log.d(TAG, "::::::Array  " + jsonarray);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        jsonObject = jsonarray.getJSONObject(i);
                        // Populate spinner with country names
                        String id = jsonObject.getString("edu_id");
                        String name = jsonObject.getString("education_name");
                        String status = jsonObject.getString("status");
                        ContentValues values = new ContentValues();
                        values.put("ID", id);
                        values.put("NAME", name);
                        values.put("STATUS", status);
                        SplashActivity.dbHandler.insertData(DBHandler.TBL_EDUCATION, values);
                    }
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                break;
            case SERVICE_ID_OCCUPATION:
                try {
                    JSONArray jsonarray = jsonObject.getJSONArray("ocup");
                    Log.d(TAG, "::::::Array  " + jsonarray);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        jsonObject = jsonarray.getJSONObject(i);
                        // Populate spinner with country names
                        String id = jsonObject.getString("ocp_id");
                        String name = jsonObject.getString("ocp_name");
                        Log.d(TAG, "::::::Id " + id + " Name " + name);
                        ContentValues values = new ContentValues();
                        values.put("ID", id);
                        values.put("NAME", name);
                        values.put("STATUS", "");
                        SplashActivity.dbHandler.insertData(DBHandler.TBL_OCCUPATION, values);
                    }
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                break;
            case SERVICE_ID_DESIGNATION:
                try {
                    JSONArray jsonarray = jsonObject.getJSONArray("designation");
                    for (int i = 0; i < jsonarray.length(); i++) {
                        jsonObject = jsonarray.getJSONObject(i);
                        // Populate spinner with country names
                        String id = jsonObject.getString("desg_id");
                        String name = jsonObject.getString("desg_name");
                        String status = jsonObject.getString("status");
                        ContentValues values = new ContentValues();
                        values.put("ID", id);
                        values.put("NAME", name);
                        values.put("STATUS", status);
                        SplashActivity.dbHandler.insertData(DBHandler.TBL_DESIGNATION, values);
                    }
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                break;

            case SERVICE_ID_LOGIN:
                try {
                    JSONArray jsonarray = jsonObject.getJSONArray("login");
                    String resCode = jsonarray.get(0).toString();
                    Toast.makeText(getApplicationContext(), "" + resCode + "   " + jsonarray.get(1), Toast.LENGTH_SHORT).show();
                    if (resCode.equals("1")) { // Success
                        Application.setDataInSharedPreference(LoginActivity.this, Application.SP_LOGIN_LOGOUT, "matri_id",
                                edUserName.getText().toString().trim());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }
}
