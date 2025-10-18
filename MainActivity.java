package com.user.com; // ← তোমার প্যাকেজ নাম লিখো

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        // সুন্দর transition effect
        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        // প্রথমে ইন্টারনেট চেক করো
        if (isConnected()) {
            myWebView.loadUrl("https://ysgtprosujit.netlify.app/m1");
        } else {
            showOfflinePage();
        }

        // Error handle করো (যাতে URL না দেখা যায়)
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                showOfflinePage();
            }
        });
    }

    // 🔹 ইন্টারনেট কানেকশন চেক করার method
    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    // 🔹 Custom offline পেজ (দারুণ লুক)
    private void showOfflinePage() {
        String htmlData = "<html><body style='background: linear-gradient(135deg,#673AB7,#512DA8);"
                + "color:white; text-align:center; font-family:sans-serif;'>"
                + "<div style='margin-top:40%;'>"
                + "<h1 style='font-size:36px;'>📶 No Internet</h1>"
                + "<p style='font-size:18px;'>Please connect to Wi-Fi or Mobile Data</p>"
                + "<button style='margin-top:20px; background:#FF9800; border:none; color:white; "
                + "padding:10px 25px; border-radius:25px; font-size:16px;' "
                + "onclick='location.reload()'>🔄 Try Again</button>"
                + "</div></body></html>";
        myWebView.loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null);
    }

    // 🔹 ব্যাক প্রেস করলে WebView এর ভেতরে ফিরে যাওয়া
    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}