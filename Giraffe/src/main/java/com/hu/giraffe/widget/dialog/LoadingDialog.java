package com.hu.giraffe.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.hu.giraffe.R;

/**
 * Loading加载
 */
public class LoadingDialog extends Dialog {

    private Context context;

    public LoadingDialog(Context context){
        super(context);
        this.context=context;
    }

    public LoadingDialog(Context context,int theme){
        super(context,theme);
        this.context=context;
    }
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.dialog_loading);
        //ImageView loadingIv=findViewById(R.id.loading_iv);
//        Glide.with(context)
//                .load(R.drawable.common_loading)
//                .asGif()
//                .error(R.drawable.common_logo)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE) //缓存策略
//                .into(loadingIv);
    }

}
