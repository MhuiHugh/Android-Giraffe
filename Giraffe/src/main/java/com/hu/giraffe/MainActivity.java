package com.hu.giraffe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hu.widget.HalfImageButton;
import com.hu.widget.HorizontalScrollIndicatorView;
import com.hu.widget.HorizontalScrollViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener, HorizontalScrollIndicatorView.OnItemClickListener {

    private final String TAG = this.getClass().getSimpleName();

    HalfImageButton halfImgBtn;
    HorizontalScrollIndicatorView horizontalScrollIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "onCreate()");
        init();
    }

    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart()");
    }

    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume()");
    }

    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause()");
    }

    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy()");
    }

    public void onLowMemory() {
        super.onLowMemory();
        Log.v(TAG, "onLowMemory()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view, int pos) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {

        } else if (event.getAction() == KeyEvent.ACTION_UP) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    finish();
                    break;
            }
        }
        return false;
    }

    //---------------------------------------------------------------------


    //---------------------------------------------------------------------

    /**
     * init
     */
    private void init() {
        Log.v(TAG, "init()");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        halfImgBtn = (HalfImageButton) findViewById(R.id.half_img_btn_welcome);
        halfImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "HalfImageButton", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, GiraffeActivity.class);
                startActivity(intent);
            }
        });

        List<Integer> colors = new ArrayList<>();
        Random random = new Random(2);
        for (int i = 0; i < 50; i++) {
            colors.add(Color.argb(255, 50 * i, random.nextInt(255), random.nextInt(255)));
        }
        horizontalScrollIndicatorView = (HorizontalScrollIndicatorView) this.findViewById(R.id.color_indicator);
        horizontalScrollIndicatorView.setAdapter(new HorizontalScrollViewAdapter(this, colors));
        horizontalScrollIndicatorView.setOnItemClickListener(this);

    }

}
