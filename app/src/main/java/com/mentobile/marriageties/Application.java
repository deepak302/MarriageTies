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

    public final static String URL_WEB = "http://matrimonialscript.in/ultimate_webservices/";

    public final static String URL_REGISTRATION = URL_WEB + "final_regi.php";
    public final static String URL_LOGIN = URL_WEB + "login.php";
    public final static String URL_RELIGION = URL_WEB + "get-religion.php";
    public final static String URL_MOTHER_TONGUE = URL_WEB + "get-mothertongue.php";
    public final static String URL_CASTE_MULTIPLE = URL_WEB + "get_multiple_cast.php?religion_id=";
    public final static String URL_COUNTRY = URL_WEB + "get-country.php";
    public final static String URL_STATE = URL_WEB + "get_multiple_state.php?country_id=";
    public final static String URL_CITY = URL_WEB + "get_multiple_city.php?country_id=";
    public final static String URL_EDUCATION = URL_WEB + "get-education.php";
    public final static String URL_OCCUPATION = URL_WEB + "get-occupation.php";
    public final static String URL_DESIGNATION = URL_WEB + "get_designation.php";
    public final static String URL_PART_MATCHES = URL_WEB + "custom_match.php";
    public final static String URL_SEARCH = URL_WEB + "search.php";
    public final static String URL_VIEW_PROFILE = URL_WEB + "view_profile.php";
    public final static String URL_PHOTO_CROP = URL_WEB + "photos/";
    public final static String URL_PHOTO_BIG = URL_WEB + "photos_big/";
    public final static String URL_UPLOAD_SMALL = URL_WEB + "fileupload.php";
    public final static String URL_UPLOAD_BIG = URL_WEB + "fileupload1.php";
    public final static String URL_SUCCESS_STORY = URL_WEB + "get-successstory.php";


    public final static String URL_PROFILE_SHORTED = URL_WEB + "get_shortlist.php";
    public final static String URL_ADD_SHORTED = URL_WEB + "add_to_short_list.php?user_id=NE37&short_id=NE1";
    public final static String URL_REMOVE_SHORTED = URL_WEB + "remove_shortlist.php?user_id=NE37&short_remove_id=NE1";

    public final static String URL_PROFILE_BLOCKED = URL_WEB + "get_blocklist.php?user_id=NE37";
    public final static String URL_ADD_BLOCKED = URL_WEB + "add_to_block_list.php?user_id=NE37&block_id=NE1";
    public final static String URL_REMOVE_BLOCKED = URL_WEB + "remove_blocklist.php?user_id=NE37&block_remove_id=NE1";


    public final static String URLM_SEARCH = "http://www.marriageties.com/appwebservices/search.php";
    public final static String URLM_PROFILE_SHORTED = "http://www.marriageties.com/appwebservices/get_shortlist.php";
    public final static String URLM_ADD_SHORTED = "http://www.marriageties.com/appwebservices/add_to_short_list.php";
    public final static String URLM_REMOVE_SHORTED = "http://www.marriageties.com/appwebservices/remove_shortlist.php";


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

    public static final int SERVICE_ID_RELIGION = 1;
    public static final int SERVICE_ID_CASTE = 2;
    public static final int SERVICE_ID_COUNTRY = 3;
    public static final int SERVICE_ID_STATE = 4;
    public static final int SERVICE_ID_CITY = 5;

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
