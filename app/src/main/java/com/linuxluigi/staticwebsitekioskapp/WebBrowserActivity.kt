package com.linuxluigi.staticwebsitekioskapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_web_browser.*
import android.webkit.WebViewClient


class WebBrowserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_browser)

        // init WebView
        initWebView("http", "localhost")
    }

    private fun initWebView(scheme: String, hostname: String) {
        // load default URL
        var url = scheme + "://" + hostname
        kioskWebView.loadUrl(url)

        // remove url bar
        kioskWebView.webViewClient = LocalhostWebViewClient()


        // setup settings
        kioskWebView.settings.javaScriptEnabled = true
        kioskWebView.settings.javaScriptCanOpenWindowsAutomatically = false
    }

}

class LocalhostWebViewClient : WebViewClient() {
    // custom WebView witch only allow localhost urls
    fun shuldOverrideKeyEvent(view: WebView, event: KeyEvent): Boolean {
        return true
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        // check if next url is on host with hostname
        var hostname = request!!.url.host
        println(hostname)
        return request!!.url.host != "localhost"
    }
}