package com.mentobile.marriageties;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.mentobile.utility.CProgressDialog;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FragmentMatchProfile extends Fragment implements AdapterView.OnItemClickListener, GetDataUsingWService.GetWebServiceData {

    private static final String TAG = "FragmentMatchProfile";
    private ListView lvFilterData;
    private AdapterShortedProfile adapterShortedProfile;
    ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
    ArrayList<ProfileShorted> matchProfileList = new ArrayList<>();

    public FragmentMatchProfile() {
        // Required empty public constructor
        Log.d(TAG, ":::::On Constructor");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nameValuePairs.add(new BasicNameValuePair("user_id", Profile.getProfile().getEmailID()));
        Log.d(TAG, ":::::On Create "+Profile.getProfile().getEmailID());
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(getActivity(), Application.URLM_SEARCH, 0, nameValuePairs, this);
        Application.StartAsyncTaskInParallel(getDataUsingWService);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, ":::::On Create View");
        View view = inflater.inflate(R.layout.fragment_match_profile, container, false);

        lvFilterData = (ListView) view.findViewById(R.id.main_list_data);
        lvFilterData.setOnItemClickListener(this);

        adapterShortedProfile = new AdapterShortedProfile(getActivity(), R.layout.row_list_shortlisted, matchProfileList);
        lvFilterData.setAdapter(adapterShortedProfile);

        return view;
    }

    @Override
    public void getWebServiceData_JSON(JSONObject jsonObject, int serviceCounter) {
        if (serviceCounter == 0) {
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

                    String shorted = jsonObject1.getString("short_status");
                    String blocked = jsonObject1.getString("block_status");

                    Log.d(TAG,"::::Short "+shorted + " :::Blocked "+blocked);

                    ProfileShorted profileShorted = new ProfileShorted(photoPath, matri_id, name, age, height, religion, caste, gotra, education, city, state, country,shorted, blocked);
                    matchProfileList.add(profileShorted);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapterShortedProfile.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.aplha);
        view.startAnimation(animation);

        Intent intent = new Intent(getActivity(), ProfileDetailActivity.class);
        intent.putExtra("matri_id", matchProfileList.get(position).getId());
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
