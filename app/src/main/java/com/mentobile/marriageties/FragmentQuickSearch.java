package com.mentobile.marriageties;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.mentobile.utility.CProgressDialog;
import com.mentobile.utility.DBHandler;
import com.mentobile.utility.MultiSpinner;
import com.mentobile.utility.WebService;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentQuickSearch extends Fragment implements AdapterView.OnItemSelectedListener, MultiSpinner.MultiSpinnerListener, GetDataUsingWService.GetWebServiceData {

    private static final String TAG = "FragmentQuickSearch";

    private Spinner spnGender;
    private Spinner spnStartAge;
    private Spinner spnEndAge;
    private Spinner spnStartHeight;
    private Spinner spnEndHeight;
    private MultiSpinner spnMaritalStatus;
    private MultiSpinner spnReligion;
    private MultiSpinner spnCaste;
    private MultiSpinner spnMotherTongue;
    private MultiSpinner spnCountry;
    private MultiSpinner spnState;
    private MultiSpinner spnCity;
    private MultiSpinner spnEducation;

    private Button btnSearch;
    private Button btnSaveSearch;

    List<String> listGender = new ArrayList<>();
    List<String> listTempHeight = new ArrayList<>();
    List<String> listStartHeight = new ArrayList<>();
    List<String> listEndHeight = new ArrayList<>();
    List<String> listStartAge = new ArrayList<>();
    List<String> listEndAge = new ArrayList<>();
    List<String> listMarital = new ArrayList<>();
    List<String> listReligion = new ArrayList<>();
    List<String> listCaste = new ArrayList<>();
    List<String> listMTongue = new ArrayList<>();
    List<String> listCountry = new ArrayList<>();
    List<String> listState = new ArrayList<>();
    List<String> listCity = new ArrayList<>();
    List<String> listEducation = new ArrayList<>();

    ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();

    private String strStartHeight = "";
    private String strEndHeight = "";
    private String strGender = "";
    private String strStartAge = "";
    private String strEndAge = "";
    private String strReligion = "";
    private String strMTongue = "";
    private String strCaste = "";
    private String strMaritalStatus = "";
    private String strCountry = "";
    private String strState = "";
    private String strCity = "";
    private String strEducation = "";

    private final int AGE_MIN = 18;
    private final int AGE_MAX = 60;

    private final int HEIGHT_MIN = 3;
    private final int HEIGHT_MAX = 8;


    public FragmentQuickSearch() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quick_search, container, false);

        spnGender = (Spinner) view.findViewById(R.id.search_quick_spn_gender);
        spnGender.setOnItemSelectedListener(this);
        listGender = Arrays.asList(getResources().getStringArray(R.array.search_gender_type));
        ArrayAdapter<String> adapterGender = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listGender);
        spnGender.setAdapter(adapterGender);

        spnStartAge = (Spinner) view.findViewById(R.id.search_quick_spn_start_age);
        spnStartAge.setOnItemSelectedListener(this);
        listStartAge = Arrays.asList(getResources().getStringArray(R.array.age));
        ArrayAdapter<String> adapterAge = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listStartAge);
        spnStartAge.setAdapter(adapterAge);

        spnEndAge = (Spinner) view.findViewById(R.id.search_quick_spn_end_age);
        spnEndAge.setOnItemSelectedListener(this);
        listEndAge = listStartAge;
        ArrayAdapter<String> adapterEndAge = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listEndAge);
        spnEndAge.setAdapter(adapterEndAge);

        spnStartHeight = (Spinner) view.findViewById(R.id.search_quick_spn_start_height);
        spnStartHeight.setOnItemSelectedListener(this);
        listStartHeight = Arrays.asList(getResources().getStringArray(R.array.height));
        ArrayAdapter<String> adapterStartHeight = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listStartHeight);
        spnStartHeight.setAdapter(adapterStartHeight);

        spnEndHeight = (Spinner) view.findViewById(R.id.search_quick_spn_end_height);
        spnEndHeight.setOnItemSelectedListener(this);
        listEndHeight = listStartHeight;
        ArrayAdapter<String> adapterEndHeight = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listEndHeight);
        spnEndHeight.setAdapter(adapterEndHeight);

        spnMaritalStatus = (MultiSpinner) view.findViewById(R.id.search_quick_spn_marital_status);
        listMarital = Arrays.asList(getResources().getStringArray(R.array.marital_status));
        spnMaritalStatus.setItems(listMarital, getString(R.string.prompt_marital), this);

        spnReligion = (MultiSpinner) view.findViewById(R.id.search_quick_spn_religion);
        listReligion = SplashActivity.dbHandler.getData(DBHandler.TBL_RELIGION);
        spnReligion.setItems(listReligion, getString(R.string.prompt_religion), this);

        spnCaste = (MultiSpinner) view.findViewById(R.id.search_quick_spn_caste);
        GetDataUsingWService casteService = new GetDataUsingWService(getActivity(), Application.URL_CASTE_MULTIPLE + 32, Application.SERVICE_ID_CASTE, nameValuePairs, this);
        Application.StartAsyncTaskInParallel(casteService);

        spnMotherTongue = (MultiSpinner) view.findViewById(R.id.search_quick_spn_tongue);
        listMTongue = SplashActivity.dbHandler.getData(DBHandler.TBL_MOTHER_TONGUE);
        spnMotherTongue.setItems(listMTongue, getString(R.string.prompt_tongue), this);

        spnCountry = (MultiSpinner) view.findViewById(R.id.search_quick_spn_country);
        listCountry = SplashActivity.dbHandler.getData(DBHandler.TBL_COUNTRY);
        spnCountry.setItems(listCountry, getString(R.string.prompt_country), this);

        spnState = (MultiSpinner) view.findViewById(R.id.search_quick_spn_state);
        GetDataUsingWService casteState = new GetDataUsingWService(getActivity(), Application.URL_STATE + 1, Application.SERVICE_ID_STATE, nameValuePairs, this);
        Application.StartAsyncTaskInParallel(casteState);

        spnCity = (MultiSpinner) view.findViewById(R.id.search_quick_spn_city);
        spnCity.setItems(listCity, getString(R.string.prompt_city), this);
        String urlCity = Application.URL_CITY + 1 + "&state_id=" + 1;
        GetDataUsingWService cityService = new GetDataUsingWService(getActivity(), urlCity, Application.SERVICE_ID_CITY, nameValuePairs, this);
        Application.StartAsyncTaskInParallel(cityService);

        spnEducation = (MultiSpinner) view.findViewById(R.id.search_quick_spn_education);
        listEducation = SplashActivity.dbHandler.getData(DBHandler.TBL_EDUCATION);
        spnEducation.setItems(listEducation, getString(R.string.prompt_education), this);

        btnSearch = (Button) view.findViewById(R.id.search_quick_btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkDataforSearchResult();
            }
        });

//        btnSaveSearch = (Button) view.findViewById(R.id.search_quick_btn_save_search);
//        btnSaveSearch.setOnClickListener(this);

        return view;
    }

    private void checkDataforSearchResult() {
        nameValuePairs.clear();
        nameValuePairs.add(new BasicNameValuePair("user_id", Profile.getProfile().getEmailID()));
        nameValuePairs.add(new BasicNameValuePair("gender", strGender));
//        nameValuePairs.add(new BasicNameValuePair("fromheight", strStartHeight));
//        nameValuePairs.add(new BasicNameValuePair("toheight", strEndHeight));
//        nameValuePairs.add(new BasicNameValuePair("fromage", strStartAge));
//        nameValuePairs.add(new BasicNameValuePair("toage", strEndAge));
        nameValuePairs.add(new BasicNameValuePair("m_status", strMaritalStatus));
        nameValuePairs.add(new BasicNameValuePair("religion", strReligion));
        nameValuePairs.add(new BasicNameValuePair("caste", strCaste));
        nameValuePairs.add(new BasicNameValuePair("m_tongue", strMTongue));
        nameValuePairs.add(new BasicNameValuePair("country", strCountry));
        nameValuePairs.add(new BasicNameValuePair("state", strState));
        nameValuePairs.add(new BasicNameValuePair("city", strCity));
        nameValuePairs.add(new BasicNameValuePair("education", strEducation));

        MyAsynchTask myAsynchTask = new MyAsynchTask();
        myAsynchTask.execute();

        Log.d(TAG, "::::::NameValuePair " + nameValuePairs.toString());
    }

    @Override
    public void getWebServiceData_JSON(JSONObject jsonObject, int serviceCounter) {

        if (serviceCounter == Application.SERVICE_ID_CASTE) { // caste
            try {
                listCaste.clear();
                SplashActivity.dbHandler.clearAllData(DBHandler.TBL_CASTE);
                JSONArray jsonarray = jsonObject.getJSONArray("caste");
                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonObject = jsonarray.getJSONObject(i);
                    // Populate spinner with country names
                    String id = jsonObject.getString("caste_id");
                    String name = jsonObject.getString("caste_name");
                    String status = "valid";
                    ContentValues values = new ContentValues();
                    values.put("ID", id);
                    values.put("NAME", name);
                    values.put("STATUS", status);
//                    Log.d(TAG, ":::::Caste " + name);
                    listCaste.add(name);
                    SplashActivity.dbHandler.insertData(DBHandler.TBL_CASTE, values);
                    //  Log.d(TAG, ":::::Caste " + name);
                }
                spnCaste.setItems(listCaste, getString(R.string.prompt_caste), this);
            } catch (Exception e) {
            }
        } else if (serviceCounter == Application.SERVICE_ID_STATE) { // State
            listState.clear();
            listCity.clear();
            strState = "";
            strState = "";
            spnState.setItems(listState, getString(R.string.prompt_state), this);
            spnCity.setItems(listCity, getString(R.string.prompt_city), this);
            SplashActivity.dbHandler.clearAllData(DBHandler.TBL_STATE);
            try {
                JSONArray jsonarray = jsonObject.getJSONArray("state");
                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonObject = jsonarray.getJSONObject(i);
                    // Populate spinner with country names
                    String id = jsonObject.getString("state_id");
                    String name = jsonObject.getString("state_name");
                    String status = "valid";
                    ContentValues values = new ContentValues();
                    values.put("ID", id);
                    values.put("NAME", name);
                    values.put("STATUS", status);
                    Log.d(TAG, ":::::State " + name);
                    listState.add(name);
                    SplashActivity.dbHandler.insertData(DBHandler.TBL_STATE, values);
                }
                listState = SplashActivity.dbHandler.getData(DBHandler.TBL_STATE);
                spnState.setItems(listState, getString(R.string.prompt_state), this);
//                String urlCity = Application.URL_CITY + strCountry + "&state_id=" + strState;
//                Log.d(TAG, "::::URL City " + urlCity);
//                GetDataUsingWService cityService = new GetDataUsingWService(getActivity(), urlCity, Application.SERVICE_ID_CITY, nameValuePairs, this);
//                Application.StartAsyncTaskInParallel(cityService);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (serviceCounter == Application.SERVICE_ID_CITY) { // City
            listCity.clear();
            strCity = "";
            spnCity.setItems(listCity, getString(R.string.prompt_city), this);
            SplashActivity.dbHandler.clearAllData(DBHandler.TBL_CITY);
            try {
                JSONArray jsonarray = jsonObject.getJSONArray("city");
                for (int i = 0; i < 100; i++) {
                    jsonObject = jsonarray.getJSONObject(i);
                    String id = jsonObject.getString("city_id");
                    String name = jsonObject.getString("city_name");
                    String status = "valid";
                    ContentValues values = new ContentValues();
                    values.put("ID", id);
                    values.put("NAME", name);
                    values.put("STATUS", status);
                    Log.d(TAG, ":::::City " + name);
                    listCity.add(name);
                    SplashActivity.dbHandler.insertData(DBHandler.TBL_CITY, values);
                }
                listCity = SplashActivity.dbHandler.getData(DBHandler.TBL_CITY);
                spnCity.setItems(listCity, getString(R.string.prompt_city), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getDataInArraay(List<String> listData, String dbName) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean selected = false;
        for (String str : listData) {
            if (selected) {
                stringBuilder.append(",");
            }
            selected = true;
            String s = SplashActivity.dbHandler.getIDUsingName(dbName, str);
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    private void getYourAgeSelection(String startAge) {
        //listEndAge.clear();
        String str = startAge;
        str = str.substring(0, 2).trim();
        int value = Integer.parseInt(str);
        for (int i = value; i <= AGE_MAX; i++) {
            listEndAge.add(i + " Year");
        }
//        adapterEndAge.notifyDataSetChanged();
    }

    private void getYourHeightSelection(int pos) {
        if (pos > 0) {
            listStartHeight.clear();
            listEndHeight.clear();
            for (int i = 0; i < listTempHeight.size(); i++) {
                if (i == pos) {
                    listStartHeight.add(listTempHeight.get(i));
                } else {
                    listEndHeight.add(listTempHeight.get(i));
                }
            }
//            adapterStartHeight.notifyDataSetChanged();
//            adapterEndHeight.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.search_quick_spn_gender:
                strGender = listGender.get(position);
                break;
            case R.id.search_quick_spn_start_age:
                strStartAge = listStartAge.get(position);
                break;
            case R.id.search_quick_spn_end_age:
                strEndAge = listEndAge.get(position);
                break;
            case R.id.search_quick_spn_start_height:
                strStartHeight = listStartHeight.get(position);
                break;
            case R.id.search_quick_spn_end_height:
                strEndHeight = listEndHeight.get(position);
                break;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemsSelected(boolean[] selected, List<String> listData, String data, int id) {

        //Log.d(TAG, "::::::Data " + data + ":::::ID  " + id + "::: ID XML  "+listData.size());
        switch (id) {

            case R.id.search_quick_spn_marital_status:
                strMaritalStatus = data;
                Log.d(TAG, ":::::Religion " + strMaritalStatus);
                break;
            case R.id.search_quick_spn_religion:
                strReligion = getDataInArraay(listData, DBHandler.TBL_RELIGION);
                Log.d(TAG, ":::::Religion " + strReligion);
                GetDataUsingWService casteService = new GetDataUsingWService(getActivity(), Application.URL_CASTE_MULTIPLE + strReligion, Application.SERVICE_ID_CASTE, nameValuePairs, this);
                Application.StartAsyncTaskInParallel(casteService);
                break;
            case R.id.search_quick_spn_caste:
                strCaste = getDataInArraay(listData, DBHandler.TBL_CASTE);
                Log.d(TAG, ":::::Caste " + strCaste);
                break;
            case R.id.search_quick_spn_tongue:
                strMTongue = getDataInArraay(listData, DBHandler.TBL_MOTHER_TONGUE);
                Log.d(TAG, ":::::Mother Tongue " + strMTongue);
                break;
            case R.id.search_quick_spn_country:
                strCountry = getDataInArraay(listData, DBHandler.TBL_COUNTRY);
                Log.d(TAG, ":::::Country " + strCountry);
                GetDataUsingWService casteState = new GetDataUsingWService(getActivity(), Application.URL_STATE + strCountry, Application.SERVICE_ID_STATE, nameValuePairs, this);
                Application.StartAsyncTaskInParallel(casteState);
                break;
            case R.id.search_quick_spn_state:
                strState = getDataInArraay(listData, DBHandler.TBL_STATE);
                String urlCity = Application.URL_CITY + strCountry + "&state_id=" + strState;
                GetDataUsingWService cityService = new GetDataUsingWService(getActivity(), urlCity, Application.SERVICE_ID_CITY, nameValuePairs, this);
                Application.StartAsyncTaskInParallel(cityService);
                break;
            case R.id.search_quick_spn_city:
                strCity = getDataInArraay(listData, DBHandler.TBL_CITY);
                break;
            case R.id.search_quick_spn_education:
                strEducation = getDataInArraay(listData, DBHandler.TBL_EDUCATION);
                break;
        }
    }

    private CProgressDialog cProgressDialog;

    private class MyAsynchTask extends AsyncTask<String, String, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cProgressDialog = new CProgressDialog(getActivity());
            cProgressDialog.setMessage("Searching...");
            cProgressDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            WebService webService = new WebService();
            JSONObject jsonObject = webService.makeHttpRequest(Application.URL_SEARCH, nameValuePairs);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            cProgressDialog.hide();
            Log.d(TAG, ":::::JSOn " + jsonObject);
            Intent intent = new Intent(getActivity(), SearchResultActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}
