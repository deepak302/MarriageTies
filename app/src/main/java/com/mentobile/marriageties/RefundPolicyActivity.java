package com.mentobile.marriageties;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class RefundPolicyActivity extends AppCompatActivity {

    private TextView tvRefundPolicy;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_policy);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvRefundPolicy = (TextView) findViewById(R.id.refund_tv_policy);
        tvRefundPolicy.setText(Html.fromHtml("<b>Payback Policy:-</b><br><br>By registering your profile through any of our domains " +
                "or by the remittance of fees you become a member of Marriage Ties.To be eligible for the refund, members should" +
                " fulfil the following:<br><br>&nbsp&nbsp * Will refund 50% of the paid amount if applied for within 7 days of" +
                " taking Membership.<br>&nbsp&nbsp * We charge administation charges of $99 on all memberships which are non " +
                "refundable at any circumstances(except only Diamond).<br>&nbsp&nbsp * Upload at least one photo.<br>&nbsp&nbsp *" +
                " Contact a minimum of 10 members.<br>&nbsp&nbsp * Once the members availed response from TheMarriage Ties, there" +
                " will be no payback for received  remittance.<br><br>Since Marriage Ties allows members to communicate with other" +
                " members This policy while providing value for money to members also ensures satisfaction criteria for the members." +
                "<br>To be eligible for the refund, members should fillup the following application form provided in link below:"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_refund_policy, menu);
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
}
