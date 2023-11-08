package com.example.activities_intent_ejemplos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Log.i("CICLO", "EJECUTANDO ONCREATE ACTVITY 2");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("CICLO", "EJECUTANDO ONSTART ACTIVITY 2");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("CICLO", "EJECUTANDO ON STOP ACTIVITY 2");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("CICLO", "EJECUTANDO on RESUME EN ACTIVITY 2");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("CICLO", "EJECUTANDO ONDESTROY EN ACTIVITY 2");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("CICLO", "EJECUTANDO ONRESTAR EN ACTIVITY 2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("CICLO", "EJECUTANDO ON PAUSE EN ACTIVITY 2");
    }
}