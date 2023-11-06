package com.example.ciclodevida;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("CICLO", "Ejecutando on create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "ejecutando onstart", Toast.LENGTH_SHORT).show();
        Log.i("CICLO", "ejecutando onstart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "ejecutando onstopppppppp", Toast.LENGTH_SHORT).show();
        Log.i("CICLO", "ejecutando ON STOP");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "ejecutando ON RESUME", Toast.LENGTH_SHORT).show();
        Log.i("CICLO", "ejecutando on RESUME");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "EJECUTANDO ON DESTROYY", Toast.LENGTH_SHORT).show();
        Log.i("CICLO", "ejecutando ONDESTROY");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "EJECUTANDO ON RESTARTT!!!", Toast.LENGTH_SHORT).show();
        Log.i("CICLO", "ejecutando ONRESTAR!!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "EJECUTANDO ON PAUSEWEE!!!", Toast.LENGTH_SHORT).show();
        Log.i("CICLO", "ejecutando ON PAUSE");
    }

    public void onClickFinalizar(View view){
        finish();
    }




}