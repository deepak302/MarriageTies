package com.mentobile.marriageties;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SuccessStoryActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btnAddSuccessStory;

    private ListView lvSuccessStory;
    private ArrayList<SuccessStory> storyArrayList = new ArrayList<>();
    private AdapterSuccessStory adapterSuccessStory ;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_story);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAddSuccessStory = (Button) findViewById(R.id.sstory_btn_addstory);
        btnAddSuccessStory.setOnClickListener(this);

        lvSuccessStory = (ListView) findViewById(R.id.sstory_list_data);

        for (int i = 0; i < 10; i++) {
            SuccessStory successStory = new SuccessStory(i, "Image Path", "Deepak" + i, "Shalu" + i, "29/10/2015", "Hello World");
            storyArrayList.add(successStory);
        }

        adapterSuccessStory = new AdapterSuccessStory(getApplicationContext(),R.layout.row_list_success_story,storyArrayList);
        lvSuccessStory.setAdapter(adapterSuccessStory);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_success_story, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sstory_btn_addstory:
                Intent intentSStory = new Intent(SuccessStoryActivity.this, AddSStoryActivity.class);
                startActivity(intentSStory);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                break;
        }
    }
}
