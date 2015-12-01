package com.mentobile.utility;

/**
 * Created by Deepak Sharma on 7/27/2015.
 */

import android.util.Log;

import com.mentobile.marriageties.Application;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class WebService {

    final String TAG = "WebService";
    static JSONObject jObj = null;
    static String json = "";
    String url = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private JSONArray jsonArray;

    // constructor
    public WebService() {

    }

    public JSONObject makeHttpRequest(String url, List<NameValuePair> params) {

        // Making HTTP request
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            //Depends on your web service
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStreamReader isr = new InputStreamReader(httpEntity.getContent());
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "::::Error converting result " + e.toString());
        }
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "::::Error parsing data " + e.toString());
        }
        return jObj;
    }
}