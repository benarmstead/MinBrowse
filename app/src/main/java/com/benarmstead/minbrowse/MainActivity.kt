package com.benarmstead.minbrowse

import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var view: WebView
    private lateinit var settings: WebSettings

    private val homeUrl = "https://www.duckduckgo.com/?&t=ddg_android"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view = findViewById(R.id.webView)
        view.webViewClient = WebViewClient()
        view.loadUrl(homeUrl)
        settings = view.settings
        settings.javaScriptEnabled = true

        findViewById<Button>(R.id.homeButton).setOnClickListener{
            goHome()
        }

        findViewById<Button>(R.id.clearIdentity).setOnClickListener{
            cleanIdentity()
            goHome()
        }

        findViewById<Button>(R.id.toggleJS).setOnClickListener{
            toggleJS()
        }

        findViewById<Button>(R.id.goBackButton).setOnClickListener{
            goBack()
        }

        findViewById<Button>(R.id.goForwardButton).setOnClickListener{
            goForward()
        }
    }

    override fun onBackPressed() {
        goBack()
    }

    private fun goForward(){
        if (view.canGoForward()){
            view.goForward()
        }
    }

    private fun goBack(){
        if (view.canGoBack()){
            view.goBack()
        }
    }

    private fun goHome(){
        view.loadUrl(homeUrl)
    }

    private fun cleanIdentity(){
        CookieManager.getInstance().removeAllCookies(null)
        CookieManager.getInstance().flush()

        view.clearCache(true)
        view.clearHistory()
    }

    private fun toggleJS(){
        settings.javaScriptEnabled = false
        view.reload()
    }
}