package edu.val.ejemploslibroandroidapp.tema3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import edu.val.ejemploslibroandroidapp.R;


public class ActividadWebViewFrameLayout extends AppCompatActivity {

    private WebView webView;
    private static final String WEB_LOCAL = "file:///android_asset/aviso.html";
    private static final String WEB_REMOTA = "https://www.google.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_web_frame);
        this.webView = findViewById(R.id.webview);
        //cargar una página ASSETS / ACTIVO -ECONÓMICO -
        //this.webView.loadUrl(WEB_LOCAL);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl(WEB_REMOTA);
        this.webView.setWebViewClient(new MyWebViewClient());//personalizo el navegador

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //si es una página de google, nos quedamos en la app
            if ("www.google.com".equals(Uri.parse(url).getHost())) {
                // This is my website, so do not override; let my WebView load the page
                return false;
            }
            //si no, solictamos abrirlo con el navegador
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }

}