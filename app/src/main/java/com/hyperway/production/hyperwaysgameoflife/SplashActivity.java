package com.hyperway.production.hyperwaysgameoflife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


/**
 * Created by vijay on 19-07-2016.
 */
public class SplashActivity extends Activity {


    private static int SPLASH_TIME_OUT = 4000;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        getActionBar().setIcon(R.mipmap.title);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                //call to first activity
                Intent i = new Intent(SplashActivity.this, FirstActivity.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
