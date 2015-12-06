package com.mentobile.marriageties;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mentobile.profile.EditProfileActivity;
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
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private NvDrawerAdapter nvAdapter;
    Toolbar toolbar;
    ArrayList<NvItems> arrNVItems = new ArrayList<NvItems>();
    private TextView tvNVEmail;
    private TextView tvNVName;
    private CircularImageView circularImageView;

    static ArrayList<ProfileShorted> sortedProfileList = new ArrayList<>();
    ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
    String strLoginID;

    private int nvIcon[] = {
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

    private ViewPager viewPager;

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
        if (strLoginID != null) {
            ArrayList<NameValuePair> valuePairsID = new ArrayList<>();
            Profile.getProfile().setEmailID(strLoginID.toString().trim());
            valuePairsID.add(new BasicNameValuePair("matri_id", strLoginID));
            GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, Application.URL_VIEW_PROFILE, 1, valuePairsID, this);
            Application.StartAsyncTaskInParallel(getDataUsingWService);
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
        circularImageView = (CircularImageView) viewGroup.findViewById(R.id.nv_header_img_pphoto);
        mDrawerList.addHeaderView(viewGroup, null, true);

        final ActionBar actionBar = getSupportActionBar();
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

        String tabs[] = getResources().getStringArray(R.array.tab_main_page);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabTextColors(Color.WHITE, Color.DKGRAY);
        for (String tab_name : tabs) {
            tabLayout.addTab(tabLayout.newTab().setText(tab_name));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        AdapterVPagerMain pagerAdapter = new AdapterVPagerMain(getSupportFragmentManager(), tabs.length);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getApplicationContext(), "Tab Selected " + tab.getPosition(), Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:// header
                Intent intentEditProfile = new Intent(MainActivity.this, EditProfileActivity.class);
                startActivity(intentEditProfile);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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

        if (serviceCounter == 1) {
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
    }
}
