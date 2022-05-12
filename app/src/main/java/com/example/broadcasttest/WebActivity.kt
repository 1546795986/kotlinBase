package com.example.broadcasttest

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class WebActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_webview)
        val webView:WebView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.baidu.com")
    }
    fun sendRequestWithHttpUrlConnection(){
        thread {
            var connection :HttpURLConnection?=null
            try {
                val response = StringBuilder()
                val url = URL("s")
                connection= url.openConnection() as HttpURLConnection
                connection.connectTimeout = 5999
                connection.readTimeout = 3999
                val input = connection.inputStream
                val reader =BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            }catch (e:Exception)
            {e.printStackTrace()}
        }
    }

    private fun showResponse(toString: String) {
        runOnUiThread {
            Log.d("tag","dd")
        }
    }
}