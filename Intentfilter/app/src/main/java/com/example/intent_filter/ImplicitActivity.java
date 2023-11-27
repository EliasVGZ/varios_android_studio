package com.example.intent_filter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class ImplicitActivity extends AppCompatActivity {

    private WebView webview_implicita;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        webview_implicita = findViewById(R.id.webview_implicita);

        //===========
        WebSettings ws = webview_implicita.getSettings();//CUANDO NECESITA JAVASCRIPT LA WEB
        ws.setJavaScriptEnabled(true);//Habilitar javascript para nuestro dispositivo
        //===========


        Intent intent = getIntent();
        Uri uriRecibida = intent.getData();//capturar el URI que le pasamos
        webview_implicita.loadUrl(uriRecibida.toString());//El loadurl NECESITA STRING!!!!!!!!!!!!!!!!!


    }
}