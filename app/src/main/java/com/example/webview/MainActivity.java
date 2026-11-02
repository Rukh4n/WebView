package com.example.webview;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    private static final String WEB_URL = "https://siposportal.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        webView = findViewById(R.id.webView);

        // Pengaturan WebView
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);   // wajib untuk YouTube
        settings.setDomStorageEnabled(true);   // wajib untuk YouTube
        settings.setMediaPlaybackRequiresUserGesture(false);

        // Agar link tetap terbuka di dalam aplikasi, bukan di browser luar
        webView.setWebViewClient(new WebViewClient());

        // Memuat website sesuai WEB_URL di atas
        webView.loadUrl(WEB_URL);

        // Tombol back: mundur halaman di WebView, bukan langsung keluar aplikasi
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });
    }
}