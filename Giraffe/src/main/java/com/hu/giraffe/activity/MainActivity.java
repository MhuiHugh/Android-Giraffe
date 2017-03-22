package com.hu.giraffe.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.hu.giraffe.R;
import com.hu.giraffe.utils.LogUtil;
import com.hu.widget.HalfImageButton;
import com.hu.widget.HorizontalScrollIndicatorView;
import com.hu.widget.HorizontalScrollIndicatorAdapter;
import com.hu.widget.StatusBarCompat;
import com.hu.widget.wheelview.ChangeAddressPopwindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.hu.giraffe.R.id.scrollView;

public class MainActivity extends BaseActivity implements View.OnKeyListener, HorizontalScrollIndicatorView.OnItemClickListener {

    private final String TAG = this.getClass().getSimpleName();

    CoordinatorLayout rootCl;//root
    HalfImageButton halfImgBtn;//图片居中，宽高各占View一半
    HorizontalScrollIndicatorView horizontalScrollIndicatorView;//颜色选择器
    Button loginBtn;//login
    ToggleButton lightTbtn;//灯

    //--------------------
    long exitTime = 0;//2秒内点击2次返回退出程序

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
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
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_BACK) {//返回按键处理
            exitApp();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {//音量加

        }
        return false;
    }


    //---------------------------------------------------------------------

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    public String activityName() {
        return getClass().getSimpleName();
    }

    //---------------------------------------------------------------------

    /**
     * init
     */
    private void init() {
        Log.v(TAG, "init()");
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimaryDark));
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        rootCl = (CoordinatorLayout) findViewById(R.id.main_root_cl);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.main_floating_action_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //图片占一半按钮
        halfImgBtn = (HalfImageButton) findViewById(R.id.main_half_img_btn_welcome);
        halfImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "HalfImageButton", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, GiraffeActivity.class);
                startActivity(intent);
            }
        });

        //颜色选择器
        List<Integer> colors = new ArrayList<>();
        Random random = new Random(2);
        for (int i = 0; i < 50; i++) {
            colors.add(Color.argb(255, 50 * i, random.nextInt(255), random.nextInt(255)));
        }
        horizontalScrollIndicatorView = (HorizontalScrollIndicatorView) findViewById(R.id.main_color_indicator);
        horizontalScrollIndicatorView.setAdapter(new HorizontalScrollIndicatorAdapter(this, colors));
        horizontalScrollIndicatorView.setOnItemClickListener(this);

        //登录
        loginBtn = (Button) findViewById(R.id.main_login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CircleMenuActivity.class);
                startActivity(intent);
            }
        });

        //灯
        lightTbtn = (ToggleButton) findViewById(R.id.main_light_tbtn);
        lightTbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(MainActivity.this);
                if (isChecked) {
                    mChangeAddressPopwindow.setAddress("上海", "上海", "静安区");
                    mChangeAddressPopwindow.showAtLocation(rootCl, Gravity.BOTTOM, 0, 0);
                    mChangeAddressPopwindow
                            .setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {
                                @Override
                                public void onClick(String provinceS, String cityS, String areaS) {
                                    Log.v(TAG, "city:" + provinceS + "--" + cityS + "--" + areaS);
                                }
                            });
                } else {
                    mChangeAddressPopwindow.dismiss();
                }
            }
        });
    }

    /**
     * 退出程序
     */
    public void exitApp() {
        LogUtil.v("exitApp()");
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), getString(R.string.common_exit_app), Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            this.finish();
        }
    }

}
