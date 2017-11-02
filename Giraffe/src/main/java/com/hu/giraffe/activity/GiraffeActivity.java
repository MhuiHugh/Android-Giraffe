package com.hu.giraffe.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hu.giraffe.R;
import com.hu.giraffe.utils.LogUtil;
import com.hu.widget.StatusBarCompat;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class GiraffeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giraffe);
        init();
    }

    private void init() {
        StatusBarCompat.compat(this,getResources().getColor(R.color.colorPrimaryDark));

        Toolbar toolbar = (Toolbar) findViewById(R.id.giraffe_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.giraffe_floating_action_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //--------------------------------------

    @Override
    public String activityName() {
        return getClass().getSimpleName();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    //--------------------------------------

    private void rxDemoZero(){
        LogUtil.v("rxDemoZero");
        String[] numbers={"zero","one","two","three","four","five"};
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        Observable.just(1,2,3,4,5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        //网络嵌套的请求示例
//        getToken()
//                .flatMap(new Func1<String, Observable<User>>() {
//                             @Override
//                             public Observable<User> onNext(String token) {
//                                 return getUser(token, userId);
//                             })
//                                     .observeOn(AndroidSchedulers.mainThread())
//                                     .subscribe(new Observer<User>() {
//                                 @Override
//                                 public void onNext(User user) {
//                                     userView.setUser(user);
//                                 }
//
//                                 @Override
//                                 public void onCompleted() {
//                                 }
//
//                                 @Override
//                                 public void onError(Throwable error) {
//                                     // Error handling
//            ...
//                                 }
//                             });

    }

    //----------flatMap()变换
    //观察者
    Subscriber<String> subscriber=new Subscriber<String>() {
        @Override
        public void onSubscribe(Subscription s) {

        }

        @Override
        public void onNext(String s) {

        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {

        }
    };

}
