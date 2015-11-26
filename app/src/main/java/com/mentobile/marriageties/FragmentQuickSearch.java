package com.mentobile.marriageties;

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

import com.mentobile.utility.DBHandler;
import com.mentobile.utility.MultiSpinner;

import org.apache.http.NameValuePair;
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

    List<String> arrayListGender = new ArrayList<>();
    List<String> arrayListHeight = new ArrayList<>();
    List<String> arrayListAge = new ArrayList<>();
    List<String> arrayListMarital = new ArrayList<>();
    List<String> arrayListReligion = new ArrayList<>();
    List<SpinnerItem> arrayListCaste = new ArrayList<>();
    List<String> arrayListMTongue = new ArrayList<>();
    List<String> arrayListCountry = new ArrayList<>();
    List<SpinnerItem> arrayListState = new ArrayList<>();
    ArrayList<SpinnerItem> arrayListCity = new ArrayList<>();
    List<String> arrayListEducation = new ArrayList<>();

    ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();

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


    public FragmentQuickSearch() {
        // Required empty public constructor
    }

    MultiSpinner spinner;

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
        arrayListGender = Arrays.asList(getResources().getStringArray(R.array.search_gender_type));
        ArrayAdapter<String> adapterGender = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListGender);
        spnGender.setAdapter(adapterGender);


        spnStartAge = (Spinner) view.findViewById(R.id.search_quick_spn_start_age);
        spnStartAge.setOnItemSelectedListener(this);
        arrayListAge = Arrays.asList(getResources().getStringArray(R.array.age));
        ArrayAdapter<String> adapterAge = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListAge);
        spnStartAge.setAdapter(adapterAge);

        spnEndAge = (Spinner) view.findViewById(R.id.search_quick_spn_end_age);
        spnEndAge.setOnItemSelectedListener(this);
        spnEndAge.setAdapter(adapterAge);

        spnStartHeight = (Spinner) view.findViewById(R.id.search_quick_spn_start_height);
        spnStartHeight.setOnItemSelectedListener(this);
        arrayListHeight = Arrays.asList(getResources().getStringArray(R.array.height));
        ArrayAdapter<String> adapterHeight = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListHeight);
        spnStartHeight.setAdapter(adapterHeight);

        spnEndHeight = (Spinner) view.findViewById(R.id.search_quick_spn_end_height);
        spnEndHeight.setOnItemSelectedListener(this);
        spnEndHeight.setAdapter(adapterHeight);

        spnMaritalStatus = (MultiSpinner) view.findViewById(R.id.search_quick_spn_marital_status);
//        spnMaritalStatus.setOnItemSelectedListener(this);
        arrayListMarital = Arrays.asList(getResources().getStringArray(R.array.marital_status));
        spnMaritalStatus.setItems(arrayListMarital, getString(R.string.prompt_marital), this);
//        ArrayAdapter<String> adapterMarital = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListMarital);
//        spnMaritalStatus.setAdapter(adapterMarital);

        spnReligion = (MultiSpinner) view.findViewById(R.id.search_quick_spn_religion);
        arrayListReligion = SplashActivity.dbHandler.getData(DBHandler.TBL_RELIGION);
        spnReligion.setItems(arrayListReligion, getString(R.string.prompt_religion), this);
//        ArrayAdapter<String> adapterReligion = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListReligion);
//        spnReligion.setAdapter(adapterReligion);
//        spnReligion.setOnItemSelectedListener(this);

        spnCaste = (MultiSpinner) view.findViewById(R.id.search_quick_spn_caste);
        strReligion = SplashActivity.dbHandler.getIDUsingName(DBHandler.TBL_RELIGION, arrayListReligion.get(0));
//                //       Get Data Caste data from Web Service
        GetDataUsingWService casteService = new GetDataUsingWService(getActivity(), Application.URL_CASTE + strReligion, Application.SERVICE_ID_CASTE, nameValuePairs, this);
        Application.StartAsyncTaskInParallel(casteService);

//        spnCaste.setOnItemSelectedListener(this);
//        String strCaste[] = getResources().getStringArray(R.array.caste);
//        List<String> listCaste = Arrays.asList(strCaste);
//        spnCaste.setItems(listCaste, getString(R.string.prompt_caste), this);


//        ArrayAdapter<String> adapterCaste = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, strCaste);
//        spnCaste.setAdapter(adapterCaste);

        spnMotherTongue = (MultiSpinner) view.findViewById(R.id.search_quick_spn_tongue);
//        spnMotherTongue.setOnItemSelectedListener(this);
        arrayListMTongue = SplashActivity.dbHandler.getData(DBHandler.TBL_MOTHER_TONGUE);
        spnMotherTongue.setItems(arrayListMTongue, getString(R.string.prompt_tongue), this);
//        ArrayAdapter<String> adapterTounge = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListMTongue);
//        spnMotherTongue.setAdapter(adapterTounge);

        spnCountry = (MultiSpinner) view.findViewById(R.id.search_quick_spn_country);
//        spnCountry.setOnItemSelectedListener(this);
        arrayListCountry = SplashActivity.dbHandler.getData(DBHandler.TBL_COUNTRY);
        spnCountry.setItems(arrayListCountry, getString(R.string.prompt_country), this);
//        ArrayAdapter<String> adapterCountry = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListCountry);
//        spnCountry.setAdapter(adapterCountry);

        spnState = (MultiSpinner) view.findViewById(R.id.search_quick_spn_state);
//        spnState.setOnItemSelectedListener(this);
        String strState[] = getResources().getStringArray(R.array.state);
        List<String> listState = Arrays.asList(strState);
        spnState.setItems(listState, getString(R.string.prompt_state), this);
//        ArrayAdapter<String> adapterState = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, strState);
//        spnState.setAdapter(adapterState);

        spnCity = (MultiSpinner) view.findViewById(R.id.search_quick_spn_city);
//        spnCity.setOnItemSelectedListener(this);
        String strCity[] = getResources().getStringArray(R.array.city);
        List<String> listCity = Arrays.asList(strCity);
        spnCity.setItems(listCity, getString(R.string.prompt_city), this);
//        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, strCity);
//        spnCity.setAdapter(adapterCity);

        spnEducation = (MultiSpinner) view.findViewById(R.id.search_quick_spn_education);
//        spnEducation.setOnItemSelectedListener(this);
        arrayListEducation = SplashActivity.dbHandler.getData(DBHandler.TBL_EDUCATION);
        spnEducation.setItems(arrayListEducation, getString(R.string.prompt_education), this);
//        ArrayAdapter<String> adapterEducation = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, strEducation);
//        spnEducation.setAdapter(adapterEducation);

        btnSearch = (Button) view.findViewById(R.id.search_quick_btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        btnSaveSearch = (Button) view.findViewById(R.id.search_quick_btn_save_search);
//        btnSaveSearch.setOnClickListener(this);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onItemsSelected(boolean[] selected, String data, int id) {

        Log.d(TAG, "::::::Data " + data + ":::::ID  " + id + "::: ID XML  ");
        switch (id) {

            case R.id.search_quick_spn_marital_status:
                break;
            case R.id.search_quick_spn_religion:
                strReligion = SplashActivity.dbHandler.getIDUsingName(DBHandler.TBL_RELIGION, arrayListReligion.get(0));
//                //       Get Data Caste data from Web Service
                GetDataUsingWService casteService = new GetDataUsingWService(getActivity(), Application.URL_CASTE + strReligion, Application.SERVICE_ID_CASTE, nameValuePairs, this);
                Application.StartAsyncTaskInParallel(casteService);
                break;
            case R.id.search_quick_spn_caste:
                break;
            case R.id.search_quick_spn_tongue:
                break;
            case R.id.search_quick_spn_country:
                break;
            case R.id.search_quick_spn_state:
                break;
            case R.id.search_quick_spn_city:
                break;
            case R.id.search_quick_spn_education:
                break;
        }
    }

    @Override
    public void getWebServiceData_JSON(JSONObject jsonObject, int serviceCounter) {

        if (serviceCounter == Application.SERVICE_ID_CASTE) { // caste
            try {
                arrayListCaste.clear();
                ArrayList<String> tempCaste = new ArrayList<>();
                JSONArray jsonarray = jsonObject.getJSONArray("caste");
                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonObject = jsonarray.getJSONObject(i);
                    // Populate spinner with country names
                    String id = jsonObject.getString("caste_id");
                    String name = jsonObject.getString("caste_name");
                    SpinnerItem spinnerItem = new SpinnerItem(id, name);
                    arrayListCaste.add(spinnerItem);
                    tempCaste.add(name);
                    Log.d(TAG, ":::::Caste " + name);
                }
                spnCaste.setItems(tempCaste, getString(R.string.prompt_caste), this);
            } catch (Exception e) {
            }
        } else if (serviceCounter == Application.SERVICE_ID_STATE) { // State
            arrayListState.clear();
            ArrayList<String> tempState = new ArrayList<>();
            try {
                JSONArray jsonarray = jsonObject.getJSONArray("state");
                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonObject = jsonarray.getJSONObject(i);
                    // Populate spinner with country names
                    String id = jsonObject.getString("state_id");
                    String name = jsonObject.getString("state_name");
                    SpinnerItem spinnerItem = new SpinnerItem(id, name);
                    tempState.add(name);
                    arrayListState.add(spinnerItem);
                }
                ArrayAdapter<String> adapterState = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, tempState);
                spnState.setAdapter(adapterState);

                SpinnerItem spinnerItem = arrayListState.get(0);
                strState = spinnerItem.getId();
                String urlCity = Application.URL_CITY + strCountry + "&state_id=" + strState;
//                //       Get Data Country data from Web Service
                Log.d(TAG, "::::Url City " + urlCity);
                GetDataUsingWService cityService = new GetDataUsingWService(getActivity(), urlCity, Application.SERVICE_ID_CITY, nameValuePairs, this);
                Application.StartAsyncTaskInParallel(cityService);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (serviceCounter == Application.SERVICE_ID_CITY) { // City
            arrayListCity.clear();
            ArrayList<String> tempCity = new ArrayList<>();
            try {
                JSONArray jsonarray = jsonObject.getJSONArray("city");
                for (int i = 0; i < 100; i++) {
                    jsonObject = jsonarray.getJSONObject(i);
                    String id = jsonObject.getString("city_id");
                    String name = jsonObject.getString("city_name");
                    SpinnerItem spinnerItem = new SpinnerItem(id, name);
                    arrayListCity.add(spinnerItem);
                    tempCity.add(name);
                }
                ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, tempCity);
                spnCity.setAdapter(adapterCity);
                adapterCity.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
