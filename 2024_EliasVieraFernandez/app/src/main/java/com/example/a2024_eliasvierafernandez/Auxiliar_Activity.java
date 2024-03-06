package com.example.a2024_eliasvierafernandez;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Auxiliar_Activity extends AppCompatActivity {

    private static final int LLAMADA_TELEFONO_DIRECTO = 2;


    //todo en auxiliar se puede cargar el onCreate
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_auxiliar);
//
//    }

    //TODO -> CLASE AUXILIAR PARA TENER EL MENU EN VARIOS LAYOUTS

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);


        return super.onCreateOptionsMenu(menu);
    }


    //Listener para menu de opciones!!!

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.opc_insertar:
                Intent intent = new Intent(Auxiliar_Activity.this, Activity3.class);
                startActivity(intent);
                return true;
            case R.id.opc_contacto:
                dialogo_ventana_2botones();
                return true;



        }

        return super.onOptionsItemSelected(item);
    }

    private void dialogo_ventana_2botones() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setIcon(R.drawable.flask1)
                .setMessage("IES DE TEIS \n ¿Conectar con nosotros? ")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(Build.VERSION.SDK_INT >= 23){ //TODO Evitar la ejecucion en APIS anteriores
                            //averiguar si el permiso ya ha sido concedido
                            if(checkSelfPermission(Manifest.permission.CALL_PHONE) == (PackageManager.PERMISSION_GRANTED)){
                                //realizar la llamada
                                Intent intentDialNumeroPremarcado = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)986986986"));
                                startActivity(intentDialNumeroPremarcado);
                            }else {
                                //Si el permiso no está concedido debemos solicitar al SO la gestion del permiso por parte del usuario
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, LLAMADA_TELEFONO_DIRECTO);
                            }
                        }else{
                            //En APIS anteriores a la 23
                            //Realizar la acción: en este caso realizar la llamada telefónica directa
                            Intent intentDialNumeroPremarcado = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)986986986"));
                            startActivity(intentDialNumeroPremarcado);
                        }

                        dialog.cancel();//Va hacia atrás
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                })
                .setTitle("Contacto")//Título
                .setCancelable(false);//No se cierra la venta hasta que le de al boton

        ventana.show();
    }

}