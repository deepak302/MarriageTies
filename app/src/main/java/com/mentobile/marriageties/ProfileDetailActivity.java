package com.mentobile.marriageties;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProfileDetailActivity extends AppCompatActivity implements GetDataUsingWService.GetWebServiceData {

    private static final String TAG = "ProfileDetailActivity";
    private String matri_Id;
    ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
    List<String> listViewProfile = new ArrayList<>();

    private ViewPager viewPager;
    private TextView tvName;
    private TextView tvAge;
    private TextView tvHeight;
    private TextView tvWeight;
    private TextView tvMotherTongue;
    private TextView tvMarital;
    private TextView tvSkinTone;
    private TextView tvBodyType;
    private TextView tvEatingHabit;
    private TextView tvDrinking;
    private TextView tvBloodGroup;
    private TextView tvSmoking;
    private TextView tvProfileCreatedBy;
    private TextView tvReferencedBy;
    private TextView tvBirthPlace;
    private TextView tvBirthTime;
    private TextView tvOtherLangKnown;
    private TextView tvHobby;
    private TextView tvAbountMe;

    // Religion Information

    private TextView tvReligion;
    private TextView tvCaste;
    private TextView tvSubCaste;
    private TextView tvStar;
    private TextView tvManglik;
    private TextView tvGothra;
    private TextView tvHoroScope;
    private TextView tvMoonsign;

    // Location Information

    private TextView tvCountry;
    private TextView tvResidanceStatus;
    private TextView tvState;
    private TextView tvCity;

    // Education / Professional Information

    private TextView tvEducation;
    private TextView tvDesignation;
    private TextView tvEmployedIn;
    private TextView tvOccupation;
    private TextView tvIncome;

    // Family Status

    private TextView tvFamilyType;
    private TextView tvFamilyStatus;
    private TextView tvFatherName;
    private TextView tvMotherName;
    private TextView tvFatherOccupation;
    private TextView tvMotherOccupation;
    private TextView tvNoBrothers;
    private TextView tvNoSisters;
    private TextView tvMarriedBrothers;
    private TextView tvMarriedSisters;
    private TextView tvAboutMyFamily;

    // Partner Information;

    private TextView tvPartLookingFor;
    private TextView tvPartAgePreference;
    private TextView tvPartExpectations;
    private TextView tvPartHeight;
    private TextView tvPartTongue;
    private TextView tvPartBodyType;
    private TextView tvPartEating;
    private TextView tvPartSmoking;
    private TextView tvPartDrinking;
    private TextView tvPartSkinTone;

    // Partner Information -- Religious

    private TextView tvPartReligion;
    private TextView tvPartCaste;
    private TextView tvPartStar;
    private TextView tvPartManglik;

    // Partner Information -- Educational and PRofessional Information

    private TextView tvPartEducation;
    private TextView tvPartDesignation;
    private TextView tvPartInCome;
    private TextView tvPartOccupation;
    private TextView tvPartEmployedIn;

    // Partner Information -- Location

    private TextView tvPartCountry;
    private TextView tvPartCity;
    private TextView tvPartState;
    private TextView tvPartResidancyStatus;
    private URI uri;
    private ArrayList<String> listImagePath = new ArrayList<>();
    AdapterViewPager adapterViewPager;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.prj_dtl_viewpager);
        adapterViewPager = new AdapterViewPager(this, listImagePath);
        viewPager.setAdapter(adapterViewPager);

        tvName = (TextView) findViewById(R.id.view_profile_tv_name);
        tvAge = (TextView) findViewById(R.id.view_profile_tv_age);
        tvHeight = (TextView) findViewById(R.id.view_profile_tv_height);
        tvWeight = (TextView) findViewById(R.id.view_profile_tv_weight);
        tvMotherTongue = (TextView) findViewById(R.id.view_profile_tv_tounge);
        tvMarital = (TextView) findViewById(R.id.view_profile_tv_marital);
        tvSkinTone = (TextView) findViewById(R.id.view_profile_tv_skin_type);
        tvBodyType = (TextView) findViewById(R.id.view_profile_tv_body_type);
        tvEatingHabit = (TextView) findViewById(R.id.view_profile_tv_eating);
        tvDrinking = (TextView) findViewById(R.id.view_profile_tv_drinking);
        tvBloodGroup = (TextView) findViewById(R.id.view_profile_tv_blood);
        tvSmoking = (TextView) findViewById(R.id.view_profile_tv_smoking);
        tvProfileCreatedBy = (TextView) findViewById(R.id.view_profile_tv_profile_created_by);
        tvReferencedBy = (TextView) findViewById(R.id.view_profile_tv_referenced_by);
        tvBirthPlace = (TextView) findViewById(R.id.view_profile_tv_birth_place);
        tvBirthTime = (TextView) findViewById(R.id.view_profile_tv_birth_time);
        tvOtherLangKnown = (TextView) findViewById(R.id.view_profile_tv_other_language);
        tvHobby = (TextView) findViewById(R.id.view_profile_tv_hobby);
        tvAbountMe = (TextView) findViewById(R.id.view_profile_tv_about);

        // Religion Information

        tvReligion = (TextView) findViewById(R.id.view_profile_tv_religion);
        tvCaste = (TextView) findViewById(R.id.view_profile_tv_caste);
        tvSubCaste = (TextView) findViewById(R.id.view_profile_tv_sub_caste);
        tvStar = (TextView) findViewById(R.id.view_profile_tv_star);
        tvManglik = (TextView) findViewById(R.id.view_profile_tv_manglik);
        tvGothra = (TextView) findViewById(R.id.view_profile_tv_gothra);
        tvHoroScope = (TextView) findViewById(R.id.view_profile_tv_horoscope);
        tvMoonsign = (TextView) findViewById(R.id.view_profile_tv_moonsign);

        // Location Information

        tvCountry = (TextView) findViewById(R.id.view_profile_tv_country);
        tvResidanceStatus = (TextView) findViewById(R.id.view_profile_tv_residance_status);
        tvState = (TextView) findViewById(R.id.view_profile_tv_state);
        tvCity = (TextView) findViewById(R.id.view_profile_tv_city);

        // Education / Professional Information

        tvEducation = (TextView) findViewById(R.id.view_profile_tv_education);
        tvDesignation = (TextView) findViewById(R.id.view_profile_tv_designation);
        tvEmployedIn = (TextView) findViewById(R.id.view_profile_tv_employed);
        tvOccupation = (TextView) findViewById(R.id.view_profile_tv_occupation);
        tvIncome = (TextView) findViewById(R.id.view_profile_tv_income);

        // Family Status

        tvFamilyType = (TextView) findViewById(R.id.view_profile_tv_family);
        tvFamilyStatus = (TextView) findViewById(R.id.view_profile_tv_family_status);
        tvFatherName = (TextView) findViewById(R.id.view_profile_tv_father);
        tvMotherName = (TextView) findViewById(R.id.view_profile_tv_mother);
        tvFatherOccupation = (TextView) findViewById(R.id.view_profile_tv_father_occupation);
        tvMotherOccupation = (TextView) findViewById(R.id.view_profile_tv_mother_occupation);
        tvNoBrothers = (TextView) findViewById(R.id.view_profile_tv_no_brothers);
        tvNoSisters = (TextView) findViewById(R.id.view_profile_tv_no_sister);
        tvMarriedBrothers = (TextView) findViewById(R.id.view_profile_tv_married_brother);
        tvMarriedSisters = (TextView) findViewById(R.id.view_profile_tv_married_sister);
        tvAboutMyFamily = (TextView) findViewById(R.id.view_profile_tv_about_family);

        // Partner Information

        tvPartLookingFor = (TextView) findViewById(R.id.view_profile_tv_part_looking_for);
        tvPartAgePreference = (TextView) findViewById(R.id.view_profile_tv_part_age_preference);
        tvPartExpectations = (TextView) findViewById(R.id.view_profile_tv_part_expectations);
        tvPartHeight = (TextView) findViewById(R.id.view_profile_tv_part_height_preference);
        tvPartTongue = (TextView) findViewById(R.id.view_profile_tv_part_tongue);
        tvPartBodyType = (TextView) findViewById(R.id.view_profile_tv_part_body_type);
        tvPartEating = (TextView) findViewById(R.id.view_profile_tv_part_eating);
        tvPartSmoking = (TextView) findViewById(R.id.view_profile_tv_part_smoking);
        tvPartDrinking = (TextView) findViewById(R.id.view_profile_tv_part_drinking);
        tvPartSkinTone = (TextView) findViewById(R.id.view_profile_tv_part_skin_tone);

        // Partner Information -- Religious

        tvPartReligion = (TextView) findViewById(R.id.view_profile_tv_part_religion);
        tvPartCaste = (TextView) findViewById(R.id.view_profile_tv_part_caste);
        tvPartStar = (TextView) findViewById(R.id.view_profile_tv_part_star);
        tvPartManglik = (TextView) findViewById(R.id.view_profile_tv_part_manglik);

        // Partner Information -- Educational and PRofessional Information

        tvPartEducation = (TextView) findViewById(R.id.view_profile_tv_part_education);
        tvPartDesignation = (TextView) findViewById(R.id.view_profile_tv_part_designation);
        tvPartInCome = (TextView) findViewById(R.id.view_profile_tv_part_income);
        tvPartOccupation = (TextView) findViewById(R.id.view_profile_tv_part_occupation);
        tvPartEmployedIn = (TextView) findViewById(R.id.view_profile_tv_part_employed);

        // Partner Information -- Location

        tvPartCountry = (TextView) findViewById(R.id.view_profile_tv_part_country);
        tvPartCity = (TextView) findViewById(R.id.view_profile_tv_part_city);
        tvPartState = (TextView) findViewById(R.id.view_profile_tv_part_state);
        tvPartResidancyStatus = (TextView) findViewById(R.id.view_profile_tv_part_residancy_status);

        matri_Id = getIntent().getExtras().getString("matri_id");
        nameValuePairs.add(new BasicNameValuePair("matri_id", matri_Id));
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, Application.URL_VIEW_PROFILE,
                0, nameValuePairs, this);
        Application.StartAsyncTaskInParallel(getDataUsingWService);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_profile_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private String checkString(String data) {
        if (data == null || data.length() == 0 || data.equalsIgnoreCase("null")) {
            return "Not Available";
        } else
            return "" + data;
    }

    @Override
    public void getWebServiceData_JSON(JSONObject jsonObject, int serviceCounter) {
        if (serviceCounter == 0) {
            JSONArray jsonArray = null;
            try {
                jsonArray = jsonObject.getJSONArray("view_profile");
                Log.d(TAG, ":::::JSon " + jsonArray);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);

                    listImagePath.add(object.getString("photo1"));
                    adapterViewPager.notifyDataSetChanged();

                    tvName.setText(checkString(object.getString("username")));
                    tvAge.setText(checkString(object.getString("Age")));
                    tvHeight.setText(checkString(object.getString("height")));
                    tvWeight.setText(checkString(object.getString("weight")));
                    tvMarital.setText(checkString(object.getString("m_status")));
                    tvSkinTone.setText(checkString(object.getString("complexion")));
                    tvBodyType.setText(checkString(object.getString("bodytype")));
                    tvEatingHabit.setText(checkString(object.getString("diet")));
                    tvDrinking.setText(checkString(object.getString("drink")));
                    tvBloodGroup.setText(checkString(object.getString("b_group")));
                    tvSmoking.setText(checkString(object.getString("smoke")));
                    tvProfileCreatedBy.setText(checkString(object.getString("profileby")));
                    tvReferencedBy.setText(checkString(object.getString("reference")));
                    tvBirthPlace.setText(checkString(object.getString("birthplace")));
                    tvBirthTime.setText(checkString(object.getString("birthtime")));
                    tvOtherLangKnown.setText(checkString(object.getString("known_languages")));
                    tvHobby.setText(checkString(object.getString("hobby")));
                    tvAbountMe.setText(checkString(object.getString("profile_text")));//

                    tvReligion.setText(checkString(object.getString("religion_name")));
                    tvCaste.setText(checkString(object.getString("caste_name")));
                    tvSubCaste.setText(checkString(object.getString("subcaste")));
                    tvStar.setText(checkString(object.getString("star")));
                    tvManglik.setText(checkString(object.getString("manglik")));
                    tvGothra.setText(checkString(object.getString("gothra")));
                    tvHoroScope.setText(checkString(object.getString("horoscope")));
                    tvMoonsign.setText(checkString(object.getString("moonsign")));

                    tvCountry.setText(checkString(object.getString("country_name")));
                    tvResidanceStatus.setText(checkString(object.getString("residence")));
                    tvState.setText(checkString(object.getString("state_name")));
                    tvCity.setText(checkString(object.getString("city_name")));

                    tvEducation.setText(checkString(object.getString("edu_name")));
                    tvDesignation.setText(checkString(object.getString("desg_name")));
                    tvEmployedIn.setText(checkString(object.getString("emp_in")));
                    tvOccupation.setText(checkString(object.getString("ocp_name")));
                    tvIncome.setText(checkString(object.getString("income")));

                    tvFamilyType.setText(checkString(object.getString("family_type")));
                    tvFamilyStatus.setText(checkString(object.getString("family_status")));
                    tvFatherName.setText(checkString(object.getString("father_name")));
                    tvMotherName.setText(checkString(object.getString("mother_name")));
                    tvFatherOccupation.setText(checkString(object.getString("father_occupation")));
                    tvMotherOccupation.setText(checkString(object.getString("mother_occupation")));
                    tvNoBrothers.setText(checkString(object.getString("no_of_brothers")));
                    tvNoSisters.setText(checkString(object.getString("no_of_sisters")));
                    tvMarriedBrothers.setText(checkString(object.getString("no_marri_brother")));
                    tvMarriedSisters.setText(checkString(object.getString("no_marri_sister")));
                    tvAboutMyFamily.setText(checkString(object.getString("family_details")));

                    tvPartLookingFor.setText(checkString(object.getString("looking_for")));
                    tvPartAgePreference.setText(checkString(object.getString("part_to_age")));
                    tvPartExpectations.setText(checkString(object.getString("part_expect")));
                    tvPartHeight.setText(checkString(object.getString("part_height")));
                    tvPartTongue.setText(checkString(object.getString("part_mtongue")));
                    tvPartBodyType.setText(checkString(object.getString("part_bodytype")));
                    tvPartEating.setText(checkString(object.getString("part_diet")));
                    tvPartSmoking.setText(checkString(object.getString("part_smoke")));
                    tvPartDrinking.setText(checkString(object.getString("part_drink")));
                    tvPartSkinTone.setText(checkString(object.getString("part_complexion")));

                    tvPartReligion.setText(checkString(object.getString("part_religion")));
                    tvPartCaste.setText(checkString(object.getString("part_caste")));
                    tvPartStar.setText(checkString(object.getString("part_star")));
                    tvPartManglik.setText(checkString(object.getString("part_manglik")));

                    tvPartEducation.setText(checkString(object.getString("part_edu")));
                    tvPartDesignation.setText(checkString(object.getString("part_designation")));
                    tvPartInCome.setText(checkString(object.getString("part_income")));
                    tvPartOccupation.setText(checkString(object.getString("part_occupation")));
                    tvPartEmployedIn.setText(checkString(object.getString("part_emp_in")));

                    tvPartCountry.setText(checkString(object.getString("part_country")));
                    tvPartCity.setText(checkString(object.getString("part_city")));
                    tvPartState.setText(checkString(object.getString("part_state")));
                    tvPartResidancyStatus.setText(checkString(object.getString("part_resi_status")));

                    Log.d(TAG, ":::::Username " + object.getString("username"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
