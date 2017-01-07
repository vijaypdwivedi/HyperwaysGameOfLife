package com.hyperway.production.hyperwaysgameoflife;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import android.content.Intent;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;



public class MainActivity extends Activity implements OnClickListener
{
    private Grid grid;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getActionBar().setIcon(R.mipmap.title);


        grid = (Grid) findViewById(R.id.grid_main);

        grid.getViewTreeObserver().addOnGlobalLayoutListener(
                new OnGlobalLayoutListener(){
                    @SuppressWarnings("deprecation")
                    @Override
                    public void onGlobalLayout() {

                        grid.buildGrid(grid.getWidth(), grid.getHeight(), true);

                        grid.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }

                });
        Button aboutButton = (Button) findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);

        Button newButton = (Button) findViewById(R.id.new_button);
        newButton.setOnClickListener(this);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_button:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
            case R.id.new_button:
                Intent gridIntent = new Intent(this, GridActivity.class);
                startActivity(gridIntent);
        }
    }
}
