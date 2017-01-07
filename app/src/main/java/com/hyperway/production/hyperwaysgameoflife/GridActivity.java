package com.hyperway.production.hyperwaysgameoflife;

/**
 * Created by vijay on 19-07-2016.
 */
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.content.Intent;
import android.view.View.*;

public class GridActivity extends Activity implements OnClickListener, OnSeekBarChangeListener {

    private Grid _gridview;
    private Button startButton, stopButton, exitGameButton;
    private SeekBar animationSpeed;


    private Life _liLife;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid);
        getActionBar().setIcon(R.mipmap.title);
        _gridview = (Grid) findViewById(R.id.grid_view);

        animationSpeed = (SeekBar) findViewById(R.id.animation_speed);
        animationSpeed.setOnSeekBarChangeListener(this);

        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(this);

        stopButton =(Button)findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);

        exitGameButton =(Button)findViewById(R.id.exit_game_button);
        exitGameButton.setOnClickListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.underpopulation_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.underpopulation_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        _gridview.getViewTreeObserver().addOnGlobalLayoutListener(
                new OnGlobalLayoutListener(){
                    @SuppressWarnings("deprecation")
                    @Override
                    public void onGlobalLayout() {
                        // gets called after layout has been done but before display
                        // so we can get the height then hide the view

                        _gridview.buildGrid(_gridview.getWidth(), _gridview.getHeight(), false);

                        _gridview.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }

                });

		/* Since we need the size of the view in pixels in order to create a grid
		 * that properly fills the screen, we intercept after the view has been
		 * created but not displayed
		 */
        _gridview.getViewTreeObserver().addOnGlobalLayoutListener(
                new OnGlobalLayoutListener(){
                    @SuppressWarnings("deprecation")
                    @Override
                    public void onGlobalLayout() {
                        // gets called after layout has been done but before display
                        // so we can get the height then hide the view

                        _gridview.buildGrid(_gridview.getWidth(), _gridview.getHeight(), false);

                        _gridview.getViewTreeObserver().removeGlobalOnLayoutListener( this );
                    }

                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_button:
                if (_gridview.getState() == Grid.PAUSE) {
                    _gridview.setMode(Grid.RUNNING);
                    startButton.setText("Pause Game");
                }

                else
                    {
                        _gridview.setMode(Grid.PAUSE);
                        startButton.setText("Play Game");

                }

                break;

            case R.id.stop_button:
                _gridview.setMode(Grid.PAUSE);
                startButton.setText("Start Game");
                /*int SPLASH_TIME_OUT=800;
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        //call to first activity
                        Intent i = new Intent(GridActivity.this, GridActivity.class);
                        startActivity(i);

                        finish();
                    }
                }, SPLASH_TIME_OUT);

               */
                break;

            case R.id.exit_game_button:

                Intent i=new Intent(this, FirstActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
        }
    }



    public void rename(){
        startButton.setText("Start New Game");
    }
    @Override
    public void onProgressChanged(SeekBar bar, int progress, boolean isUser) {
        _gridview.setDelay(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //so the compiler doesnt yell at us
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //so the compiler doesnt yell at us
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, PreferencesActivity.class));
                return true;
        }
        return false;
    }


    @Override
    protected void onPause() {

        super.onPause();
        _gridview.setMode(Grid.PAUSE);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
