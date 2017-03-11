package longtianlove.webviewdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    View tv_view;
    H5WebView h5WebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_view=this.findViewById(R.id.tv_view);
        h5WebView= (H5WebView) this.findViewById(R.id.webview);
        h5WebView.setUrl("http://127.0.0.1:8081/index");
        h5WebView.load();


        tv_view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                h5WebView.load();
            }
        });
//        dosomething();
    }

    void dosomething() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("longtianlove","onReusme");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("longtianlove","onPause");

    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.e("longtianlove","onStop");

    }
}
