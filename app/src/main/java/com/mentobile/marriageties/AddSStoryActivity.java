package com.mentobile.marriageties;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddSStoryActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView  imgPicture;
    private EditText edBrideID;
    private EditText edBrideName;
    private EditText edGroomID;
    private EditText edGroomName;
    private EditText edWeddingDate;
    private EditText edYourExp;

    private Button btnSubmit;
    private Button btnClear;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sstory);

//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Success Story");

        imgPicture = (ImageView)findViewById(R.id.add_story_imgv_contact_pic);
        imgPicture.setOnClickListener(this);
        edBrideID = (EditText) findViewById(R.id.add_story_edt_bride_id);
        edBrideName = (EditText) findViewById(R.id.add_story_edt_bride_name);
        edGroomID = (EditText) findViewById(R.id.add_story_edt_groom_id);
        edGroomName = (EditText) findViewById(R.id.add_story_edt_bride_name);
        edWeddingDate = (EditText) findViewById(R.id.add_story_edt_wedding_date);
        edWeddingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateTimeField(AddSStoryActivity.this, edWeddingDate);
            }
        });

        edYourExp = (EditText) findViewById(R.id.add_story_edt_your_exp);

        btnSubmit = (Button) findViewById(R.id.add_story_btn_submit);
        btnSubmit.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.add_story_btn_clear);
        btnClear.setOnClickListener(this);
    }

    public void setDateTimeField(Activity activity, final EditText editText) {

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Application.DATE_PATTERN3, Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String strDataTime = simpleDateFormat.format(newDate.getTime());
                editText.setText("" + strDataTime);
            }
        }, year, month, date);

        datePickerDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_sstory, menu);
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
            case R.id.action_close:
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_story_imgv_contact_pic:

                break;
            case R.id.add_story_btn_submit:
                break;
            case R.id.add_story_btn_clear:
                edBrideID.getText().clear();
                edBrideName.getText().clear();
                edGroomID.getText().clear();
                edGroomName.getText().clear();
                edWeddingDate.getText().clear();
                edYourExp.getText().clear();
                break;
        }
    }
}
