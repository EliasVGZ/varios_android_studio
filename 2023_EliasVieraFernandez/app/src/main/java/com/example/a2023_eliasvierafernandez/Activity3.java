package com.example.a2023_eliasvierafernandez;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Activity3 extends AppCompatActivity implements View.OnClickListener {

    private EditText et_metros, et_kg, et_pulgadas, et_libras;
    private Button btn_calcular, btn_fin, sis_metrico, btn_calcular2, btn_fin2;

    private LinearLayout ll1, ll2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        et_metros = findViewById(R.id.et_metros);
        et_kg = findViewById(R.id.et_kg);
        btn_calcular = findViewById(R.id.btn_calcular);
        btn_fin = findViewById(R.id.btn_fin);
        ll1 = findViewById(R.id.ll1);
        ll2 = findViewById(R.id.ll2);
        sis_metrico = findViewById(R.id.sis_metrico);
        btn_calcular2 = findViewById(R.id.btn_calcular2);
        btn_fin2 = findViewById(R.id.btn_fin2);
        et_pulgadas = findViewById(R.id.et_pulgadas);
        et_libras = findViewById(R.id.et_libras);

        et_metros.setOnClickListener(this);
        et_kg.setOnClickListener(this);
        btn_calcular.setOnClickListener(this);
        btn_fin.setOnClickListener(this);
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        sis_metrico.setOnClickListener(this);
        btn_calcular2.setOnClickListener(this);
        et_pulgadas.setOnClickListener(this);
        et_libras.setOnClickListener(this);
        btn_fin2.setOnClickListener(this);


        sis_metrico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll1.setVisibility(View.GONE);
                ll2.setVisibility(View.VISIBLE);
            }
        });

        btn_calcular2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valorLibrasString = et_libras.getText().toString();
                String valorPulgadasString = et_pulgadas.getText().toString();

                if (!valorLibrasString.isEmpty() && !valorPulgadasString.isEmpty()) {
                    valorLibrasString = valorLibrasString.replace(",", ".");
                    valorPulgadasString = valorPulgadasString.replace(",", ".");

                    double valorLibras = Double.parseDouble(valorLibrasString);
                    double valorPulgadas = Double.parseDouble(valorPulgadasString);

                    double calculoSisMetrico = calcularIMCmetrico(valorLibras, valorPulgadas);

                    String mensaje = null;
                    if (calculoSisMetrico <= 25) {
                        mensaje = "NORMAL";
                    } else if (calculoSisMetrico >= 25 && calculoSisMetrico <= 30) {
                        mensaje = "OBESO";
                    } else if (calculoSisMetrico > 30) {
                        mensaje = "SOBREPESO";
                    }

                    double textoFormateado = 0;
                    String textoFormateadoStr = String.format(Locale.getDefault(), "%.2f", calculoSisMetrico);
                    textoFormateado = parseDoubleLocale(textoFormateadoStr);

                    Intent intent = new Intent(Activity3.this, Activity4.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("mensaje", mensaje);
                    bundle.putDouble("calculo", textoFormateado);
                    intent.putExtras(bundle);

                    startActivity(intent);
                } else {
                    Toast.makeText(Activity3.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @SuppressLint("DefaultLocale")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calcular) {

            String valorMetrosString = et_metros.getText().toString();
            String valorKgString = et_kg.getText().toString();

            if (!valorMetrosString.isEmpty() && !valorKgString.isEmpty()) {

                // Reemplazar comas por puntos en las cadenas de entrada
                valorMetrosString = valorMetrosString.replace(",", ".");
                valorKgString = valorKgString.replace(",", ".");

                double valorMetros = Double.parseDouble(valorMetrosString);
                double valorKg = Double.parseDouble(valorKgString);

                double calculo = calcularIMC(valorMetros, valorKg);


                String mensaje = null;
                double textoFormateado = 0;

                if (esObeso(valorMetros, valorKg)) {
                    mensaje = "OBESO";

                } else if (sobrePeso(valorMetros, valorKg)) {
                    mensaje = "SOBREPESO";
                } else if (normal(valorMetros, valorKg)) {
                    mensaje = "NORMAL";
                }

                // Formatear el texto utilizando Locale.US para asegurar el uso del punto como separador decimal
                String textoFormateadoStr = String.format(Locale.getDefault(), "%.2f", calculo);
                textoFormateado = parseDoubleLocale(textoFormateadoStr);


                Intent intent = new Intent(Activity3.this, Activity4.class);
                Bundle bundle = new Bundle();
                bundle.putString("mensaje", mensaje);
                bundle.putDouble("calculo", textoFormateado);
                intent.putExtras(bundle);
                startActivity(intent);

            } else {
                Toast.makeText(Activity3.this, "Faltan datos", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.btn_fin) {
            finishAffinity();
        }
    }

    private double parseDoubleLocale(String value) {
        try {
            // Crear un NumberFormat para manejar el formato de número de la configuración regional
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
            return numberFormat.parse(value).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0.0; // Manejo de error, podrías considerar lanzar una excepción o manejarlo de otra manera
        }
    }



    private double calcularIMC(double metros, double kg) {

        return kg / (metros * metros);
    }

    private double calcularIMCmetrico(double libras, double pulgadas){
        return (libras / (pulgadas*pulgadas))*703;
    }

    public boolean esObeso(double metros, double kg) {
        double imc = calcularIMC(metros, kg);
        return imc >= 30;
    }

    public boolean sobrePeso(double metros, double kg) {
        double imc = calcularIMC(metros, kg);
        return imc >= 25;
    }

    public boolean normal(double metros, double kg) {
        double imc = calcularIMC(metros, kg);
        return imc <= 25;
    }


}