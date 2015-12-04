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
import android.widget.CheckBox;
import android.widget.Spinner;

import com.mentobile.utility.CProgressDialog;
import com.mentobile.utility.DBHandler;
import com.mentobile.utility.MultiSpinner;
import com.mentobile.utility.WebService;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FragmentAdvanceSearch extends Fragment implements AdapterView.OnItemSelectedListener, MultiSpinner.MultiSpinnerListener, GetDataUsingWService.GetWebServiceData {

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
    private CheckBox chkWithPhoto;
    private MultiSpinner spnCountry;
    private MultiSpinner spnState;
    private MultiSpinner spnCity;
    private MultiSpinner spnEducation;
    private MultiSpinner spnOccupation;
    private MultiSpinner spnIncome;
    private MultiSpinner spnEating;
    private Spinner spnDrinking;
    private Spinner spnSmoking;
    private MultiSpinner spnComplexion;
    private MultiSpinner spnBodyType;
    private MultiSpinner spnStar;
    private Spinner spnManglik;

    private Button btnSearch;
    private Button btnSaveSearch;

    List<String> listGender = new ArrayList<>();
    List<String> listHeight = new ArrayList<>();
    List<String> listAge = new ArrayList<>();
    List<String> listMarital = new ArrayList<>();
    List<String> listReligion = new ArrayList<>();
    List<String> listCaste = new ArrayList<>();
    List<String> listMTongue = new ArrayList<>();
    List<String> listCountry = new ArrayList<>();
    List<String> listState = new ArrayList<>();
    List<String> listCity = new ArrayList<>();
    List<String> listEducation = new ArrayList<>();
    List<String> listOccupation = new ArrayList<>();
    List<String> listIncome = new ArrayList<>();
    List<String> listEating = new ArrayList<>();
    List<String> listDrinking = new ArrayList<>();
    List<String> listSmoking = new ArrayList<>();
    List<String> listComplexion = new ArrayList<>();
    List<String> listBodyType = new ArrayList<>();
    List<String> listStar = new ArrayList<>();
    List<String> listManglik = new ArrayList<>();


    private String strHeight = "";
    private String strGender = "";
    private String strReligion = "";
    private String strMTongue = "";
    private String strCaste = "";
    private String strMaritalStatus = "";
    private String strCountry = "";
    private String strState = "";
    private String strCity = "";
    private String strEducation = "";
    private String strOccupation = "";
    private String strIncome = "";
    private String strEating = "";
    private String strDrinking = "";
    private String strSmoking = "";
    private String strComplexion = "";
    private String strBodyType = "";
    private String strStar = "";
    private String strManglik = "";

    ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();

    public FragmentAdvanceSearch() {
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
        View view = inflater.inflate(R.layout.fragment_advance_search, container, false);

        spnGender = (Spinner) view.findViewById(R.id.search_advance_spn_gender);
        spnGender.setOnItemSelectedListener(this);
        listGender = Arrays.asList(getResources().getStringArray(R.array.search_gender_type));
        ArrayAdapter<String> adapterGender = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listGender);
        spnGender.setAdapter(adapterGender);

        spnStartAge = (Spinner) view.findViewById(R.id.search_advance_spn_start_age);
        spnStartAge.setOnItemSelectedListener(this);
        listAge = Arrays.asList(getResources().getStringArray(R.array.age));
        ArrayAdapter<String> adapterAge = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listAge);
        spnStartAge.setAdapter(adapterAge);

        spnEndAge = (Spinner) view.findViewById(R.id.search_advance_spn_end_age);
        spnEndAge.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterEndAge = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listAge);
        spnEndAge.setAdapter(adapterEndAge);

        spnStartHeight = (Spinner) view.findViewById(R.id.search_advance_spn_start_height);
        spnStartHeight.setOnItemSelectedListener(this);
        listHeight = Arrays.asList(getResources().getStringArray(R.array.height));
        ArrayAdapter<String> adapterStartHeight = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listHeight);
        spnStartHeight.setAdapter(adapterStartHeight);

        spnEndHeight = (Spinner) view.findViewById(R.id.search_advance_spn_end_height);
        spnEndHeight.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterEndHeight = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listHeight);
        spnEndHeight.setAdapter(adapterEndHeight);

        spnMaritalStatus = (MultiSpinner) view.findViewById(R.id.search_advance_spn_marital_status);
        listMarital = Arrays.asList(getResources().getStringArray(R.array.marital_status));
        spnMaritalStatus.setItems(listMarital, getString(R.string.prompt_marital), this);

        spnReligion = (MultiSpinner) view.findViewById(R.id.search_advance_spn_religion);
        listReligion = SplashActivity.dbHandler.getData(DBHandler.TBL_RELIGION);
        spnReligion.setItems(listReligion, getString(R.string.prompt_religion), this);

        spnCaste = (MultiSpinner) view.findViewById(R.id.search_advance_spn_caste);
//        listCaste  = Arrays.asList(getResources().getStringArray(R.array.caste));
        spnCaste.setItems(listCaste, getString(R.string.prompt_caste), this);
//        GetDataUsingWService casteService = new GetDataUsingWService(getActivity(), Application.URL_CASTE_MULTIPLE + 32, Application.SERVICE_ID_CASTE, nameValuePairs, this);
//        Application.StartAsyncTaskInParallel(casteService);

        spnMotherTongue = (MultiSpinner) view.findViewById(R.id.search_advance_spn_tongue);
        listMTongue = SplashActivity.dbHandler.getData(DBHandler.TBL_MOTHER_TONGUE);
        spnMotherTongue.setItems(listMTongue, getString(R.string.prompt_tongue), this);

        spnCountry = (MultiSpinner) view.findViewById(R.id.search_advance_spn_country);
        listCountry = SplashActivity.dbHandler.getData(DBHandler.TBL_COUNTRY);
        spnCountry.setItems(listCountry, getString(R.string.prompt_country), this);

        spnState = (MultiSpinner) view.findViewById(R.id.search_advance_spn_state);
//        listState  = Arrays.asList(getResources().getStringArray(R.array.state));
        spnState.setItems(listState, getString(R.string.prompt_state), this);
//        GetDataUsingWService casteState = new GetDataUsingWService(getActivity(), Application.URL_STATE + 1, Application.SERVICE_ID_STATE, nameValuePairs, this);
//        Application.StartAsyncTaskInParallel(casteState);

        spnCity = (MultiSpinner) view.findViewById(R.id.search_advance_spn_city);
//        listCity  = Arrays.asList(getResources().getStringArray(R.array.city));
        spnCity.setItems(listCity, getString(R.string.prompt_city), this);
//        String urlCity = Application.URL_CITY + 1 + "&state_id=" + 1;
//        GetDataUsingWService cityService = new GetDataUsingWService(getActivity(), urlCity, Application.SERVICE_ID_CITY, nameValuePairs, this);
//        Application.StartAsyncTaskInParallel(cityService);

        spnEducation = (MultiSpinner) view.findViewById(R.id.search_advance_spn_education);
        listEducation = SplashActivity.dbHandler.getData(DBHandler.TBL_EDUCATION);
        spnEducation.setItems(listEducation, getString(R.string.prompt_education), this);

        spnOccupation = (MultiSpinner) view.findViewById(R.id.search_advance_spn_occupation);
        listOccupation = SplashActivity.dbHandler.getData(DBHandler.TBL_OCCUPATION);
        spnOccupation.setItems(listOccupation, getString(R.string.prompt_occupation), this);

        spnIncome = (MultiSpinner) view.findViewById(R.id.search_advance_spn_income);
        listIncome = Arrays.asList(getResources().getStringArray(R.array.annual_income));
        spnIncome.setItems(listIncome, getString(R.string.prompt_income), this);

        spnEating = (MultiSpinner) view.findViewById(R.id.search_advance_spn_eating);
        listEating = Arrays.asList(getResources().getStringArray(R.array.eating));
        spnEating.setItems(listEating, getString(R.string.prompt_eating), this);

        spnDrinking = (Spinner) view.findViewById(R.id.search_advance_spn_drinking);
        listDrinking = Arrays.asList(getResources().getStringArray(R.array.drink));
        ArrayAdapter<String> adapterDrinking = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listDrinking);
        spnDrinking.setAdapter(adapterDrinking);
        spnDrinking.setOnItemSelectedListener(this);

        spnSmoking = (Spinner) view.findViewById(R.id.search_advance_spn_smoking);
        listSmoking = Arrays.asList(getResources().getStringArray(R.array.smoke));
        ArrayAdapter<String> adapterSmoking = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listSmoking);
        spnSmoking.setAdapter(adapterSmoking);
        spnSmoking.setOnItemSelectedListener(this);

        spnComplexion = (MultiSpinner) view.findViewById(R.id.search_advance_spn_complexion);
        listComplexion = Arrays.asList(getResources().getStringArray(R.array.complexian));
        spnComplexion.setItems(listComplexion, getString(R.string.prompt_complexion), this);

        spnBodyType = (MultiSpinner) view.findViewById(R.id.search_advance_spn_body_type);
        listBodyType = Arrays.asList(getResources().getStringArray(R.array.body));
        spnBodyType.setItems(listBodyType, getString(R.string.prompt_body), this);

        spnStar = (MultiSpinner) view.findViewById(R.id.search_advance_spn_star);
        listStar = Arrays.asList(getResources().getStringArray(R.array.star));
        spnStar.setItems(listStar, getString(R.string.prompt_star), this);

        spnManglik = (Spinner) view.findViewById(R.id.search_advance_spn_manglik);
        listManglik = Arrays.asList(getResources().getStringArray(R.array.mangalik));
        ArrayAdapter<String> adapterManglik = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listManglik);
        spnManglik.setAdapter(adapterManglik);
        spnManglik.setOnItemSelectedListener(this);

        btnSearch = (Button) view.findViewById(R.id.search_advance_btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkDataforSearchResult();
            }
        });


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
        nameValuePairs.add(new BasicNameValuePair("occupation", strOccupation));
        nameValuePairs.add(new BasicNameValuePair("annual_income", strIncome));
        nameValuePairs.add(new BasicNameValuePair("diet", strEating));
        nameValuePairs.add(new BasicNameValuePair("drink", strDrinking));
        nameValuePairs.add(new BasicNameValuePair("smoking", strSmoking));
        nameValuePairs.add(new BasicNameValuePair("complexion", strComplexion));
        nameValuePairs.add(new BasicNameValuePair("bodytype", strBodyType));
        nameValuePairs.add(new BasicNameValuePair("star", strStar));
        nameValuePairs.add(new BasicNameValuePair("manglik", strManglik));

        Log.d(TAG, "::::::NameValuePair " + nameValuePairs.toString());

        MyAsynchTask myAsynchTask = new MyAsynchTask();
        myAsynchTask.execute();

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
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("Country");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String photoPath = jsonObject1.getString("photo1");
                    String matri_id = jsonObject1.getString("matri_id");
                    String name = jsonObject1.getString("username");
                    String age = jsonObject1.getString("Age");
                    String height = jsonObject1.getString("height");
                    String religion = ""; // this field is not available in search web service
                    String caste = jsonObject1.getString("caste_name");
                    String gotra = jsonObject1.getString("mtongue_name");
                    ;// this field is not available in search web service
                    String education = jsonObject1.getString("edu_name");
                    String city = jsonObject1.getString("city_name");
                    String state = jsonObject1.getString("state_name");
                    String country = jsonObject1.getString("country_name");
                    MainActivity.sortedProfileList.clear();
                    ProfileShorted profileShorted = new ProfileShorted(photoPath,matri_id, name, age, height, religion, caste, gotra, education, city, state, country);
                    MainActivity.sortedProfileList.add(profileShorted);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
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


    private String getDataInArray(List<String> listData, String dbName) {
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
                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonObject = jsonarray.getJSONObject(i);
                    String id = jsonObject.getString("city_id");
                    String name = jsonObject.getString("city_name");
                    String status = "valid";
                    ContentValues values = new ContentValues();
                    values.put("ID", id);
                    values.put("NAME", name);
                    values.put("STATUS", status);
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

    @Override
    public void onItemsSelected(boolean[] selected, List<String> listData, String data, int id) {
        Log.d(TAG, ":::::ID " + id);
        switch (id) {
            case R.id.search_advance_spn_marital_status:
                strMaritalStatus = data;
                break;
            case R.id.search_advance_spn_religion:
                strReligion = getDataInArray(listData, DBHandler.TBL_RELIGION);
                GetDataUsingWService casteService = new GetDataUsingWService(getActivity(), Application.URL_CASTE_MULTIPLE + strReligion, Application.SERVICE_ID_CASTE, nameValuePairs, this);
                Application.StartAsyncTaskInParallel(casteService);
                break;
            case R.id.search_advance_spn_caste:
                strCaste = getDataInArray(listData, DBHandler.TBL_CASTE);
//                Log.d(TAG, ":::::Caste " + strCaste);
                break;
            case R.id.search_advance_spn_tongue:
                strMTongue = getDataInArray(listData, DBHandler.TBL_MOTHER_TONGUE);
//                Log.d(TAG, ":::::Mother Tongue " + strMTongue);
                break;
            case R.id.search_advance_spn_country:
                strCountry = getDataInArray(listData, DBHandler.TBL_COUNTRY);
//                Log.d(TAG, ":::::Country " + strCountry);
                GetDataUsingWService casteState = new GetDataUsingWService(getActivity(), Application.URL_STATE + strCountry, Application.SERVICE_ID_STATE, nameValuePairs, this);
                Application.StartAsyncTaskInParallel(casteState);
                break;
            case R.id.search_advance_spn_state:
                strState = getDataInArray(listData, DBHandler.TBL_STATE);
                String urlCity = Application.URL_CITY + strCountry + "&state_id=" + strState;
                GetDataUsingWService cityService = new GetDataUsingWService(getActivity(), urlCity, Application.SERVICE_ID_CITY, nameValuePairs, this);
                Application.StartAsyncTaskInParallel(cityService);
                break;
            case R.id.search_advance_spn_city:
                strCity = getDataInArray(listData, DBHandler.TBL_CITY);
                break;
            case R.id.search_advance_spn_education:
                strEducation = getDataInArray(listData, DBHandler.TBL_EDUCATION);
                break;
            case R.id.search_advance_spn_occupation:
                strOccupation = getDataInArray(listData, DBHandler.TBL_OCCUPATION);
                break;

            case R.id.search_advance_spn_income:
                strIncome = data;
                break;

            case R.id.search_advance_spn_eating:
                strEating = data;
                break;

            case R.id.search_advance_spn_complexion:
                strComplexion = data;
                break;

            case R.id.search_advance_spn_body_type:
                strBodyType = data;
                break;

            case R.id.search_advance_spn_star:
                strStar = data;
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.search_advance_spn_gender:
                strGender = listGender.get(position);
                break;
            case R.id.search_advance_spn_start_age:
//                strStartAge = listStartAge.get(position);
                break;
            case R.id.search_advance_spn_end_age:
//                strEndAge = listEndAge.get(position);
                break;
            case R.id.search_advance_spn_start_height:
//                strStartHeight = listStartHeight.get(position);
                break;
            case R.id.search_advance_spn_end_height:
//                strEndHeight = listEndHeight.get(position);
                break;

            case R.id.search_advance_spn_drinking:
                strDrinking = listDrinking.get(position);
                break;

            case R.id.search_advance_spn_smoking:
                strSmoking = listSmoking.get(position);
                break;

            case R.id.search_advance_spn_manglik:
                strManglik = listManglik.get(position);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
