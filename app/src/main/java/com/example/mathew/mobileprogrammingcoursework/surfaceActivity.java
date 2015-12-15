package com.example.mathew.mobileprogrammingcoursework;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by Mathew on 07/12/2015.
 */
//Call to Surface Activity
public class surfaceActivity extends Activity {
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bio_draw_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        setContentView(new surfaceView(this));
    }
}
