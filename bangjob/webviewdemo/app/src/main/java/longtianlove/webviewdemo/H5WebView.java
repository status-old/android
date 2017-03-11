package longtianlove.webviewdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

/**
 * Created by 58 on 2017/1/18.
 */

public class H5WebView extends RelativeLayout {
    public H5WebView(Context context) {
        super(context);
        this.context = context;
    }

    public H5WebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public H5WebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    RelativeLayout.LayoutParams layoutlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    Context context;
    WebView webview;

    /**
     * 加载的url
     */
    public String mUrl;

    public void setUrl(String url) {
        mUrl = url;
    }


    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    void initWeb() {
        webview = new WebView(context); //初始化WebView
        WebSettings webSettings = webview.getSettings();//获取Setting设置
        webSettings.setJavaScriptEnabled(true);//允许Js交互 如果JS交互必须要设置true
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持js打开窗口
        webSettings.setDomStorageEnabled(false);//设置LocalStorage可用性
        webSettings.setAllowFileAccess(true);//支持读取本地文件
        webSettings.setAppCacheEnabled(false);//设置允许本地缓存
        webSettings.setDatabaseEnabled(false);//设置本地数据库操作
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //设置缓存模式
        webSettings.setDefaultTextEncodingName("UTF-8"); //设置编码格式

        if (Build.VERSION.SDK_INT >= 21) {//设置webView支持Https与Http 混合页面资源支持  如果不加入的话 HTTPS中不能有HTTP的URL

            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        }
        try {
            webSettings.setSavePassword(false);//设置可以保存密码
        } catch (Exception e) {
            e.printStackTrace();
        }

        webview.removeJavascriptInterface("accessibility");//防止JS安全漏洞
        webview.removeJavascriptInterface("accessibilityTraversal");//防止JS安全漏洞


        webview.setLayoutParams(layoutlp);
        addView(webview);


        webview.setWebChromeClient(new H5ChromeClient());//设置WebView交互监听 包括标题变化监听，页面加载进度监听 ，文件选择监听，JS弹框监听 等交互事件
        webview.setWebViewClient(new H5WebViewClient());//设置WebView状态监听 页面加载是否拦截，页面加载开始 结束状态，页面错误状态，HTTPS加载状态等。


        webview.loadUrl(mUrl);
    }

    /**
     * 加载webview
     */
    public void load() {
        if (webview != null) {
            webview.reload();
        } else {
            initWeb();
        }
    }

    /**
     * 销毁webview
     */
    public void destroy() {
        if (webview != null) {
            removeView(webview);
            webview.clearHistory();
            context.deleteDatabase("webview.db");
            context.deleteDatabase("webviewCache.db");
            webview.destroy();
        }
    }

    public class H5ChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            //获取title
            Log.e("long-onReceivedTitle", title);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            //返回每个页面的进度 0~100
            Log.e("long-onProgressChanged", newProgress + "");
        }
    }

    public class H5WebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // 返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边

            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            // 开始加载页面资源
            Log.e("long-onPageStarted", url);

        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            super.onPageCommitVisible(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            // 结束加载页面资源
            Log.e("long-onPageFinished", url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            //页面错误的时候触发 errorCode 代表错误码 description代表描述
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            //HTTPS信任
        }
    }
}
