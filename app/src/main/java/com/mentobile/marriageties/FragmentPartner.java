package com.mentobile.marriageties;


import android.app.Activity;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.mentobile.utility.DBHandler;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentPartner extends Fragment implements AdapterView.OnItemSelectedListener, GetDataUsingWService.GetWebServiceData {

    private static final String TAG = "FragmentPartner";
    private static final int SERVICE_ID_CASTE = 1;

    private Spinner spnEducation;
    private Spinner spnEmployee;
    private Spinner spnOccupation;
    private Spinner spnDesignation;
    private Spinner spnIncome;
    private Spinner spnPartLooking;
    private Spinner spnPartStartAge;
    private Spinner spnPartEndAge;
    private Spinner spnPartCountry;
    private Spinner spnPartReligion;
    private Spinner spnPartCaste;
    private Spinner spnPartStartHeight;
    private Spinner spnPartEndHeight;
    private Spinner spnPartEducation;
    private Spinner spnPartComplexion;
    private Spinner spnPartTongue;
    private Spinner spnPartIncome;

    private EditText edAbout;

    ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();

    private List<String> listEducation = new ArrayList<>();
    private List<String> listEmployee = new ArrayList<>();
    private List<String> listOccupation = new ArrayList<>();
    private List<String> listDesignation = new ArrayList<>();
    private List<String> listIncome = new ArrayList<>();
    private List<String> listPLooking = new ArrayList<>();
    private List<String> listPAge = new ArrayList<>();
    private List<String> listPCountry = new ArrayList<>();
    private List<String> listPReligion = new ArrayList<>();
    private List<SpinnerItem> listPCaste = new ArrayList<>();
    private List<String> listPHeight = new ArrayList<>();
    private List<String> listPComplexion = new ArrayList<>();
    private List<String> listPTongue = new ArrayList<>();


    private String strEducation = "";
    private String strEmployee = "";
    private String strOccupation = "";
    private String strDesignation = "";
    private String strIncome = "";
    private String strPartLooking = "";
    private String strAbout = "";
    private String strPartStartAge = "";
    private String strPartEndAge = "";
    private String strPartCountry = "";
    private String strPartReligion = "";
    private String strPartCaste = "";
    private String strPartStartHeight = "";
    private String strPartEndHeight = "";
    private String strPartEducation = "";
    private String strPartComplexion = "";
    private String strPartTongue = "";
    private String strPartIncome = "";

    FragmentRegComplete fragmentRegComplete ;

    public FragmentPartner() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, ":::::On Attatch");
        RelativeLayout relativeLayout = (RelativeLayout)getActivity().findViewById(R.id.framelayout_profile);
        relativeLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, ":::::On DeAttatch");
        RelativeLayout relativeLayout = (RelativeLayout)getActivity().findViewById(R.id.framelayout_profile);
        relativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentRegComplete = new FragmentRegComplete();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_partner, container, false);

        edAbout = (EditText) view.findViewById(R.id.register_edt_about);

        spnEducation = (Spinner) view.findViewById(R.id.register_spn_education);
        spnEducation.setOnItemSelectedListener(this);
        listEducation = SplashActivity.dbHandler.getData(DBHandler.TBL_EDUCATION);
        ArrayAdapter<String> adapterEducation = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listEducation);
        spnEducation.setAdapter(adapterEducation);


        spnEmployee = (Spinner) view.findViewById(R.id.register_spn_employee);
        spnEmployee.setOnItemSelectedListener(this);
        listEmployee = Arrays.asList(getResources().getStringArray(R.array.employed));
        ArrayAdapter<String> adapterEmployee = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listEmployee);
        spnEmployee.setAdapter(adapterEmployee);


        spnOccupation = (Spinner) view.findViewById(R.id.register_spn_occupation);
        spnOccupation.setOnItemSelectedListener(this);
        listOccupation = SplashActivity.dbHandler.getData(DBHandler.TBL_OCCUPATION);
        ArrayAdapter<String> adapterOccupation = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listOccupation);
        spnOccupation.setAdapter(adapterOccupation);

        spnDesignation = (Spinner) view.findViewById(R.id.register_spn_designation);
        spnDesignation.setOnItemSelectedListener(this);
        listDesignation = SplashActivity.dbHandler.getData(DBHandler.TBL_DESIGNATION);
        ArrayAdapter<String> adapterDesi = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listDesignation);
        spnDesignation.setAdapter(adapterDesi);

        spnIncome = (Spinner) view.findViewById(R.id.register_spn_income);
        spnIncome.setOnItemSelectedListener(this);
        listIncome = Arrays.asList(getResources().getStringArray(R.array.annual_income));
        ArrayAdapter<String> adapterIncome = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listIncome);
        spnIncome.setAdapter(adapterIncome);

        spnPartLooking = (Spinner) view.findViewById(R.id.register_spn_p_looking);
        spnPartLooking.setOnItemSelectedListener(this);
        listPLooking = Arrays.asList(getResources().getStringArray(R.array.looking));
        ArrayAdapter<String> adapterLooking = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listPLooking);
        spnPartLooking.setAdapter(adapterLooking);

        spnPartStartAge = (Spinner) view.findViewById(R.id.register_spn_p_start_age);
        spnPartStartAge.setOnItemSelectedListener(this);
        listPAge = Arrays.asList(getResources().getStringArray(R.array.age));
        ArrayAdapter<String> adapterStartAge = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listPAge);
        spnPartStartAge.setAdapter(adapterStartAge);

        spnPartEndAge = (Spinner) view.findViewById(R.id.register_spn_p_end_age);
        spnPartEndAge.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterEndAge = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listPAge);
        spnPartEndAge.setAdapter(adapterEndAge);

        spnPartCountry = (Spinner) view.findViewById(R.id.register_spn_p_country);
        spnPartCountry.setOnItemSelectedListener(this);
        listPCountry = SplashActivity.dbHandler.getData(DBHandler.TBL_COUNTRY);
        ArrayAdapter<String> adapterCountry = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listPCountry);
        spnPartCountry.setAdapter(adapterCountry);

        spnPartReligion = (Spinner) view.findViewById(R.id.register_spn_p_religion);
        spnPartReligion.setOnItemSelectedListener(this);
        listPReligion = SplashActivity.dbHandler.getData(DBHandler.TBL_RELIGION);
        ArrayAdapter<String> adapterReligion = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listPReligion);
        spnPartReligion.setAdapter(adapterReligion);

        spnPartCaste = (Spinner) view.findViewById(R.id.register_spn_p_caste);
        spnPartCaste.setOnItemSelectedListener(this);
        String[] strCaste = getResources().getStringArray(R.array.caste);
        ArrayAdapter<String> adapterCaste = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, strCaste);
        spnPartCaste.setAdapter(adapterCaste);

        spnPartStartHeight = (Spinner) view.findViewById(R.id.register_spn_p_start_height);
        spnPartStartHeight.setOnItemSelectedListener(this);
        listPHeight = Arrays.asList(getResources().getStringArray(R.array.height));
        ArrayAdapter<String> adapterStartHeight = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listPHeight);
        spnPartStartHeight.setAdapter(adapterStartHeight);

        spnPartEndHeight = (Spinner) view.findViewById(R.id.register_spn_p_end_height);
        spnPartEndHeight.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterEndHeight = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listPHeight);
        spnPartEndHeight.setAdapter(adapterEndHeight);

        spnPartEducation = (Spinner) view.findViewById(R.id.register_spn_p_education);
        spnPartEducation.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterPEducation = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listEducation);
        spnPartEducation.setAdapter(adapterPEducation);

        spnPartComplexion = (Spinner) view.findViewById(R.id.register_spn_p_complexion);
        spnPartComplexion.setOnItemSelectedListener(this);
        listPComplexion = Arrays.asList(getResources().getStringArray(R.array.complexian));
        ArrayAdapter<String> adapteComp = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listPComplexion);
        spnPartComplexion.setAdapter(adapteComp);

        spnPartTongue = (Spinner) view.findViewById(R.id.register_spn_p_tongue);
        spnPartTongue.setOnItemSelectedListener(this);
        listPTongue = SplashActivity.dbHandler.getData(DBHandler.TBL_MOTHER_TONGUE);
        ArrayAdapter<String> adapterTongue = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listPTongue);
        spnPartTongue.setAdapter(adapterTongue);

        spnPartIncome = (Spinner) view.findViewById(R.id.register_spn_p_income);
        spnPartIncome.setOnItemSelectedListener(this);
        listIncome = Arrays.asList(getResources().getStringArray(R.array.annual_income));
        ArrayAdapter<String> adapterPIncome = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listIncome);
        spnPartIncome.setAdapter(adapterPIncome);

        Button button = (Button) view.findViewById(R.id.partner_btn_continue1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "::::::Button Click");
                checkPartnerRegistration();
            }
        });

        return view;
    }

    private void checkPartnerRegistration() {

        strAbout = edAbout.getText().toString().trim();
        if (strAbout.length() < 50) {
            edAbout.setError(getResources().getString(R.string.error_abount));
        } else {
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("edu_id", strEducation));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("employed_in", strEmployee));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("occupation", strOccupation));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("designation", strDesignation));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("annual_income", strIncome));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("profile_text", strAbout));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("looking_for", strPartLooking));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("pfrom_age", strPartStartAge));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("pto_age", strPartEndAge));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("pcountry", strPartCountry));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("part_religion_id", strPartReligion));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("part_caste_id", strPartCaste));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("pform_height", strPartStartHeight));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("pto_height", strPartEndHeight));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("peducation", strPartEducation));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("pcomplexion", strPartComplexion));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("pmtongue", strPartTongue));
            RegisterActivity.valuePairsRegister.add(new BasicNameValuePair("pannual_income", strPartIncome));


        FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
        transaction1.setCustomAnimations(R.anim.slide_in_right, 0, 0, R.anim.slide_out_right);
        transaction1.replace(android.R.id.content, fragmentRegComplete);
        transaction1.addToBackStack(null);
        transaction1.commit();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.register_spn_education:
                strEducation = SplashActivity.dbHandler.getIDUsingName(DBHandler.TBL_EDUCATION, listEducation.get(position));
                break;
            case R.id.register_spn_employee:
                strEmployee = listEmployee.get(position);
                break;
            case R.id.register_spn_occupation:
                strOccupation = SplashActivity.dbHandler.getIDUsingName(DBHandler.TBL_OCCUPATION, listOccupation.get(position));
                break;
            case R.id.register_spn_designation:
                strDesignation = SplashActivity.dbHandler.getIDUsingName(DBHandler.TBL_DESIGNATION, listDesignation.get(position));
                break;
            case R.id.register_spn_income:
                strIncome = listIncome.get(position);
                break;
            case R.id.register_spn_p_looking:
                strPartLooking = listPLooking.get(position);
                break;
            case R.id.register_spn_p_start_age:
                strPartStartAge = listPAge.get(position);
                break;
            case R.id.register_spn_p_end_age:
                strPartEndAge = listPAge.get(position);
                break;
            case R.id.register_spn_p_country:
                strPartCountry = listPCountry.get(position);
                strPartCountry = SplashActivity.dbHandler.getIDUsingName(DBHandler.TBL_COUNTRY, strPartCountry);
                break;
            case R.id.register_spn_p_religion:
                strPartReligion = listPReligion.get(position);
                strPartReligion = SplashActivity.dbHandler.getIDUsingName(DBHandler.TBL_RELIGION, strPartReligion);
                GetDataUsingWService casteService = new GetDataUsingWService(getActivity(), Application.URL_CASTE_MULTIPLE + strPartReligion, SERVICE_ID_CASTE, nameValuePairs, this);
                Application.StartAsyncTaskInParallel(casteService);
                break;
            case R.id.register_spn_p_caste:
                if (listPCaste.size() > 0) {
                    SpinnerItem spnCaste = listPCaste.get(position);
                    strPartCaste = spnCaste.getId();
                } else {
                    strPartCaste = "1";
                }
                break;
            case R.id.register_spn_p_start_height:
                strPartStartHeight = listPHeight.get(position);
                break;
            case R.id.register_spn_p_end_height:
                strPartEndHeight = listPHeight.get(position);
                break;
            case R.id.register_spn_p_education:
                strPartEducation = SplashActivity.dbHandler.getIDUsingName(DBHandler.TBL_EDUCATION, listEducation.get(position));
                break;
            case R.id.register_spn_p_complexion:
                strPartComplexion = listPComplexion.get(position);
                break;
            case R.id.register_spn_p_tongue:
                strPartTongue = listPTongue.get(position);
                strPartTongue = SplashActivity.dbHandler.getIDUsingName(DBHandler.TBL_MOTHER_TONGUE, strPartTongue);
                break;
            case R.id.register_spn_p_income:
                strPartIncome = listIncome.get(position);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void getWebServiceData_JSON(JSONObject jsonObject, int serviceCounter) {
        if (serviceCounter == SERVICE_ID_CASTE) { // caste
            try {
                listPCaste.clear();
                ArrayList<String> tempCaste = new ArrayList<>();
                JSONArray jsonarray = jsonObject.getJSONArray("caste");
                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonObject = jsonarray.getJSONObject(i);
                    // Populate spinner with country names
                    String id = jsonObject.getString("caste_id");
                    String name = jsonObject.getString("caste_name");
                    SpinnerItem spinnerItem = new SpinnerItem(id, name);
                    listPCaste.add(spinnerItem);
                    tempCaste.add(name);
                }
                ArrayAdapter<String> adapterCaste = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, tempCaste);
                spnPartCaste.setAdapter(adapterCaste);
            } catch (Exception e) {
            }
        }
    }
}
