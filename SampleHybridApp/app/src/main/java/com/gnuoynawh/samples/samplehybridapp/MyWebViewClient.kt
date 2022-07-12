package com.gnuoynawh.samples.samplehybridapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.http.SslError
import android.webkit.*

class MyWebViewClient(
    val activity: MainActivity,
    val webView: WebView
) : WebViewClient() {

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
    }

    @SuppressLint("WebViewClientOnReceivedSslError")
    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        AlertDialog.Builder(activity)
            .setMessage("이 사이트의 보안 인증서는 신뢰하는 보안 인증서가 아닙니다. 계속하시겠습니까?")
            .setPositiveButton("계속하기") { _, _ ->
                handler!!.proceed()
            }.setNegativeButton("취소") { _, _ ->
                handler!!.cancel()
            }.create()
            .show()
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        val errorCode = error!!.errorCode

        when (errorCode) {
            ERROR_AUTHENTICATION,           // 서버에서 사용자 인증 실패
            ERROR_BAD_URL,                  // 잘못된 URL
            ERROR_CONNECT,                  // 서버로 연결 실패
            ERROR_FAILED_SSL_HANDSHAKE,     // SSL handshake 수행 실패
            ERROR_FILE,                     // 일반 파일 오류
            ERROR_FILE_NOT_FOUND,           // 파일을 찾을 수 없습니다
            ERROR_HOST_LOOKUP,              // 서버 또는 프록시 호스트 이름 조회 실패
            ERROR_IO,                       // 서버에서 읽거나 서버로 쓰기 실패
            ERROR_PROXY_AUTHENTICATION,     // 프록시에서 사용자 인증 실패
            ERROR_REDIRECT_LOOP,            // 너무 많은 리디렉션
            ERROR_TIMEOUT,                  // 연결 시간 초과
            ERROR_TOO_MANY_REQUESTS,        // 페이지 로드중 너무 많은 요청 발생
            ERROR_UNKNOWN,                  // 일반 오류
            ERROR_UNSUPPORTED_AUTH_SCHEME,  // 지원되지 않는 인증 체계
            ERROR_UNSUPPORTED_SCHEME -> {}  // URI가 지원되지 않는 방식
        }

        if (errorCode == ERROR_CONNECT || errorCode == ERROR_TIMEOUT) {
            AlertDialog.Builder(activity)
                .setMessage("서버접속이 원할하지 않습니다.")
                .setPositiveButton("종료") { dialog, _ ->
                    dialog.dismiss()
                    activity.finish()
                }.create()
                .show()
        }

        super.onReceivedError(view, request, error)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {

        //
        //
        //

        return super.shouldOverrideUrlLoading(view, request)
    }
}