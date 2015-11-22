package com.mentobile.marriageties;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class PrivacyPolicyActivity extends AppCompatActivity {

    private TextView tvPrivacyPolicy;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvPrivacyPolicy = (TextView)findViewById(R.id.privacy_tv_data);
        tvPrivacyPolicy.setText(Html.fromHtml("General Information<br><br>Marriage Ties commits privacy of information to each and " +
                "every member of the website. The Marriage Ties collects, uses, stores and discloses information, " +
                "in accordance with the Canadian Privacy Act R.S.C., 1985, C.P-21 Act. The privacy policy of Marriage Ties states " +
                "the privacy practices for the website, which are available to visitors. This privacy statement (along with the terms and " +
                "conditions and other documents) is based on the data collected from members, or from the data provided to us by the members. " +
                "We process all this data. When you agree to the usage of the products and services offered by us, you give us the permission" +
                " to use all the personal information provided by you, in confirmation with the privacy policy of the company and the terms and" +
                " conditions set out by us.<br><br>What information we collect<br><br>Marriage Ties collects two types of data:<br><br>   " +
                "1.Personally identifiable information (PII) – this consists of data that represents you as and individual<br>   " +
                "2.Non-personally identifiable information (non-PII) – this consists of all the other information that does not " +
                "specifically represent you. It is also called ‘sensitive personal data’.<br><br>The following sections describe how PII and " +
                "non-PII of a member is collected by our team and how the information is used to help you find the best partner.<br><br>How" +
                " we collect and use information<br><br><b>Log files</b><br><br>Whenever you visit our website, we will collect all kinds of " +
                "information about the way you use our website. Some of the aspects analyzed by us include; IP address, browser type, internet " +
                "service provider, pages referred, exit pages, platform type, date and time and the number of clicks. These factors are used to " +
                "evaluate the ongoing trend, administer the website, keep a track of the movement of visitors on an average and to collect broad" +
                " demographic information. It also helps us to understand which part of our website is the most popular.<br><br><b>Registration/" +
                " subscription questionnaires and personality profiles</b><br><br>Once you register with the services of Marriage Ties, our team" +
                " collects a wide variety of knowledge about you, in order to understand you completely, as an individual. It is also used to " +
                "find the most compatible match for a long lasting relationship. A personality profile is built on the basis of your answers in " +
                "the questionnaire. This profile assists us in our ‘Match-Me’ service to give you the most suitable suggestions.<br><br>All the " +
                "personal information provided to us through the questionnaires, are kept strictly confidential. However, a member can share" +
                " the personal information with other members on the website by making changes in the privacy settings.<br><br><b>Aggregate " +
                "information</b><br><br>Along with the personality profile, we also collect other types of information from members. These " +
                "include, name, age, location, occupation, height, weight, religion and ethnicity. This information is necessary to give a" +
                " more detailed information to prospective matches about you. When you provide us with these aspects of information you " +
                "agree to the fact that Marriage Ties has the right to disclose this information to other members of the website upon " +
                "requirement.<br><br>Candidates are also allowed to provide us with their photos and videos for online sharing. However, " +
                "when you do so, you allow us to share them with other members of the website and users of Marriage Ties’s services. If " +
                "you are using our community services, called ‘Advice’, you would have complete control over what kind of knowledge is " +
                "disclosed to the people visiting your profile. The PII information is not disclosed in any case. It is only shared when " +
                "the owner of the profile allows us.<br><br><b>Purchase Information</b><br><br>Along with other services, Marriage Ties " +
                "offers a subscriber based service to people seeking our services and for individuals who purchase our products in relation " +
                "to successful relationships and personal growth. In order to confirm purchases or subscription of services with us, important " +
                "information is collected by us. Some of these include, name, billing address, phone number, email address, shipping address " +
                "and credit card information.<br><br><b>Emails and telephone calls</b><br><br>Every new subscriber has to provide us with an" +
                " email id. Notifications are sent on your email, when we identify a potential match for you under our ‘Match-Me’ service, in " +
                "accordance with your profile and information provided to us.<br><br>For subscribers of the community service, such as ‘Advice’," +
                " newsletters are sent on the email-address upon consent. Email address is also used to notify the subscriber about the latest" +
                " developments, events, new products, promotion and other important information. If a subscriber wishes to stop getting emails," +
                " he/she can see the section of ‘choice/opt-out’.<br><br>Our team can also contact you through on the telephone number provided to us solely regarding the services provided by Marriage Ties. In case you do not want us to contact you through telephone calls for marketing or other suggestions, you can email us or write to us.<br><br><b>Demographic and profile data</b><br><br>We use this data to learn about the experience of visitors to our website. Keeping that in mind, content is altered and only those aspects are displayed in which the audience is interested in and the content that we think is essential.<br><br><b>Online survey data</b><br><br>Our team conducts frequent voluntary member surveys. All subscribers are encouraged to participate in the survey. The feedback of the subscribers helps us in understanding the required number of improvements in our services. This survey is not displayed on the PII and the answers are anonymous.<br><br><b>Information regarding friends</b><br><br>We encourage you to refer a friend of yours to us. You can do so by giving us the name and email-id of your friend. This information will be stored in our database and an email would be sent to that concerned friend along with your name and greetings. That friend will be invited to visit our website. We will not send any additional or promotional emails to your friend unless he/she signs up for it. Your friend will also be able to remove his/her information from our database by a quick and easy process.<br><br><b>Public forums</b><br><br>Sometimes discussion boards are opened to visitors and subscribers. Marriage Ties does not take responsibility for any information shared on this platform. All the information shared on public forums are available for everyone to read. Hence, we advice everyone to be extremely cautious.<br><br><b>Use for research</b><br><br>When you start using the services provided by Marriage Ties, you agree to let us your anonym zed information for research and improvisations in our services. The research may also be used in a academic journals. All responses and PII are never published.<br><br>Disclosure of your information to third parties<br><br><b>Disclosure by law</b><br><br>You permit Marriage Ties to disclose personal information, if the law requires it to do so or if we feel the disclosure is important for:<br><br>Requests from law enforcement or by any other legal process<br><br>Third parties<br><br>To protect the rights of Marriage Ties or that of a third party<br><br>To protect the life, health and safety of a person, in case it is threatened<br><br><b>Disclosure to protect abuse victims</b><br><br>If Marriage Ties suspects that any kind of information provided to the website involves a party that is the victim of abuse, then we will have the right to report the information to legal authorities. The abuse could be in any form, such as, domestic violence, elder abuse, spousal abuse or child abuse.<br><br><b>Disclosure to trusted third parties by us</b><br><br>We might share the PII provided by you, with third part service providers that are associated with us. These third parties will help in providing better services to you. The PII would be kept confidential by these third parties. We seek consent from you through email or telephone before sharing the information with third parties, who are dedicated towards providing goods and services that suit your interest. This facility can be stopped by choosing the ‘choice/opt-out’ option.<br><br><b>Disclosure to trusted third parties at your request</b><br><br>After subscription to our website, we offer a number of promotions, advertisements and sweepstakes to our members on behalf of third parties. A subscriber is completely responsible for accepting such offers and submitting information directly to the third parties. We will transfer your information only after obtaining your consent. However, you will have the discretion to opt out of an offer whenever you want.<br><br>Age Restrictions<br><br>The terms and conditions of our website clearly state that our services are not targeted towards serving individuals below the age of 18. If we believe that the user is below 18 years of age, then their profile will be deleted from our database.<br><br>Security<br><br>Marriage Ties use high-class security measures to ensure the safety of information in our database. Extensive technological advancement are carried out to make sure there is no loss, misuse and unnecessary alteration on data. Reasonable amount of care is taken to ensure that the information submitted to us over the internet is protected. However, 100% guarantee or warranty cannot be given for submission of information through the internet.<br><br>Choice/ Opt-out<br><br>We store your PII for only as long as it is necessary for the purpose it was meant for. If you are a subscriber of the services of Marriage Ties and wish to opt-out of the services provided by us, you can remove your information from our database in the following manner:<br><br>You can go to our website: http://www.nrimb.com/ and select the help link at the bottom of the page. You can search for answers in our FAQ section.<br><br>You can also email us at info@nrimb.com – our staff would be glad to assist you.<br><br>You can send us a mail at our postal address: Marriage Ties CORP.<br><br>Access to information<br><br>In accordance with the law and various Acts, you have the right to access the information that we hold about you. However, a nominal fee would be charged if you want to know about the details we have in our database about you.<br><br>Correct/update<br><br>The ‘My Settings’ option on the home page can be chosen for making changes in the password, credit card or any other kind of information. The link of ‘Account Settings’ will give you a list of all the information that you can edit. Furthermore, assistance from our staff can be taken in case of a problem.<br><br>Acceptance of Privacy Statement<br><br>In case of any dispute regarding the privacy of information, your use of our services, is subject to the terms and conditions set out by us. If we make any changes in our privacy policy, this page will be updated and notifications will be sent out to our subscribers through email or any other means of communication. If you continue to use the website after the notifications have been sent out or make any changes, it will be assumed that you accept the update privacy policy of the company.<br><br>"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_privacy_policy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
