package com.hu.giraffe.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.hu.giraffe.utils.Constant;
import com.hu.giraffe.utils.LogUtil;
import com.hu.giraffe.utils.SoftKeyboardUtil;
import com.hu.giraffe.utils.SystemUtil;

/**
 * Activity基类
 * Created by Mhui on 2016/8/4.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnKeyListener {

    //广播
    private final BaseBroadcastReceiver baseBroadcastReceiver = new BaseBroadcastReceiver();

    //------------生命周期方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("onCreate()-" + this.getClass().getName());
        init(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d("onStart()-" + this.getClass().getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d("onResume()-" + this.getClass().getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d("onPause()-" + this.getClass().getName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d("onStop()-" + this.getClass().getName());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(baseBroadcastReceiver);
        LogUtil.d("onDestroy()-" + this.getClass().getName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.d("onActivityResult()-" + this.getClass().getName());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    //---------------------------事件监听

    /**
     * 重写事件分发，解决点击软键盘外隐藏键盘
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (SoftKeyboardUtil.isShouldHideInput(v, ev)) {
                SoftKeyboardUtil.hideSoftKeyboard(this);
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { // 监控/拦截/屏蔽返回键
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {//进入测试

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (Constant.isShowSoftKeyBoard) {
                SoftKeyboardUtil.hideSoftKeyboard(this);
            } else {
                this.finishActivity();
            }
        }
        return false;
    }

    //-----------------------------自定义方法

    /**
     * 初始化
     */
    private void init(Bundle savedInstanceState) {
        //广播过滤器设置
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACT_EXIT_APP);
        this.registerReceiver(baseBroadcastReceiver, intentFilter);

        //软键盘显隐状态监听
        SoftKeyboardUtil.observeSoftKeyboard(this, new SoftKeyboardUtil.OnSoftKeyboardChangeListener() {
            @Override
            public void onSoftKeyBoardChange(int softKeybardHeight, boolean visible) {
                if (SystemUtil.isTopActivy(BaseActivity.this.getClass().getName())) {
                    if (visible) {
                        Constant.isShowSoftKeyBoard = true;
                    }
                    Message msg = new Message();
                    msg.what = 0;
                    msg.arg1 = softKeybardHeight;
                    msg.arg2 = visible ? 0 : 1;
                    baseHandler.sendMessageDelayed(msg, 200);
                }
            }
        });
    }

    /**
     * finish all Activity
     */
    private void finishAllActivity() {
        LogUtil.d("finishAllActivity()");
        Constant.isAppRuning = false;
        this.finishActivity();
    }

    //--------------------------抽象方法

    /**
     * 清理内存数据，退出当前Activity
     */
    public abstract void finishActivity();

    /**
     * Activity的名称，用于友盟页面统计
     *
     * @return
     */
    public abstract String activityName();

    //------------------------广播监听

    public Handler baseHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0://延时100毫秒更新键盘显隐状态
                    Constant.isShowSoftKeyBoard = msg.arg2 == 0;
                    //LogUtil.d("handler:" + Constant.softKeyBoardHeight + "--" + Constant.softKeyBoardIsShow);
                    break;
                case 1://延迟xxx毫秒退出Activity
                    LogUtil.d("延迟finishActivity()");
                    finishActivity();
                    break;
            }
        }
    };

    /**
     * 广播接受，BaseActivity使用EventBus其它子Activity必须用规避
     */
    private class BaseBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            String action = arg1.getAction();
            if (action.equals(Constant.ACT_EXIT_APP)) {//退出所有Activity(退出app)
                finishAllActivity();
            }
        }
    }

}
