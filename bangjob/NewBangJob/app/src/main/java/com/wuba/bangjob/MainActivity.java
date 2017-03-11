package com.wuba.bangjob;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import longtianlove.newbangjob.R;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by 58 on 2017/1/16.
 */

public class MainActivity extends Activity {
    View tv_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    void initView() {
        Uri uri = Uri.parse("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
        SimpleDraweeView my_image_view = (SimpleDraweeView) this.findViewById(R.id.my_image_view);
        my_image_view.setImageURI(uri);

        tv_send = this.findViewById(R.id.tv_send);
        tv_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dosomething();
            }
        });

    }

    void dosomething() {
//        Observable<String> onserver = new Observable<String>() {
//
//        };
        Subscriber<String> subscriber=new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        Observable observable =Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onNext("hi");
                subscriber.onNext("third");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(subscriber);

    }
}
