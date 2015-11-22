package com.mentobile.marriageties;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.mentobile.utility.DBHandler;

public class SplashActivity extends ActionBarActivity implements MediaPlayer.OnCompletionListener {

    private VideoView videoView;
    public static DBHandler dbHandler;
    private String strSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        videoView = (VideoView) findViewById(R.id.splash_vv_videoview);
        MediaController mc = new MediaController(this);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        mc.setVisibility(View.GONE);
        videoView.setMediaController(mc);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.mipmap.animation);
        videoView.setVideoURI(video);
        videoView.start();
        videoView.setOnCompletionListener(this);
        dbHandler = new DBHandler(getApplication(), 1);

        strSignIn = Application.getDataFromSharedPreference(this, Application.SP_LOGIN_LOGOUT, "matri_id");
        Log.d("Splash Activity ", "::::" + strSignIn);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

        if (!Application.isNetworkAvailable(getApplicationContext())) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(android.R.id.content, new NetworkErrorFragment());
            transaction.commit();
        } else if (strSignIn != null) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        } else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        }
    }
}
