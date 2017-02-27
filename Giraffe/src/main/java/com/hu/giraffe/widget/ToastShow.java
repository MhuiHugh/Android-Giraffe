package com.hu.giraffe.widget;

import android.view.Gravity;
import android.widget.Toast;

import com.hu.giraffe.MyApp;

/**
 * Toast显示，Toast在屏幕中位置，根据提示设置显示时长
 * Created by Menghui on 2016/8/15.
 */
public class ToastShow {
    private static Toast toast = null;

    /**
     * 在屏幕中间显示Toast
     *
     * @param message
     */
    public static void showInCenter(String message) {
        show(message, Gravity.CENTER, -1);
    }

    /**
     * 在屏幕底部默认位置显示Toast
     *
     * @param message
     */
    public static void showInBottom(String message) {
        show(message, Gravity.BOTTOM, -1);
    }

    /**
     * Toast 3个属性设置
     *
     * @param message
     * @param gravity
     * @param time    小于-1时根据文本长度自动设置显示时长
     */
    public static void show(String message, int gravity, int time) {
        if (message.isEmpty()) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(MyApp.getInstance(), "", time);
        }
        if (gravity == Gravity.BOTTOM) {
            toast.setGravity(gravity, 0, 60);
        } else {
            toast.setGravity(gravity, 0, 0);
        }
        if (time > -1) {
            toast.setDuration(time);
        } else {
            if (message.length() > 16) {
                toast.setDuration(Toast.LENGTH_LONG);
            } else {
                toast.setDuration(Toast.LENGTH_SHORT);
            }
        }
        toast.setText(message);
        toast.show();
    }
}
