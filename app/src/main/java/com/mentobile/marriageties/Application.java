package com.mentobile.marriageties;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Deepak Sharma on 8/2/2015.
 */
public class Application {

    public final static String URL = "http://www.mentobile.com/homzz/";
    public final static String SP_LOGIN_LOGOUT = "login_logout";

    public final static String URL_RELIGION = "http://matrimonialscript.in/ultimate_webservices/get-religion.php";
    public final static String URL_MOTHER_TONGUE = "http://matrimonialscript.in/ultimate_webservices/get-mothertongue.php";
    public final static String URL_CASTE = "http://matrimonialscript.in/ultimate_webservices/get-caste.php?religion_id=";
    public final static String URL_COUNTRY = "http://matrimonialscript.in/ultimate_webservices/get-country.php";
    public final static String URL_STATE = "http://matrimonialscript.in/ultimate_webservices/get-state.php?id=";
    public final static String URL_CITY = "http://matrimonialscript.in/ultimate_webservices/get-city.php?country_id=";
    public final static String URL_EDUCATION = "http://matrimonialscript.in/ultimate_webservices/get-education.php";
    public final static String URL_OCCUPATION = "http://matrimonialscript.in/ultimate_webservices/get-occupation.php";
    public final static String URL_DESIGNATION = "http://matrimonialscript.in/ultimate_webservices/get_designation.php";

    public final static String URL_REGISTRATION = "http://matrimonialscript.in/ultimate_webservices/final_regi_new.php";
    public final static String URL_LOGIN = "http://matrimonialscript.in/ultimate_webservices/login.php";


    public final static String PATH_IMAGE_FOLDER = URL + "images/";

    public static final String DATE_PATTERN1 = "dd.MM.yy";
    public static final String DATE_PATTERN2 = "yyyyMMdd";
    public static final String DATE_PATTERN3 = "dd/MM/yyyy";
    public static final String DATE_PATTERN4 = "dd-MM-yyyy";
    public static final String DATE_PATTERN5 = "MM/dd/yyyy";
    public static final String DATE_PATTERN6 = "ddMMyyyy";
    public static final String DATE_PATTERN7 = "yyyy-MM-dd";

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String TIME_PATTERN4 = "HH:mm:ss";//16:56:45
    public static final String TIME_PATTERN2 = "HH:mm:ss a";//16:46:56 AM
    public static final String TIME_PATTERN3 = "HH:mm:ss:S aa";//16:34:56:67678 PM
    public static final String TIME_PATTERN1 = "HHmmss";

    private static URL url = null;

    // validating email id
    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void setDataInSharedPreference(Activity activity, String spName, String key, String data) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, data);
        editor.commit();
    }

    public static String getDataFromSharedPreference(Activity activity, String spName, String key) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(spName, Context.MODE_PRIVATE);
        String val = (sharedPreferences.contains(key) ? sharedPreferences.getString(key, null) : null);
        return val;
    }

    public static void clearSharedPreferenceFile(Activity activity, String fileName) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }


    public static void removeFragment(Fragment fragment, Activity activity) {
        if (fragment != null && activity != null) {
            try {
                FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
                activity.getFragmentManager().popBackStack();
                transaction.detach(fragment).commit();
            } catch (Exception e) {

            }
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        boolean available = false;
        /** Getting the system's connectivity service */
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        /** Getting active network interface  to get the network's status */
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isAvailable())
            available = true;

        /** Returning the status of the network */
        Log.d("Application ", ":::::Network " + available);
        return available;
    }

    private static InputStream OpenHttpConnection(String urlString)
            throws IOException {
        InputStream in = null;
        int response = -1;

        url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");

        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception ex) {
            throw new IOException("Error connecting");
        }
        return in;
    }

    public static Bitmap DownloadImage(String URL) {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return bitmap;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void StartAsyncTaskInParallel(GetDataUsingWService task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            task.execute();
    }
}
