package com.example.intents_implicitos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int LLAMADA_TELEFONO = 0;
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
                //intentDialNumeroPremarcado = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)695103238"));
                //startActivity(intentDialNumeroPremarcado);

                //averiguar si el permiso ya ha sido concedido
                if(checkSelfPermission(Manifest.permission.CALL_PHONE) == (PackageManager.PERMISSION_GRANTED)){
                    //realizar la llamada
                    intentDialNumeroPremarcado = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)695103238"));
                    startActivity(intentDialNumeroPremarcado);
                }else {
                    //Si el permiso no está concedido debemos solicitar al SO la gestion del permiso por parte del usuario
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, LLAMADA_TELEFONO);
                }
                break;

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