package com.mentobile.marriageties;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mentobile.utility.DBHandler;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, GetDataUsingWService.GetWebServiceData {

    private static final String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;
    private ViewPager viewPager;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private NvDrawerAdapter nvAdapter;
    Toolbar toolbar;
    ArrayList<NvItems> arrNVItems = new ArrayList<NvItems>();
    private TextView tvNVEmail;
    private TextView tvNVName;
    private ListView lvFilterData;
    private ArrayList<ProfileShorted> storyArrayList = new ArrayList<>();
    private AdapterShortedProfile adapterShortedProfile;

    private EditText edSearchData;

    private int nvIcon[] = {R.drawable.register, R.drawable.success, R.drawable.search, R.drawable.payment, R.drawable.online,
            R.drawable.contact, R.drawable.privacy_policy, R.drawable.refund, R.drawable.terms, R.mipmap.ic_launcher};

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Do you want to exit this application?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setProfile();
    }

    private void setProfile() {
        String strMatriID = Application.getDataFromSharedPreference(this, Application.SP_LOGIN_LOGOUT, "matri_id");
        if (strMatriID != null) {
            strMatriID = strMatriID.toUpperCase(Locale.US);
            tvNVName.setText(strMatriID);
            tvNVEmail.setText("");
            NvItems items = (arrNVItems).get(arrNVItems.size() - 1);
            items.setTitle(getString(R.string.prompt_logout));
            nvAdapter.notifyDataSetChanged();
            Profile profile = Profile.getProfile();
            profile.setEmailID(strMatriID);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        LayoutInflater inflater = getLayoutInflater();
        final ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.nv_header, null, false);
        tvNVEmail = (TextView) viewGroup.findViewById(R.id.nvheader_tv_pemail);
        tvNVName = (TextView) viewGroup.findViewById(R.id.nvheader_tv_pname);
        mDrawerList.addHeaderView(viewGroup, null, false);

        ActionBar actionBar = getSupportActionBar();
        String nv_array[] = getResources().getStringArray(R.array.prompt_nv_drawer);
        for (int i = 0; i < nv_array.length; i++) {
            NvItems items = new NvItems(nvIcon[i], nv_array[i], null, false);
            arrNVItems.add(items);
        }
        nvAdapter = new NvDrawerAdapter(getApplicationContext(), R.layout.nv_drawer_row, arrNVItems);
        mDrawerList.setAdapter(nvAdapter);
        mDrawerList.setOnItemClickListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

//        edSearchData = (EditText) findViewById(R.id.main_ed_anyType);
//        edSearchData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentSearch = new Intent(MainActivity.this, SearchActivity.class);
//                startActivity(intentSearch);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//            }
//        });

        lvFilterData = (ListView) findViewById(R.id.main_list_data);
        lvFilterData.setOnItemClickListener(this);
        for (int i = 0; i < 15; i++) {
            ProfileShorted profileShorted = new ProfileShorted(i, "Name " + i, "" + 20 + i, "5 Ft 6 in", "Hindu", "Brahmin", "Gaur", "MCA", "Gurgaon", "Haryana", "India");
            storyArrayList.add(profileShorted);
        }
        adapterShortedProfile = new AdapterShortedProfile(getApplicationContext(), R.layout.row_list_shortlisted, storyArrayList);
        lvFilterData.setAdapter(adapterShortedProfile);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.main_list_data) {
            Log.d(TAG, "::::Start Animation");
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.aplha);
            view.startAnimation(animation);

            Intent intent = new Intent(MainActivity.this, ProfileDetailActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


        } else {
            switch (position) {
                case 0:// header

                    break;
                case 1://Success Story
                    Intent intentSuccessStory = new Intent(MainActivity.this, SuccessStoryActivity.class);
                    startActivity(intentSuccessStory);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;
                case 2:// Search
                    Intent intentSearch = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(intentSearch);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;
                case 3:// Payment
                    Intent intentPayment = new Intent(MainActivity.this, PaymentActivity.class);
                    startActivity(intentPayment);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;
                case 4:// Online Member
                    Intent intentOnlineMember = new Intent(MainActivity.this, OnlineMemberActivity.class);
                    startActivity(intentOnlineMember);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;
                case 5:// Contact
                    Intent intentContact = new Intent(MainActivity.this, ContactActivity.class);
                    startActivity(intentContact);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;
                case 6:// Privacy Policy
                    Intent intentPrivacyPolicy = new Intent(MainActivity.this, PrivacyPolicyActivity.class);
                    startActivity(intentPrivacyPolicy);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;
                case 7: // Refund Policy
                    Intent intentRefundPolicy = new Intent(MainActivity.this, RefundPolicyActivity.class);
                    startActivity(intentRefundPolicy);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    break;
                case 8: // Terms and Condition
                    Intent intentTermsandCondition = new Intent(MainActivity.this, Terms_ConditionActivity.class);
                    startActivity(intentTermsandCondition);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;
                case 9: // Sign In And Sign Out
                    if (Application.getDataFromSharedPreference(this, Application.SP_LOGIN_LOGOUT, "matri_id") != null) {
                        Application.clearSharedPreferenceFile(this, Application.SP_LOGIN_LOGOUT);
                        NvItems items = arrNVItems.get(position - 1);
                        items.setTitle(getString(R.string.prompt_login));
                        nvAdapter.notifyDataSetChanged();
                        tvNVEmail.setText("");
                        tvNVName.setText("Your Matrimonial ID");
                    }
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent intentSearch = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intentSearch);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getWebServiceData_JSON(JSONObject jsonObject, int serviceCounter) {
        Log.d(TAG, "::::::Service Data " + jsonObject + " Counter " + serviceCounter);
    }
}
