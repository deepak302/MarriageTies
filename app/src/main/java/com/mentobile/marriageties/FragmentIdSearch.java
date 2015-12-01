package com.mentobile.marriageties;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mentobile.utility.CProgressDialog;
import com.mentobile.utility.WebService;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentIdSearch extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "idSearch";
    ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();

    private Spinner spnGender;
    private EditText edID;

    private Button btnSearch;
    private Button btnSaveSearch;

    List<String> listGender = new ArrayList<>();

    private String strGender = "";
    private String strid = "";
    //http://matrimonialscript.in/ultimate_webservices/search.php?id_search=NE1&user_id=NE37

    public FragmentIdSearch() {
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
        View view = inflater.inflate(R.layout.fragment_id_search, container, false);

        spnGender = (Spinner) view.findViewById(R.id.search_id_spn_gender);
        spnGender.setOnItemSelectedListener(this);

        listGender = Arrays.asList(getResources().getStringArray(R.array.search_gender_type));
        ArrayAdapter<String> adapterGender = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listGender);
        spnGender.setAdapter(adapterGender);

        edID = (EditText) view.findViewById(R.id.search_id_ed_keyword);

        btnSearch = (Button) view.findViewById(R.id.search_id_btn_search);
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
        strid = edID.getText().toString().trim();
        if (!TextUtils.isEmpty(strid)) {
            nameValuePairs.add(new BasicNameValuePair("user_id", Profile.getProfile().getEmailID()));
            nameValuePairs.add(new BasicNameValuePair("gender", strGender));
            nameValuePairs.add(new BasicNameValuePair("id_search", strid));

            MyAsynchTask myAsynchTask = new MyAsynchTask();
            myAsynchTask.execute();
        } else {
            Toast.makeText(getActivity(), "Please enter the Matrimonial Id", Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "::::::NameValuePair " + nameValuePairs.toString());
    }

    private CProgressDialog cProgressDialog;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        strGender = listGender.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

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
                    String education = jsonObject1.getString("edu_name");
                    String city = jsonObject1.getString("city_name");
                    String state = jsonObject1.getString("state_name");
                    String country = jsonObject1.getString("country_name");
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

}
