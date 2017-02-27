package com.hu.giraffe.utils;

/**
 * 静态常量，程序配置，状态标记变量
 * Created by Menghui on 2017/2/6.
 */
public class Constant {

    //----------boolean状态标记
    public static boolean isAppRuning = false;//程序是否运行状态
    public static boolean isShowSoftKeyBoard = false;//输入法是否显示

    //----------SharedPreferences配置
    public static final String KEY_SHARED_PREFERENCES_NAME = "sp_data";//shardpreferences的xml文件名称

    //----------静态字符串常量
    public static final String ACT_EXIT_APP = "actExitApp";//退出程序

}
