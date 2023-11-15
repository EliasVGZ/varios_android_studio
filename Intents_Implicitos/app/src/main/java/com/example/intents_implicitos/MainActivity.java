package com.example.intents_implicitos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int LLAMADA_TELEFONO = 0;
    private static final int LLAMADA_TELEFONO_DIRECTO = 2;
    Intent intent, intentDial, intentDialNumeroPremarcado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBotones(View view){



        switch (view.getId()){
            case R.id.btn_contactos:
                //generar la accion del intent implicit: ACTION_VIEW

                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                startActivity(intent);
                break;
            case R.id.btn_llamar_dial:
                intentDial = new Intent(Intent.ACTION_DIAL);
                startActivity(intentDial);
                break;
            case R.id.btn_llamar_dial_con_numero:
                intentDialNumeroPremarcado = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+34)986986986"));
                startActivity(intentDialNumeroPremarcado);
                break;
            case R.id.btn_call_directo:

                if(Build.VERSION.SDK_INT >= 23){ //TODO Evitar la ejecucion en APIS anteriores
                    //averiguar si el permiso ya ha sido concedido
                    if(checkSelfPermission(Manifest.permission.CALL_PHONE) == (PackageManager.PERMISSION_GRANTED)){
                        //realizar la llamada
                        intentDialNumeroPremarcado = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)695103238"));
                        startActivity(intentDialNumeroPremarcado);
                    }else {
                        //Si el permiso no está concedido debemos solicitar al SO la gestion del permiso por parte del usuario
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, LLAMADA_TELEFONO);
                    }
                }else{
                    //En APIS anteriores a la 23
                    //Realizar la acción: en este caso realizar la llamada telefónica directa
                    intentDialNumeroPremarcado = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)695103238"));
                    startActivity(intentDialNumeroPremarcado);
                }
                break;
            case R.id.btn_call_directo_compat:

                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == (PackageManager.PERMISSION_GRANTED)){
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)695103238"));
                    startActivity(intent);
                }else{
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, LLAMADA_TELEFONO_DIRECTO);
                }
                break;

            case R.id.btn_llamada_url:
                String url = "https://www.elpais.es";
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                //verificar si hay algun explorado para abrir
                if(intent.resolveActivity(getPackageManager() )!= null){
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "ESTA ACCION NO SE PUEDE REALIZAR!!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_posicion_mapa:

                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:42.25,-8.68"));
                //intent.setPackage("com.google.android.apps.map");
                if(intent.resolveActivity(getPackageManager() )!= null){
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "ESTA ACCION NO SE PUEDE REALIZAR!!", Toast.LENGTH_SHORT).show();
                }

        }
    }

    //Método en donde recibimos la respuesta que ha dado el usuario.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Vemos si el request code es el que yo envié
        if(requestCode == LLAMADA_TELEFONO){

            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //Acciones correspondientes al permiso concedido
                //Hago la llamada al intent para LLAMAR
                intentDialNumeroPremarcado = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)695103238"));
                startActivity(intentDialNumeroPremarcado);
                Toast.makeText(this, "El usuario ha aceptado el permiso de llamada", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "El usuario ha deenegado el permiso de llamada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}