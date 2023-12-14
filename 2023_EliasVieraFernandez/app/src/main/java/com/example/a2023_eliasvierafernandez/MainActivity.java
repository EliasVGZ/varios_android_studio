package com.example.a2023_eliasvierafernandez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {

    private ImageButton img_logo;
    private LinearLayout ll1;

    private RadioGroup rg_grupo;
    private RadioButton rbn_calcular, rbn_informacion;
    private Button btn_vacio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        img_logo = findViewById(R.id.img_logo);
        ll1 = findViewById(R.id.ll1);
        rg_grupo = findViewById(R.id.rg_grupo);
        rbn_calcular = findViewById(R.id.rbn_calcular);
        rbn_informacion = findViewById(R.id.rbn_informacion);
        btn_vacio = findViewById(R.id.btn_vacio);

        img_logo.setOnLongClickListener(this);
        ll1.setOnLongClickListener(this);
        rg_grupo.setOnClickListener(this);
        rbn_calcular.setOnClickListener(this);
        rbn_informacion.setOnClickListener(this);
        btn_vacio.setOnClickListener(this);

        rbn_calcular.setChecked(true);


        rbn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbn_calcular.isChecked()) {
                    String boton1 = "CALCULAR IMC";
                    btn_vacio.setText(boton1);
                }
            }
        });
        rbn_informacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbn_informacion.isChecked()) {
                    String boton2 = "Más informacion";
                    btn_vacio.setText(boton2);
                }
            }
        });

    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.img_logo) {
            /*Intent intent = new Intent(this, Activity2.class);
            startActivity(intent);*/
            img_logo.setVisibility(View.GONE);
            ll1.setVisibility(View.VISIBLE);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_vacio) {
            if (rbn_calcular.isChecked()) {
                Intent intent = new Intent(this, Activity3.class);
                startActivity(intent);
            } else if (rbn_informacion.isChecked()) {
                Intent intent = new Intent(this, Activity5.class);
                startActivity(intent);
            }
        }


    }
}