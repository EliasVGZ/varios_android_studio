package com.example.eligegiro;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button btnAceptar;
    private RadioGroup rgGrupo;
    private RadioButton rbnTexto, rbnImagen;
    private TextView tvTexto;
    private LinearLayout ll1;

    private ImageView ivImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAceptar = findViewById(R.id.btnAceptar);
        rgGrupo = findViewById(R.id.rg_grupo);
        rbnTexto = findViewById(R.id.rbnTexto);
        rbnImagen = findViewById(R.id.rbnImagen);
        tvTexto = findViewById(R.id.tvGirando);
        ivImagen = findViewById(R.id.ivImagen);
        ll1 = findViewById(R.id.ll1);

        btnAceptar.setOnClickListener(this);
        rbnTexto.setOnClickListener(this);
        rbnImagen.setOnClickListener(this);
        tvTexto.setOnClickListener(this);
        ivImagen.setOnClickListener(this);
        ll1.setOnClickListener(this);

    }


    public void onClick(View v) {
        if (v.getId() == R.id.btnAceptar) {
            if (rbnTexto.isChecked()) {
                ll1.setVisibility(View.GONE);
                ivImagen.setVisibility(View.INVISIBLE);
                tvTexto.setVisibility(View.VISIBLE);

                //GIRE 45 GRADOS AL CLICKEAR ENCIMA DEL TEXTO
                tvTexto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvTexto.setRotation(tvTexto.getRotation() + 45);
                    }
                });
            } else if (rbnImagen.isChecked()) {
                ll1.setVisibility(View.GONE);
                tvTexto.setVisibility(View.INVISIBLE);
                ivImagen.setVisibility(View.VISIBLE);

                //GIRE 45 GRADOS AL CLICKEAR ENCIMA DEL TEXT
                ivImagen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ivImagen.setRotation(ivImagen.getRotation() + 45);
                    }
                });
            }
        }
    }



}