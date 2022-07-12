package com.gnuoynawh.samples.samplehybridapp

import android.app.AlertDialog
import android.os.Message
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebView.WebViewTransport

class MyWebChromeClient(
    private val activity: MainActivity,
    private val webView: WebView
) : WebChromeClient() {

    override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
        AlertDialog.Builder(activity)
            .setMessage(message)
            .setPositiveButton("확인") { dialog, _ ->
                result?.confirm()
                dialog.dismiss()
            }
            .create()
            .show()
        return true
    }

    override fun onJsConfirm(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
        AlertDialog.Builder(activity)
            .setMessage(message)
            .setPositiveButton("확인") { dialog, _ ->
                result?.confirm()
                dialog.dismiss()
            }.setNegativeButton("취소") { dialog, _ ->
                result?.cancel()
                dialog.dismiss()
            }
            .create()
            .show()
        return true
    }

    override fun onCreateWindow(view: WebView?, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean {
        val newWebView = WebView(activity)
        val transport = resultMsg!!.obj as WebViewTransport
        transport.webView = newWebView
        resultMsg.sendToTarget()
        return true
    }

    override fun onCloseWindow(window: WebView?) {
        super.onCloseWindow(window)
    }
}