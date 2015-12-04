package com.mentobile.marriageties;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mentobile.utility.CProgressDialog;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

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
    private CircularImageView circularImageView;
    private ListView lvFilterData;
    static ArrayList<ProfileShorted> sortedProfileList = new ArrayList<>();
    ArrayList<ProfileShorted> matchProfileList = new ArrayList<>();
    private AdapterShortedProfile adapterShortedProfile;
    CProgressDialog cProgressDialog;
    private EditText edSearchData;
    ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
    String strLoginID;

    private int nvIcon[] = {
            R.drawable.register,
            R.drawable.success,
            R.drawable.search,
            R.drawable.payment,
            R.drawable.online,
            R.drawable.contact,
            R.drawable.privacy_policy,
            R.drawable.refund,
            R.drawable.terms,
            R.mipmap.ic_launcher};
    private URI uri;

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
        strLoginID = Application.getDataFromSharedPreference(this, Application.SP_LOGIN_LOGOUT, "matri_id");
        Log.d(TAG, ":::::Matri Id " + strLoginID);
        if (strLoginID != null) {
            ArrayList<NameValuePair> valuePairsID = new ArrayList<>();
            valuePairsID.add(new BasicNameValuePair("matri_id", strLoginID));
            GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, Application.URL_VIEW_PROFILE, 1, valuePairsID, this);
            Application.StartAsyncTaskInParallel(getDataUsingWService);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cProgressDialog = new CProgressDialog(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        LayoutInflater inflater = getLayoutInflater();
        final ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.nv_header, null, false);
        tvNVEmail = (TextView) viewGroup.findViewById(R.id.nvheader_tv_pemail);
        tvNVName = (TextView) viewGroup.findViewById(R.id.nvheader_tv_pname);
        circularImageView = (CircularImageView) viewGroup.findViewById(R.id.nv_header_img_pphoto);
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

        nameValuePairs.add(new BasicNameValuePair("user_id", Profile.getProfile().getEmailID()));
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, Application.URL_PART_MATCHES, 0, nameValuePairs, this);
        Application.StartAsyncTaskInParallel(getDataUsingWService);

        lvFilterData = (ListView) findViewById(R.id.main_list_data);
        lvFilterData.setOnItemClickListener(this);

        adapterShortedProfile = new AdapterShortedProfile(getApplicationContext(), R.layout.row_list_shortlisted, matchProfileList);
        lvFilterData.setAdapter(adapterShortedProfile);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.main_list_data) {
            Log.d(TAG, "::::Start Animation");
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.aplha);
            view.startAnimation(animation);

            Intent intent = new Intent(MainActivity.this, ProfileDetailActivity.class);
            intent.putExtra("matri_id", matchProfileList.get(position).getId());
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
        if (serviceCounter == 0) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("match");
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
                    matchProfileList.add(profileShorted);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (serviceCounter == 1) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("view_profile");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String photoPath = jsonObject1.getString("photo1");
                    String username = jsonObject1.getString("username");

                    tvNVName.setText(strLoginID.toUpperCase(Locale.US));
                    tvNVEmail.setText(username);
                    try {
                        URL url = new URL(Application.URL_PHOTO_BIG + photoPath);
                        uri = url.toURI();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    Picasso.with(getApplicationContext())
                            .load(uri.toString())
                            .error(R.mipmap.no_photo)
                            .fit()
                            .placeholder(R.mipmap.no_photo)
                            .into(circularImageView);
                    NvItems items = (arrNVItems).get(arrNVItems.size() - 1);
                    items.setTitle(getString(R.string.prompt_logout));
                    nvAdapter.notifyDataSetChanged();
                    Profile profile = Profile.getProfile();
                    profile.setEmailID(strLoginID);
                    profile.setName(username);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        adapterShortedProfile.notifyDataSetChanged();
    }
}
