package com.example.activities_intent_ejemplos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String DatoAEnviar;
    private static final int CODIGO_LLAMADA_ACT5 = 1;
    private static final int CODIGO_LLAMADA_ACT6 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("CICLO", "ejecutando ONCREATE ACTVITYMAIN");


    }

    public void onClickCambioActivity(View view) {
        if (view.getId() == R.id.btnLlamadaActividad2) {
            //crear un objeto Intent
            Intent intent = new Intent(this, Activity2.class);
            //para realizar la llamada hay que hacer
            startActivity(intent);
        } else if (view.getId() == R.id.btnLlamadaActividad3) {
            Intent myintent = new Intent(this, Activity3.class);
            //COLOCAMOS EL DATO A ENVIAR
            DatoAEnviar = "El activity 1 envia mensaje al activity 3";
            //añadir dato al input, le damos un nombre al dato y le mandamos el dato
            myintent.putExtra("mensaje", DatoAEnviar);

            startActivity(myintent);

        }
        //LLAMADA A LA ACITIVITY 4
        else if (view.getId() == R.id.btnLlamadaActividad4Bundle) {
            Intent intent = new Intent(this, Activity4.class);
            //DATO A ENVIAR
            DatoAEnviar = "El activity 1 envia mensaje al activity 4";
            //INSTANCIA DE OBJETO BUNDLE
            Bundle bundle = new Bundle();
            //LE PASAMOS AL BUNDLE UNA KEY Y EL MENSAJE
            bundle.putString("mensaje", DatoAEnviar);
            //METEMOS AL INTENT EL PAQUETE BUNDLE, EL EXTRAS SERA EN PLURAL
            intent.putExtras(bundle);

            startActivity(intent);

        }
        //LLAMADA A LA ACTIVITY 5
        else if (view.getId() == R.id.btnLlamadaEsperandoRespuesta) {
            Intent intent = new Intent(this, Activity5.class);
            //LLAMADA ESPERANDO RESPUESTA
            startActivityForResult(intent, CODIGO_LLAMADA_ACT5);
        } else if (view.getId() == R.id.btnLlamadaOtraApp) {
            Intent intent = new Intent();
            //esto es para llamar a otra app
            //NOMBRE DEL PAQUETE + NOMBRE DEL PAKETE Y NOMBRE CLASE
            intent.setClassName("com.example.cambiodemoneda", "com.example.cambiodemoneda.MainActivity");
            //startActivity(intent);

            /*try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(MainActivity.this, "Ninguna ac􀆟vidad puede realizar esta acción", Toast.LENGTH_SHORT).show();
            }*/
            //TODO Llamamos al packagemanera para preguntarle si existe el activity

            PackageManager pm = getPackageManager();
            List actividadesPosibles = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if (actividadesPosibles.size() > 0) {
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Ninguna actividad puede realizar esta acción", Toast.LENGTH_SHORT).show();
            }

        }//BOTON PARA LLAMAR A LA CALCULADORA
        else if (view.getId() == R.id.btnLlamadaCalculadora) {
            Intent intentCalculadora = new Intent();
            intentCalculadora.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
            //todo esta vez uso el try catch que hace lo mismo, llamada al packmanger por debajo
            try {
                startActivity(intentCalculadora);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(MainActivity.this, "Ninguna ac􀆟vidad puede realizar esta acción", Toast.LENGTH_SHORT).show();
            }
        } else if (view.getId() == R.id.btnLlamadaAjustes) {
            Intent intentAjustes = new Intent();
            intentAjustes.setClassName("com.android.settings",
                    "com.android.settings.Settings");
            //todo esta vez uso el try catch que hace lo mismo, llamada al packmanger por debajo
            try {
                startActivity(intentAjustes);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(MainActivity.this, "Ninguna ac􀆟vidad puede realizar esta acción", Toast.LENGTH_SHORT).show();
            }
        }
        else if (view.getId() == R.id.btnLlamadaWeb) {
            //Intent intent = new Intent();
            String urlMarca = "http://www.marca.com";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlMarca));

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(MainActivity.this, "Ninguna ac􀆟vidad puede realizar esta acción", Toast.LENGTH_SHORT).show();
            }
        }
        if(view.getId() == R.id.btnLlamadaAct6){
            Intent intent = new Intent(this, Activity6.class);
            //LLAMADA ESPERANDO RESPUESTA
            startActivityForResult(intent, CODIGO_LLAMADA_ACT6);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i("CICLO", "Ejecutando onactivityresult en mainactivity");
        super.onActivityResult(requestCode, resultCode, data);
        //VAMOS A VER QUE ACTIVITY NOS CONTESTA
        if (requestCode == CODIGO_LLAMADA_ACT5){
            //REALIZAR OPERACIONES PERTINENTES


            //testeamos el codigo del resultado
            if(resultCode == RESULT_OK){
                //operaciones si la actividad llamada finalizo segun lo previsto
                TextView tvRespuesta = findViewById(R.id.tv_RespuestaAct5);
                tvRespuesta.setText(data.getStringExtra("respuesta"));
                Toast.makeText(this, "Mensaje recibido", Toast.LENGTH_SHORT).show();
            }else{
                //operaciones si la actividad llamada NO finalizo segun lo previsto
                Toast.makeText(this, "Mensaje no recibido", Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == CODIGO_LLAMADA_ACT6){
            if(resultCode == RESULT_OK){
                //operaciones si la actividad llamada finalizo segun lo previsto
                TextView tvRespuesta = findViewById(R.id.tv_RespuestaAct6);
                tvRespuesta.setText(data.getStringExtra("respuesta"));
                Toast.makeText(this, "Mensaje recibido", Toast.LENGTH_SHORT).show();
            }else{
                //operaciones si la actividad llamada NO finalizo segun lo previsto
                Toast.makeText(this, "Mensaje no recibido", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("CICLO", "ejecutando ONSTART MAINACTIVITY");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("CICLO", "ejecutando ON STOP MAINACTIVITY");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("CICLO", "ejecutando on RESUME EN MAINACTIVITY");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("CICLO", "ejecutando ONDESTROY EN MAINACTIVITY");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("CICLO", "ejecutando ONRESTAR EN MAINACTIVITY");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("CICLO", "ejecutando ON PAUSE EN MAINACTIVITY");
    }
}






















