package com.hu.giraffe;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.hu.giraffe.utils.Constant;
import com.hu.giraffe.utils.ForegroundCallbacks;
import com.hu.giraffe.utils.LogUtil;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Menghui on 2017/2/6.
 */
public class MyApp extends Application {

    //Application
    private static MyApp app;
    //sharedpreferences
    private SharedPreferences mySharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.v("onCreate()");
        init();
    }

    public void onLowMemory() {
        super.onLowMemory();
        LogUtil.e("onLowMemory()");
    }

    //--------------------------------------

    /**
     * 饿汉式线程安全单例模式
     *
     * @return
     */
    public static MyApp getInstance() {
        return app;
    }

    //----------------------------------------------
    private void init() {
        app = this;

        //内存泄漏分析
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
            LogUtil.v("内存泄漏分析...");
        }
        //程序前后台监听
        ForegroundCallbacks.get(this).addListener(new ForegroundCallbacks.Listener() {
            @Override
            public void onBecameForeground() {
                LogUtil.v("程序进入前台...");
                Constant.isAppRuning = true;
            }

            @Override
            public void onBecameBackground() {
                LogUtil.v("程序进入后台...");
            }
        });
    }

    /**
     * 获取xml数据存储对象
     *
     * @return
     */
    public SharedPreferences getMySharedPreferences() {
        if (mySharedPreferences == null) {
            mySharedPreferences = getSharedPreferences(Constant.KEY_SHARED_PREFERENCES_NAME,
                    Activity.MODE_PRIVATE);
        }
        return mySharedPreferences;
    }

}

