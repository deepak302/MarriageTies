package com.mentobile.marriageties;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "MainActivity";

    private ListView lvSearchResult;
    private ArrayList<ProfileShorted> arrayListSearch = new ArrayList<>();
    private AdapterShortedProfile adapterSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        lvSearchResult = (ListView) findViewById(R.id.search_result_lv_data);
        lvSearchResult.setOnItemClickListener(this);
        for (int i = 0; i < 15; i++) {
            ProfileShorted profileShorted = new ProfileShorted(i, "Name " + i, "" + 20 + i, "5 Ft 6 in", "Hindu", "Brahmin", "Gaur", "MCA", "Gurgaon", "Haryana", "India");
            arrayListSearch.add(profileShorted);
        }
        adapterSearchResult = new AdapterShortedProfile(getApplicationContext(), R.layout.row_list_shortlisted, arrayListSearch);
        lvSearchResult.setAdapter(adapterSearchResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.aplha);
        view.startAnimation(animation);

        Intent intent = new Intent(SearchResultActivity.this, ProfileDetailActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
