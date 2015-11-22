package com.mentobile.marriageties;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.mentobile.utility.DBHandler;
import com.mentobile.utility.DatePickerDialogCustom;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentProfile extends Fragment implements AdapterView.OnItemSelectedListener, GetDataUsingWService.GetWebServiceData {

    private static final String TAG = "FragmentProfile";
    Fragment_Partner fragmentPartner;

    private EditText edFirstName;
    private EditText edLastName;
    private EditText edEmailID;
    private EditText edPassword;
    private EditText edMobileNo;
    private EditText edDOB;
    private Spinner spnHeight;
    private Spinner spnWeight;
    private Spinner spnGender;
    private Spinner spnReligion;
    private Spinner spnMotherTongue;
    private Spinner spnCaste;
    private Spinner spnMaritalStatus;
    private Spinner spnManglik;
    private Spinner spnCountry;
    private Spinner spnState;
    private Spinner spnCity;


    List<String> arrayListHeight = new ArrayList<>();
    List<String> arrayListWeight = new ArrayList<>();
    List<String> arrayListGender = new ArrayList<>();
    List<String> arrayListReligion = new ArrayList<>();
    List<String> arrayListMTongue = new ArrayList<>();
    List<SpinnerItem> arrayListCaste = new ArrayList<>();
    List<String> arrayListMarital = new ArrayList<>();
    List<String> arrayListManglik = new ArrayList<>();
    List<String> arrayListCountry = new ArrayList<>();
    List<SpinnerItem> arrayListState = new ArrayList<>();
    ArrayList<SpinnerItem> arrayListCity = new ArrayList<>();

    private final int SERVICE_ID_CASTE = 2;
    private final int SERVICE_ID_STATE = 4;
    private final int SERVICE_ID_CITY = 5;

    ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
    public static ArrayList<NameValuePair> valuePairsRegister = new ArrayList<>();

    private String strFirstName = "";
    private String strLastName = "";
    private String strEmail = "";
    private String strPassword = "";
    private String strMobile = "";
    private String strDOB = "";
    private String strHeight = "";
    private String strWeight = "";
    private String strGender = "";
    private String strReligion = "";
    private String strMTongue = "";
    private String strCaste = "";
    private String strMaritalStatus = "";
    private String strManglik = "";
    private String strCountry = "";
    private String strState = "";
    private String strCity = "";

    public FragmentProfile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentPartner = new Fragment_Partner();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ((LoginActivity) getActivity()).getSupportActionBar().setTitle("Register Activity");

        edFirstName = (EditText) view.findViewById(R.id.register_edt_firstname);
        edLastName = (EditText) view.findViewById(R.id.register_edt_lastname);
        edEmailID = (EditText) view.findViewById(R.id.register_edt_emailid);
        edEmailID.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !Application.isValidEmail(edEmailID.getText().toString().trim())) {
                    edEmailID.setError(getResources().getString(R.string.error_email_verify));
                }
            }
        });

        edPassword = (EditText) view.findViewById(R.id.register_edt_password);
        edMobileNo = (EditText) view.findViewById(R.id.register_edt_mobile);

        edDOB = (EditText) view.findViewById(R.id.register_edt_dob);
        edDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialogCustom datePickerFragment = new DatePickerDialogCustom(getActivity(), new DatePickerDialogCustom.GetPickerDailogDate() {
                    @Override
                    public void GetDate(String date) {
                        edDOB.setText("" + date);
                    }
                });
                datePickerFragment.show(getActivity().getSupportFragmentManager(), "Show Dialog");
            }
        });

        spnHeight = (Spinner) view.findViewById(R.id.register_spn_height);
        spnHeight.setOnItemSelectedListener(this);
        arrayListHeight = Arrays.asList(getResources().getStringArray(R.array.height));
        ArrayAdapter<String> adapterHeight = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListHeight);
        spnHeight.setAdapter(adapterHeight);

        spnWeight = (Spinner) view.findViewById(R.id.register_spn_weight);
        spnWeight.setOnItemSelectedListener(this);
        arrayListWeight = Arrays.asList(getResources().getStringArray(R.array.weight));
        ArrayAdapter<String> adapterWeight = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListWeight);
        spnWeight.setAdapter(adapterWeight);

        spnGender = (Spinner) view.findViewById(R.id.register_spn_gender);
        spnGender.setOnItemSelectedListener(this);
        arrayListGender = Arrays.asList(getResources().getStringArray(R.array.gender));
        ArrayAdapter<String> adapterGender = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListGender);
        spnGender.setAdapter(adapterGender);

        spnReligion = (Spinner) view.findViewById(R.id.register_spn_religion);
        arrayListReligion = SplashActivity.dbHandler.getData(DBHandler.TBL_RELIGION);
        ArrayAdapter<String> adapterReligion = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListReligion);
        spnReligion.setAdapter(adapterReligion);
        spnReligion.setOnItemSelectedListener(this);

        spnMotherTongue = (Spinner) view.findViewById(R.id.register_spn_tongue);
        spnMotherTongue.setOnItemSelectedListener(this);
        arrayListMTongue = SplashActivity.dbHandler.getData(DBHandler.TBL_MOTHER_TONGUE);
        ArrayAdapter<String> adapterTounge = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListMTongue);
        spnMotherTongue.setAdapter(adapterTounge);

        spnCaste = (Spinner) view.findViewById(R.id.register_spn_caste);
        spnCaste.setOnItemSelectedListener(this);
        String strCaste[] = getResources().getStringArray(R.array.caste);
        ArrayAdapter<String> adapterCaste = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, strCaste);
        spnCaste.setAdapter(adapterCaste);

        spnMaritalStatus = (Spinner) view.findViewById(R.id.register_spn_marital);
        spnMaritalStatus.setOnItemSelectedListener(this);
        arrayListMarital = Arrays.asList(getResources().getStringArray(R.array.marital_status));
        ArrayAdapter<String> adapterMarital = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListMarital);
        spnMaritalStatus.setAdapter(adapterMarital);

        spnManglik = (Spinner) view.findViewById(R.id.register_spn_manglik);
        spnManglik.setOnItemSelectedListener(this);
        arrayListManglik = Arrays.asList(getResources().getStringArray(R.array.mangalik));
        ArrayAdapter<String> adapterManglik = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListManglik);
        spnManglik.setAdapter(adapterManglik);

        spnCountry = (Spinner) view.findViewById(R.id.register_spn_country);
        spnCountry.setOnItemSelectedListener(this);
        arrayListCountry = SplashActivity.dbHandler.getData(DBHandler.TBL_COUNTRY);
        ArrayAdapter<String> adapterCountry = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListCountry);
        spnCountry.setAdapter(adapterCountry);

        spnState = (Spinner) view.findViewById(R.id.register_spn_state);
        spnState.setOnItemSelectedListener(this);
        String strState[] = getResources().getStringArray(R.array.state);
        ArrayAdapter<String> adapterState = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, strState);
        spnState.setAdapter(adapterState);

        spnCity = (Spinner) view.findViewById(R.id.register_spn_city);
        spnCity.setOnItemSelectedListener(this);
        String strCity[] = getResources().getStringArray(R.array.city);
        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, strCity);
        spnCity.setAdapter(adapterCity);

        Button button = (Button) view.findViewById(R.id.profile_btn_continue);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRegisterCondition();
            }
        });

        return view;
    }

    private void checkRegisterCondition() {
        valuePairsRegister.clear();
        strFirstName = edFirstName.getText().toString().trim();
        strEmail = edEmailID.getText().toString().trim();
        strPassword = edPassword.getText().toString().trim();
        strMobile = edMobileNo.getText().toString().trim();
        strDOB = edDOB.getText().toString().trim();
        if (strFirstName.length() < 1) {
            edFirstName.setError(getResources().getString(R.string.error_fname));
            edFirstName.requestFocus();
        } else if (strEmail.length() < 1 && Application.isValidEmail(strEmail)) {
            edEmailID.setError(getResources().getString(R.string.error_email_verify));
        } else if (strPassword.length() < 8) {
            edPassword.setError(getResources().getString(R.string.error_password_register));
        } else if (strMobile.length() < 10) {
            edMobileNo.setError(getResources().getString(R.string.error_mobile));
        } else if (strDOB.length() < 1) {
            edDOB.setError(getResources().getString(R.string.error_dob));
        } else {
            valuePairsRegister.add(new BasicNameValuePair("first_name", strFirstName));
            valuePairsRegister.add(new BasicNameValuePair("last_name", edLastName.getText().toString().trim()));
            valuePairsRegister.add(new BasicNameValuePair("email", strEmail));
            valuePairsRegister.add(new BasicNameValuePair("password", "" + strPassword));
            valuePairsRegister.add(new BasicNameValuePair("mobile_no", "" + strMobile));
            valuePairsRegister.add(new BasicNameValuePair("birthdate", "" + strDOB));
            valuePairsRegister.add(new BasicNameValuePair("height", strHeight));
            valuePairsRegister.add(new BasicNameValuePair("weight", strWeight));
            valuePairsRegister.add(new BasicNameValuePair("gender", strGender));
            valuePairsRegister.add(new BasicNameValuePair("religion_id", "" + strReligion));
            valuePairsRegister.add(new BasicNameValuePair("mothertongue", "" + strMTongue));
            valuePairsRegister.add(new BasicNameValuePair("my_caste", "" + strCaste));
            valuePairsRegister.add(new BasicNameValuePair("marital_status", "" + strMaritalStatus));
            valuePairsRegister.add(new BasicNameValuePair("manglik", "" + strManglik));
            valuePairsRegister.add(new BasicNameValuePair("country_id", "" + strCountry));
            valuePairsRegister.add(new BasicNameValuePair("state", "" + strState));
            valuePairsRegister.add(new BasicNameValuePair("city", "" + strCity));

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_right, 0, 0, R.anim.slide_out_right);
            transaction.replace(R.id.framelayout_profile, fragmentPartner);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void getWebServiceData_JSON(JSONObject jsonObject, int serviceCounter) {
        if (serviceCounter == SERVICE_ID_CASTE) { // caste
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
                }
                ArrayAdapter<String> adapterCaste = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, tempCaste);
                spnCaste.setAdapter(adapterCaste);
            } catch (Exception e) {
            }
        } else if (serviceCounter == SERVICE_ID_STATE) { // State
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
                GetDataUsingWService cityService = new GetDataUsingWService(getActivity(), urlCity, SERVICE_ID_CITY, nameValuePairs, this);
                Application.StartAsyncTaskInParallel(cityService);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (serviceCounter == SERVICE_ID_CITY) { // City
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
    public void onDestroyView() {
        super.onDestroyView();
        ((LoginActivity) getActivity()).getSupportActionBar().setTitle("Login Activity");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.register_spn_height:
                strHeight = arrayListHeight.get(position);
                break;
            case R.id.register_spn_weight:
                strWeight = arrayListWeight.get(position);
                break;
            case R.id.register_spn_gender:
                strGender = arrayListGender.get(position);
                break;
            case R.id.register_spn_religion:
                strReligion = SplashActivity.dbHandler.getIDUsingName(DBHandler.TBL_RELIGION, arrayListReligion.get(position));
//                //       Get Data Caste data from Web Service
                GetDataUsingWService casteService = new GetDataUsingWService(getActivity(), Application.URL_CASTE + strReligion, SERVICE_ID_CASTE, nameValuePairs, this);
                Application.StartAsyncTaskInParallel(casteService);

                break;
            case R.id.register_spn_tongue:
                strMTongue = SplashActivity.dbHandler.getIDUsingName(DBHandler.TBL_MOTHER_TONGUE, arrayListMTongue.get(position));
                break;
            case R.id.register_spn_caste:
                if (arrayListCaste.size() > 0) {
                    SpinnerItem spnCaste = arrayListCaste.get(position);
                    strCaste = spnCaste.getId();
                } else {
                    strCaste = "1";
                }
                break;
            case R.id.register_spn_marital:
                strMaritalStatus = arrayListMarital.get(position);
                break;
            case R.id.register_spn_manglik:
                strManglik = arrayListManglik.get(position);
                break;
            case R.id.register_spn_country:
                strCountry = SplashActivity.dbHandler.getIDUsingName(DBHandler.TBL_COUNTRY, arrayListCountry.get(position));
//                //       Get Data Country data from Web Service
                GetDataUsingWService stateService = new GetDataUsingWService(getActivity(), Application.URL_STATE + strCountry, SERVICE_ID_STATE, nameValuePairs, this);
                Application.StartAsyncTaskInParallel(stateService);
                break;
            case R.id.register_spn_state:
                if (arrayListState.size() > 1) {
                    SpinnerItem spinnerItem = arrayListState.get(position);
                    strState = spinnerItem.getId();
                    String urlCity = Application.URL_CITY + strCountry + "&state_id=" + strState;
//                //       Get Data Country data from Web Service
                    Log.d(TAG, "::::City " + urlCity);
                    GetDataUsingWService cityService = new GetDataUsingWService(getActivity(), urlCity, SERVICE_ID_CITY, nameValuePairs, this);
                    Application.StartAsyncTaskInParallel(cityService);
                } else {
                    strState = "6";
                }
                break;
            case R.id.register_spn_city:
                if (arrayListCity.size() > 0) {
                    SpinnerItem spnCity = arrayListCity.get(position);
                    strCity = spnCity.getId();
                } else {
                    strCity = "";
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
