package com.example.a2023_eliasvierafernandez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Activity5 extends AppCompatActivity implements View.OnClickListener {

    private RadioButton rbn_web, rbn_tel;
    private Button btn_ok, btn_atras;
    private static final int LLAMADA_TELEFONO_DIRECTO = 2;
    private static final int LLAMADA_TELEFONO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        rbn_web = findViewById(R.id.rbn_web);
        rbn_tel = findViewById(R.id.rbn_tel);
        btn_ok = findViewById(R.id.btn_ok);
        btn_atras = findViewById(R.id.btn_atras);

        rbn_web.setOnClickListener(this);
        rbn_tel.setOnClickListener(this);
        btn_atras.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_ok) {
            if (rbn_web.isChecked()) {
                String url = "https://es.wikipedia.org/wiki/Indice_de_masa_corporal";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "ESTA ACCION NO SE PUEDE REALIZAR!!", Toast.LENGTH_SHORT).show();
                }

            }
            if (rbn_tel.isChecked()) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == (PackageManager.PERMISSION_GRANTED)) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)915555555"));
                    startActivity(intent);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, LLAMADA_TELEFONO_DIRECTO);
                }
            }
        } else if (v.getId() == R.id.btn_atras) {
            finish();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Vemos si el request code es el que yo envié
        if (requestCode == LLAMADA_TELEFONO) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34)695103238"));
                startActivity(intent);
                Toast.makeText(this, "El usuario ha aceptado el permiso de llamada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El usuario ha deenegado el permiso de llamada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}