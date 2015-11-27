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
import android.widget.CheckBox;
import android.widget.Spinner;

import com.mentobile.utility.DBHandler;
import com.mentobile.utility.MultiSpinner;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
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
    private MultiSpinner spnDrinking;
    private MultiSpinner spnSmoking;
    private MultiSpinner spnComplexion;
    private MultiSpinner spnBodyType;
    private MultiSpinner spnStar;
    private MultiSpinner spnManglik;

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
    List<String> arrayListOccupation = new ArrayList<>();
    List<String> arrayListIncome = new ArrayList<>();
    List<String> arrayListEating = new ArrayList<>();
    List<String> arrayListDrinking = new ArrayList<>();
    List<String> arrayListSmoking = new ArrayList<>();
    List<String> arrayListComplexion = new ArrayList<>();
    List<String> arrayListBodyType = new ArrayList<>();
    List<String> arrayListStar = new ArrayList<>();
    List<String> arrayListManglik = new ArrayList<>();


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
    private String strOccupation= "";
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

       /* spnGender = (Spinner) view.findViewById(R.id.search_quick_spn_gender);
        spnGender.setOnItemSelectedListener(this);
        arrayListGender = Arrays.asList(getResources().getStringArray(R.array.search_gender_type));
        ArrayAdapter<String> adapterGender = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListGender);
        spnGender.setAdapter(adapterGender);


        spnStartAge = (Spinner) view.findViewById(R.id.search_advance_spn_start_age);
        spnStartAge.setOnItemSelectedListener(this);
        arrayListStartAge = Arrays.asList(getResources().getStringArray(R.array.age));
        ArrayAdapter<String> adapterAge = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListStartAge);
        spnStartAge.setAdapter(adapterAge);

        spnEndAge = (Spinner) view.findViewById(R.id.search_advance_spn_end_age);
        spnEndAge.setOnItemSelectedListener(this);
        spnEndAge.setAdapter(adapterAge);

        spnStartHeight = (Spinner) view.findViewById(R.id.search_advance_spn_start_height);
        spnStartHeight.setOnItemSelectedListener(this);
        arrayListHeight = Arrays.asList(getResources().getStringArray(R.array.height));
        ArrayAdapter<String> adapterHeight = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListHeight);
        spnStartHeight.setAdapter(adapterHeight);

        spnEndHeight = (Spinner) view.findViewById(R.id.search_advance_spn_end_height);
        spnEndHeight.setOnItemSelectedListener(this);
        spnEndHeight.setAdapter(adapterHeight);

        spnMaritalStatus = (MultiSpinner) view.findViewById(R.id.search_advance_spn_marital_status);
        arrayListMarital = Arrays.asList(getResources().getStringArray(R.array.marital_status));
        spnMaritalStatus.setItems(arrayListMarital, getString(R.string.prompt_marital), this);

        spnReligion = (MultiSpinner) view.findViewById(R.id.search_advance_spn_religion);
        arrayListReligion = SplashActivity.dbHandler.getData(DBHandler.TBL_RELIGION);
        spnReligion.setItems(arrayListReligion, getString(R.string.prompt_religion), this);

        spnCaste = (MultiSpinner) view.findViewById(R.id.search_advance_spn_caste);
        strReligion = SplashActivity.dbHandler.getIDUsingName(DBHandler.TBL_RELIGION, arrayListReligion.get(0));
//                //       Get Data Caste data from Web Service
        GetDataUsingWService casteService = new GetDataUsingWService(getActivity(), Application.URL_CASTE_MULTIPLE + strReligion, Application.SERVICE_ID_CASTE, nameValuePairs, this);
        Application.StartAsyncTaskInParallel(casteService);

        spnMotherTongue = (MultiSpinner) view.findViewById(R.id.search_advance_spn_tongue);
        arrayListMTongue = SplashActivity.dbHandler.getData(DBHandler.TBL_MOTHER_TONGUE);
        spnMotherTongue.setItems(arrayListMTongue, getString(R.string.prompt_tongue), this);

        spnCountry = (MultiSpinner) view.findViewById(R.id.search_advance_spn_country);
        arrayListCountry = SplashActivity.dbHandler.getData(DBHandler.TBL_COUNTRY);
        spnCountry.setItems(arrayListCountry, getString(R.string.prompt_country), this);

        spnState = (MultiSpinner) view.findViewById(R.id.search_advance_spn_state);
        String strState[] = getResources().getStringArray(R.array.state);
        List<String> listState = Arrays.asList(strState);
        spnState.setItems(listState, getString(R.string.prompt_state), this);

        spnCity = (MultiSpinner) view.findViewById(R.id.search_advance_spn_city);
        String strCity[] = getResources().getStringArray(R.array.city);
        List<String> listCity = Arrays.asList(strCity);
        spnCity.setItems(listCity, getString(R.string.prompt_city), this);

        spnEducation = (MultiSpinner) view.findViewById(R.id.search_advance_spn_education);
        arrayListEducation = SplashActivity.dbHandler.getData(DBHandler.TBL_EDUCATION);
        spnEducation.setItems(arrayListEducation, getString(R.string.prompt_education), this);

        btnSearch = (Button) view.findViewById(R.id.search_advance_btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
*/
        return view ;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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

    @Override
    public void onItemsSelected(boolean[] selected, List<String> listData, String data, int id) {

    }
}
