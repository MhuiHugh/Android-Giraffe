package com.hu.giraffe.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hu.giraffe.activity.BaseActivity;
import com.hu.giraffe.utils.LogUtil;


/**
 * Fragment基类
 * Created by Menghui on 2016/8/5.
 */
public abstract class BaseFragment extends Fragment {

    protected BaseActivity mBaseActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.d("onCreateView()-" + this.getClass().getName());
        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LogUtil.d("onViewCreated()-" + this.getClass().getName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("onCreate()-" + this.getClass().getName());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.d("onActivityCreated()-" + this.getClass().getName());
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.d("onStart()-" + this.getClass().getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d("onResume()-" + this.getClass().getName());
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d("onPause()-" + this.getClass().getName());
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.d("onStop()-" + this.getClass().getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("onDestroy()-" + this.getClass().getName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.d("onActivityResult()-" + this.getClass().getName());
    }

    /**
     * Android-6.0及以上会回调2个方法
     */
    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    /**
     * Android-6.0以下会回调该方法
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
        if (activity instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) activity;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.d("onDestroyView()-" + this.getClass().getName());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtil.d("onSaveInstanceState()-" + this.getClass().getName());
    }

    //------自定义方法-----------------------------

    /**
     * onAttach()系统方法处理兼容问题
     *
     * @param context
     */
    protected void onAttachToContext(Context context) {
        //LogUtil.d("onAttachToContext()");
    }

    //------抽象方法----------------------------------

    /**
     * Fragment的名称，用于友盟页面统计
     *
     * @return
     */
    public abstract String fragmentName();

}
