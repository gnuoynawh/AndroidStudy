package com.gnuoynawh.samples.samplehybridapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {

    private val webView: WebView by lazy {
        findViewById(R.id.webView)
    }

    private val URL = "https://www.naver.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initWebView()

    }

    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
    private fun initWebView() {

        // 웹뷰 설정
        val settings = webView.settings
        settings.javaScriptEnabled = true                           // Javascript 사용하기
        settings.javaScriptCanOpenWindowsAutomatically = true       // 외부브라우저 실행
        settings.domStorageEnabled = true                           // Setting Local Storage
        settings.builtInZoomControls = true                         // WebView 내장 줌 사용여부
        settings.displayZoomControls = false
        settings.mediaPlaybackRequiresUserGesture = false
        settings.cacheMode = WebSettings.LOAD_NO_CACHE              // 캐시사용 정의 - 보안이슈
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW  //
        settings.setSupportMultipleWindows(true)

        // 웹뷰 스크롤
        webView.isHorizontalScrollBarEnabled = false
        webView.isVerticalScrollBarEnabled = true

        // 웹뷰 UserAgent 설정
        val userAgent = settings.userAgentString
        val customString = "hybrid samples"
        settings.userAgentString = userAgent + customString

        // 웹뷰 쿠키 설정
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(webView, true)

        // 웹뷰 텍스트 고정 - 시스템 텍스트 크기 무시 (https://www.fun25.co.kr/blog/android-webview-text-zoom-setting/?category=003)
        settings.textZoom = 100

        // 웹뷰 복사방지
        webView.isDrawingCacheEnabled = false
        webView.setOnLongClickListener { _ ->
            true
        }

        // 웹뷰 Chrome Debugging
        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        // 웹뷰 custom
        webView.addJavascriptInterface(MyJavaScriptInterface(this, webView), "android")
        webView.webViewClient = MyWebViewClient(this, webView)
        webView.webChromeClient = MyWebChromeClient(this, webView)

        webView.loadUrl(URL)
    }
}