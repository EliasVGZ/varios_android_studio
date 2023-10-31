package com.example.eligegiro;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button btnAceptar;
    private RadioGroup rgGrupo;
    private RadioButton rbnTexto, rbnImagen;
    private TextView tvTexto;
    private LinearLayout ll1;
    private EditText etGrados;

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
        etGrados = findViewById(R.id.etGrados);

        btnAceptar.setOnClickListener(this);
        rbnTexto.setOnClickListener(this);
        rbnImagen.setOnClickListener(this);
        tvTexto.setOnClickListener(this);
        ivImagen.setOnClickListener(this);
        ll1.setOnClickListener(this);
        etGrados.setOnClickListener(this);

    }


    public void onClick(View v) {
        if (v.getId() == R.id.btnAceptar) {
            String valorTexto = etGrados.getText().toString();

            if (!valorTexto.isEmpty()) {
                int valor = Integer.parseInt(valorTexto);//pasamos el string a int para usarlo en metodos matematicos

                if (valor < 10 || valor > 90) {
                    Toast.makeText(this, "Valor fuera de rango", Toast.LENGTH_SHORT).show();
                } else {
                    if (rbnTexto.isChecked()) {
                        ll1.setVisibility(View.GONE);
                        ivImagen.setVisibility(View.INVISIBLE);
                        tvTexto.setVisibility(View.VISIBLE);

                        tvTexto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvTexto.setRotation(tvTexto.getRotation() + valor);
                            }
                        });

                    } else if (rbnImagen.isChecked()) {
                        ll1.setVisibility(View.GONE);
                        tvTexto.setVisibility(View.INVISIBLE);
                        ivImagen.setVisibility(View.VISIBLE);

                        ivImagen.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ivImagen.setRotation(ivImagen.getRotation() + valor);
                            }
                        });
                    }
                }
            } else {
                Toast.makeText(this, "Debe introducir un valor", Toast.LENGTH_SHORT).show();
            }
        }
    }




}