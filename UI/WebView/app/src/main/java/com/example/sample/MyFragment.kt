package com.example.sample

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar


class MyFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_my, container, false)

        // WebView

        val webView = v.findViewById(R.id.webView1) as GestureWebView
        val progressBar = v.findViewById(R.id.progressBar1) as ProgressBar
        webView.webViewClient = object: WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.visibility = View.VISIBLE
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }
        }

        webView.settings.javaScriptEnabled = true

        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(webView, true)
        WebView.setWebContentsDebuggingEnabled(true)

        webView.loadUrl("https://www.google.com")

        // ToolBar

        val toolbar = v.findViewById(R.id.toolbar) as Toolbar
        toolbar.inflateMenu(R.menu.menu_toolbar)
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.btn_prev) {
                webView.goBack()
            } else if (it.itemId == R.id.btn_next) {
                webView.goForward()
            } else if (it.itemId == R.id.btn_reload) {
                webView.reload()
            }
            true
        }

        return v
    }

}