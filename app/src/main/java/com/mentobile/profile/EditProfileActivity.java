package com.mentobile.profile;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.mentobile.marriageties.R;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imgBtnBasicDetail;
    private ImageButton imgBtnAboutMe;
    private ImageButton imgBtnReligiousDetail;
    private ImageButton imgBtnLocationInfo;
    private ImageButton imgBtnEducationInfo;
    private ImageButton imgBtnFamilyDetail;
    private ImageButton imgBtnPartBasicDetail;
    private ImageButton imgBtnPartReligious;
    private ImageButton imgBtnPartEduDetail;
    private ImageButton imgBtnPartLocation;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private FragmentBasicDetail fragmentBasicDetail;
    private FragmentEditAboutMe fragmentEditAboutMe;
    private FragmentEditReligiousInfo fragmentEditReligiousInfo;
    private FragmentEditLocationInfo fragmentEditLocationInfo;
    private FragmentEditEduInfo fragmentEditEduInfo;
    private FragmentEditFamilyDetail fragmentEditFamilyDetail;
    private FragmentPartEditBasicDetail fragmentPartEditBasicDetail;
    private FragmentPartEditReligious fragmentPartEditReligious;
    private FragmentPartEditEduInfo fragmentPartEditEduInfo;
    private FragmentPartEditLocation fragmentPartEditLocation;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgBtnBasicDetail = (ImageButton) findViewById(R.id.edit_profile_imgbtn_basic_detail);
        imgBtnBasicDetail.setOnClickListener(this);

        imgBtnAboutMe = (ImageButton) findViewById(R.id.edit_profile_imgbtn_aboutme);
        imgBtnAboutMe.setOnClickListener(this);

        imgBtnReligiousDetail = (ImageButton) findViewById(R.id.edit_profile_imgbtn_religious);
        imgBtnReligiousDetail.setOnClickListener(this);

        imgBtnLocationInfo = (ImageButton) findViewById(R.id.edit_profile_imgbtn_location);
        imgBtnLocationInfo.setOnClickListener(this);

        imgBtnEducationInfo = (ImageButton) findViewById(R.id.edit_profile_imgbtn_edu_professin);
        imgBtnEducationInfo.setOnClickListener(this);

        imgBtnFamilyDetail = (ImageButton) findViewById(R.id.edit_profile_imgbtn_family_detail);
        imgBtnFamilyDetail.setOnClickListener(this);

        imgBtnPartBasicDetail = (ImageButton) findViewById(R.id.edit_profile_imgbtn_part_basic_preference);
        imgBtnPartBasicDetail.setOnClickListener(this);

        imgBtnPartReligious = (ImageButton) findViewById(R.id.edit_profile_imgbtn_part_religious);
        imgBtnPartReligious.setOnClickListener(this);

        imgBtnPartEduDetail = (ImageButton) findViewById(R.id.edit_profile_imgbtn_part_edu_profession);
        imgBtnPartEduDetail.setOnClickListener(this);

        imgBtnPartLocation = (ImageButton) findViewById(R.id.edit_profile_imgbtn_part_location);
        imgBtnPartLocation.setOnClickListener(this);

        manager = getSupportFragmentManager();

        fragmentBasicDetail = new FragmentBasicDetail();
        fragmentEditAboutMe = new FragmentEditAboutMe();
        fragmentEditReligiousInfo = new FragmentEditReligiousInfo();
        fragmentEditLocationInfo = new FragmentEditLocationInfo();
        fragmentEditEduInfo = new FragmentEditEduInfo();
        fragmentEditFamilyDetail = new FragmentEditFamilyDetail();
        fragmentPartEditBasicDetail = new FragmentPartEditBasicDetail();
        fragmentPartEditReligious = new FragmentPartEditReligious();
        fragmentPartEditEduInfo = new FragmentPartEditEduInfo();
        fragmentPartEditLocation = new FragmentPartEditLocation();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        transaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.edit_profile_imgbtn_basic_detail:
                transaction.replace(android.R.id.content, fragmentBasicDetail);
                transaction.setCustomAnimations(R.anim.slide_in_right, 0, 0, R.anim.slide_out_right);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.edit_profile_imgbtn_aboutme:
                transaction.replace(android.R.id.content, fragmentEditAboutMe);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.edit_profile_imgbtn_religious:
                transaction.replace(android.R.id.content, fragmentEditReligiousInfo);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.edit_profile_imgbtn_location:
                transaction.replace(android.R.id.content, fragmentEditLocationInfo);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.edit_profile_imgbtn_edu_professin:
                transaction.replace(android.R.id.content, fragmentEditEduInfo);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.edit_profile_imgbtn_family_detail:
                transaction.replace(android.R.id.content, fragmentEditFamilyDetail);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.edit_profile_imgbtn_part_basic_preference:
                transaction.replace(android.R.id.content, fragmentPartEditBasicDetail);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.edit_profile_imgbtn_part_religious:
                transaction.replace(android.R.id.content, fragmentPartEditReligious);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.edit_profile_imgbtn_part_edu_profession:
                transaction.replace(android.R.id.content, fragmentPartEditEduInfo);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.edit_profile_imgbtn_part_location:
                transaction.replace(android.R.id.content, fragmentPartEditLocation);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
    }
}
