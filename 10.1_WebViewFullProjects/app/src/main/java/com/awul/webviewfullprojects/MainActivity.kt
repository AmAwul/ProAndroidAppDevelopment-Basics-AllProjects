package com.awul.webviewfullprojects

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.awul.webviewfullprojects.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.webView.apply {
            webViewClient = webViewClient(binding)
            webChromeClient = webChromeClient(binding)
            loadUrl("https://touhidapps.com")
            settings.javaScriptEnabled = true
            settings.setSupportZoom(true)
            settings.builtInZoomControls = true
            settings.displayZoomControls = true
        }

        clickListeners()













    }// onCreate

    private fun clickListeners() {

        binding.btnBack.setOnClickListener {
            if (!isOnline(this)) {
                Toast.makeText(this, "No internet available", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.webView.canGoBack()) {
                binding.webView.goBack()
            }
        }

        binding.btnForward.setOnClickListener {
            if (!isOnline(this)) {
                Toast.makeText(this, "No internet available", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.webView.canGoForward()) {
                binding.webView.goForward()
            }
        }


        binding.btnGo.setOnClickListener {
            if (!isOnline(this)) {
                Toast.makeText(this, "No internet available", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                val url_ed = binding.edtUrl.text.toString()
                binding.webView.loadUrl("https://$url_ed")
            }

        }

    } // listeners

    private fun isOnline(context: Context): Boolean {

        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected

    }

    class webViewClient(var binding: ActivityMainBinding): WebViewClient() {
        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)

        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            binding.progressBarLinear.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.progressBarLinear.visibility = View.GONE
        }

    }

    class webChromeClient(var binding: ActivityMainBinding): WebChromeClient() {

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)

            binding.progressBarLinear.progress = newProgress

        }

    }
}