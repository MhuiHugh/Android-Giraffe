package com.hu.giraffe.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hu.giraffe.R;
import com.hu.giraffe.widget.ToastShow;
import com.hu.widget.CircleButton;
import com.hu.widget.CircleRelativeLayout;
import com.hu.widget.CircleSixLayout;

/**
 * 单个Activity Menu组功能测验
 */
public class CircleMenuActivity extends BaseActivity implements View.OnClickListener {

    CircleRelativeLayout circleRl;//根布局
    CircleSixLayout circleSl;//位置排列,控件交互处理

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_menu);
        init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.circle_zero_cbtn:
                ToastShow.showInCenter("zero");
                break;
            case R.id.circle_one_cbtn:
                ToastShow.showInCenter("one");
                break;
            case R.id.circle_two_cbtn:
                ToastShow.showInCenter("two");
                break;
        }
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public String activityName() {
        return getClass().getSimpleName();
    }

    private void init() {
        circleRl = (CircleRelativeLayout) findViewById(R.id.circle_root_crl);
        circleSl = (CircleSixLayout) findViewById(R.id.circle_menu_csl);
        //显示隐藏监听
        circleRl.setOnMoveListenerListener(circleSl);
        //布局不全屏时设置Click监听，否则可能无法监听OnTouch事件
        circleRl.setOnClickListener(this);
        int count = circleSl.getChildCount();
        for (int i = 0; i < count; i++) {
            CircleButton child = (CircleButton) circleSl.getChildAt(i);
            child.setOnClickListener(this);
        }
    }

}
