package com.example.intent_filter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class ExplicitActivity extends AppCompatActivity {

    private WebView webview_explicita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);

        webview_explicita = findViewById(R.id.webview_explicita);

        //capturar el intent que nos han enviado
        Intent intent = getIntent();
        String urlRecibida = intent.getExtras().getString("url_key");

        //cargar web loadUrl(), carga una url en un objeto webview
        webview_explicita.loadUrl(urlRecibida);

    }
}