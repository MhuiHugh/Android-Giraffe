package com.hu.giraffe.widget.dialog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * 对话框工厂
 */
public class DialogFactory {

    //------每种Dialog tag标记
    private static final String DIALOG_LOADING_TAG = "loading";//加载
    private static final String DIALOG_PAY_TAG = "pay";//支付
    private static final String DIALOG_WARN_TAG = "warn";//警告
    private static final String DIALOG_UPDATE_TAG = "update";//升级
    private static final String DIALOG_COMMON_TAG = "common";//通用对话框

    private FragmentManager mFragmentManager;

    public DialogListenerHolder mListenerHolder = new DialogListenerHolder();

    public DialogFactory(FragmentManager fragmentManager, Bundle savedInstanceState) {
        this.mFragmentManager = fragmentManager;
        mListenerHolder.getDialogListenerKey(savedInstanceState);
    }

    /**
     * 这个方法很重要，是恢复dialog listener的一个关键点，在初始化DialogFactory或把DialogFactory赋值后，调用该方法，把调用该方法所在
     * 的类的实例作为参数。 该方法会把param中的属性依次遍历，尝试找属性是BaseDialogFragment.BaseDialogListener的实例，
     * 并且该属性就是保存在bundle中的dialog listener key对应的dialog listener
     *
     * @param o
     */
    public void restoreDialogListener(Object o) {
        mListenerHolder.restoreDialogListener(o);
    }

    //---------------------------------------------------

    /**
     * 显示加载提示,2种提示样式
     *
     * @msg msg为""时显示自定义loading,不为""时显示系统ProgressDialog并提示msg
     */
    public void showLoadingDialog(String msg) {
        if (mFragmentManager != null) {
            /**
             * 为了不重复显示dialog，在显示对话框之前移除正在显示的对话框。
             */
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            Fragment fragment = mFragmentManager.findFragmentByTag(DIALOG_LOADING_TAG);
            if (null != fragment) {
                ft.remove(fragment).commit();
            }
            //DialogLoadingFragment loadingDialogFragment = DialogLoadingFragment.newInstance(msg, false);
            //loadingDialogFragment.show(mFragmentManager, DIALOG_LOADING_TAG);
            mFragmentManager.executePendingTransactions();
        }
    }

    /**
     * 隐藏加载中Dialog
     */
    public void hideLoadingDialog() {
        Fragment fragment = mFragmentManager.findFragmentByTag(DIALOG_LOADING_TAG);
        if (null != fragment) {
            //((DialogLoadingFragment) fragment).dismiss();
            mFragmentManager.beginTransaction().remove(fragment).commit();
        }
    }

}
