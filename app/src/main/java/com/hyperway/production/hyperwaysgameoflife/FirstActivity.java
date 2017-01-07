package com.hyperway.production.hyperwaysgameoflife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by vijay on 21-07-2016.
 */
public class FirstActivity extends Activity {

    Button newGame,aboutGame,exitGame;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        getActionBar().setIcon(R.mipmap.title);

        newGame=(Button)findViewById(R.id.newgame);
        aboutGame=(Button)findViewById(R.id.aboutgame);
        exitGame=(Button)findViewById(R.id.exitgame);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //to call grid activity and load main.xml
                Intent intent=new Intent(FirstActivity.this,GridActivity.class);
                startActivity(intent);

            }
        });

        aboutGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //to call about activity
                Intent intent=new Intent(FirstActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });

        exitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }
}
