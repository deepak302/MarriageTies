package com.mentobile.marriageties;

import android.app.Activity;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;

import com.mentobile.utility.CProgressDialog;
import com.mentobile.utility.WebService;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 11/11/2015.
 */

public class GetDataUsingWService extends AsyncTask<String, String, JSONObject> {

    private Activity activity;
    private ArrayList<Object> arrayList = new ArrayList<>();
    private ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
    CProgressDialog cProgressDialog;
    private String url;
    private WebService webService;
    GetWebServiceData getWebServiceData;
    private int serviceCounter;
    private String message = "Preparing...";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public interface GetWebServiceData {
        void getWebServiceData_JSON(JSONObject jsonObject, int serviceCounter);
    }

    public GetDataUsingWService(Activity activity, String url, int serviceCounter, ArrayList<NameValuePair> nameValuePairs, GetWebServiceData getWebServiceData) {
        this.activity = activity;
        this.nameValuePairs = nameValuePairs;
        this.url = url;
        this.serviceCounter = serviceCounter;
        this.getWebServiceData = getWebServiceData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        cProgressDialog = new CProgressDialog(activity);
        cProgressDialog.setMessage(getMessage());
        cProgressDialog.setCanceledOnTouchOutside(false);
        cProgressDialog.setCancelable(false);
        cProgressDialog.show();
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        webService = new WebService();
        JSONObject json = webService.makeHttpRequest(url, nameValuePairs);
        return json;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        cProgressDialog.hide();
        getWebServiceData.getWebServiceData_JSON(result, serviceCounter);
    }
}
