package com.bykrkc.webviewornegi;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    ProgressDialog progressDialog;
    RelativeLayout relativeLayout;
    String url = "http://bykrkc.com/android/m";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yukleniyor();
        if (InternetConnectivity.isConnected(getApplicationContext())) {
            webViewIslemleri();
            webView.loadUrl(url);
        } else {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Uyarı")
                    .setMessage("Bağlantı hatası!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    }).setCancelable(false).show();
        }

    }

    void yukleniyor() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(getResources().getString(R.string.yukleniyor));
        progressDialog.show();
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    void webViewIslemleri() {
        relativeLayout = (RelativeLayout) findViewById(R.id.rl);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.setWebViewClient(new webViewClient());
        webView.addJavascriptInterface(new webViewAppInterface(this), "Android");
    }

    class webViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressDialog.dismiss();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            webView.loadUrl(url);
            return true;
        }
    }

    public class webViewAppInterface {
        Context mContext;

        webViewAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void ac() {
            Intent i = new Intent(MainActivity.this, DigerSayfa.class);
            startActivity(i);
        }

    }

}
