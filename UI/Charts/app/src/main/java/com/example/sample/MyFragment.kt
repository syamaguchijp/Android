package com.example.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.google.gson.Gson

class MyFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_my, container, false)

        val webView = v.findViewById(R.id.webView) as WebView

        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()

        webView.webViewClient = object : WebViewClient() {
            // ページ読み込み完了後に、JSが利用できるようになる
            override fun onPageFinished(view: WebView, url: String) {
                // 円グラフ
                // 2次元配列にしても、Androidの場合はなぜかJS側には一次元配列で渡ってしまう
                val dataArray = arrayOf(
                    arrayOf("東京", 50), arrayOf("大阪", 30), arrayOf("新潟", 10))
                var dataStr = Gson().toJson(dataArray)
                webView.evaluateJavascript("window.drawPieChart(${dataStr})", null)

                // 折れ線グラフ
                val lineChartDataArray = arrayOf(
                    arrayOf("2018", 800, 120),
                    arrayOf("2019", 600, 100),
                    arrayOf("2020", 900, 150),
                    arrayOf("2021", 500, 170))
                var lineChartDataStr = Gson().toJson(lineChartDataArray)
                webView.evaluateJavascript("window.drawLineChart(${lineChartDataStr})", null)
            }
        }

        webView.loadUrl("file:///android_asset/test.html")

        // JS側から発信されたメッセージを受け取る場合の例
        webView.addJavascriptInterface(JSCallbackInterface(), "AndroidWebView")

        return v
    }

    // JS側から発信されたメッセージを受け取る場合の例
    class JSCallbackInterface() {
        @JavascriptInterface
        fun answerFromJs(message: String) {
            println("called from JS!!! ${message}")
        }
    }
}