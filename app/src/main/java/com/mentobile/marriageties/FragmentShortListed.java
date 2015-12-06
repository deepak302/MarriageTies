package com.mentobile.marriageties;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentShortListed extends Fragment implements GetDataUsingWService.GetWebServiceData, AdapterView.OnItemClickListener {

    private static final String TAG = "FragmentShortListed";
    private ListView lvShortedProfile;
    private AdapterShortedProfile adapterShorted;
    ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
    ArrayList<ProfileShorted> arrListShorted = new ArrayList<>();

    public FragmentShortListed() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nameValuePairs.add(new BasicNameValuePair("user_id", Profile.getProfile().getEmailID()));
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(getActivity(), Application.URL_PROFILE_SHORTED, 0, nameValuePairs, this);
        Application.StartAsyncTaskInParallel(getDataUsingWService);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_short_listed, container, false);

        lvShortedProfile = (ListView) view.findViewById(R.id.short_list_data);
        lvShortedProfile.setOnItemClickListener(this);
//        for (int i = 0; i < 15; i++) {
//            ProfileShorted profileShorted = new ProfileShorted("", "MatriID" + i, "Deepak" + i, "age" + i, "height" + i, "religion" + i, "caste" + i, "gotra" + i, "education" + i, "city" + i, "state" + i, "country" + i);
//            arrListShorted.add(profileShorted);
//        }
        adapterShorted = new AdapterShortedProfile(getActivity(), R.layout.row_list_shortlisted, arrListShorted);
        lvShortedProfile.setAdapter(adapterShorted);

        return view;
    }

    @Override
    public void getWebServiceData_JSON(JSONObject jsonObject, int serviceCounter) {
        if (serviceCounter == 0) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("shortlist");
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
                    ProfileShorted profileShorted = new ProfileShorted(photoPath, matri_id, name, age, height, religion, caste, gotra, education, city, state, country);
                    arrListShorted.add(profileShorted);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapterShorted.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.aplha);
//        view.startAnimation(animation);
//
//        Intent intent = new Intent(getActivity(), ProfileDetailActivity.class);
//        intent.putExtra("matri_id", arrListBlocked.get(position).getId());
//        startActivity(intent);
//        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
