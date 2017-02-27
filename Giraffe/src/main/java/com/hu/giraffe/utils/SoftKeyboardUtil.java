package com.hu.giraffe.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 键盘显示隐藏，监听键盘状态
 */
public class SoftKeyboardUtil {
    public static final String TAG = "SoftKeyboardUtil";

    public static void observeSoftKeyboard(Activity activity, final OnSoftKeyboardChangeListener listener) {
        final View decorView = activity.getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                decorView.getWindowVisibleDisplayFrame(rect);
                int displayHeight = rect.bottom - rect.top;
                int height = decorView.getHeight();
                boolean hide = (double) displayHeight / height > 0.8;
                if (Log.isLoggable(TAG, Log.DEBUG)) {
                    Log.d(TAG, "DecorView display hight = " + displayHeight);
                    Log.d(TAG, "DecorView hight = " + height);
                    Log.d(TAG, "softkeyboard visible = " + !hide);
                }
                listener.onSoftKeyBoardChange(height - displayHeight, !hide);
            }
        });
    }

    /**
     * Note: if you change layout in this method, maybe this method will repeat because the ViewTreeObserver.OnGlobalLayoutListener will repeat So maybe you need do some handle(ex: add some flag to avoid repeat) in this callback
     * <p>
     * Example:
     * <p>
     * private int previousHeight = -1; private void setupKeyboardLayoutWhenEdit() { SoftKeyboardUtil.observeSoftKeyBoard(this, new OnSoftKeyboardChangeListener() {
     *
     * @Override public void onSoftKeyBoardChange(int softkeybardHeight, boolean visible) { if (previousHeight == softkeybardHeight) { return; } previousHeight = softkeybardHeight; //code for change layout } }); }
     */
    public interface OnSoftKeyboardChangeListener {
        void onSoftKeyBoardChange(int softKeybardHeight, boolean visible);
    }

    /**
     * 隐藏键盘
     *
     * @param activity
     */
    public static void hideSoftKeyboard(final Activity activity) {
        try {
            IBinder token = activity.getCurrentFocus().getWindowToken();
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (token != null) {
                imm.hideSoftInputFromWindow(token, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示软键盘
     *
     * @param view
     */
    public static void showInput(Activity activity, View view) {
        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
    }

    /**
     * 判断当前点击是否为EditView，处理点击键盘外隐藏输入法
     *
     * @param v
     * @param event
     * @return
     */
    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

}
