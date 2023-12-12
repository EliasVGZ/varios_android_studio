package com.example.a2023_eliasvierafernandez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity implements View.OnClickListener{

    private EditText et_metros, et_kg, et_pulgadas, et_libras;
    private Button btn_calcular, btn_fin, sis_metrico;
    private String DatoAEnviar;
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

        et_metros.setOnClickListener(this);
        et_kg.setOnClickListener(this);
        btn_calcular.setOnClickListener(this);
        btn_fin.setOnClickListener(this);
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        sis_metrico.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_calcular){

            String valorMetrosString = et_metros.getText().toString();
            String valorKgString = et_kg.getText().toString();

            if (!valorMetrosString.isEmpty() && !valorKgString.isEmpty()) {
                double valorMetros = Double.parseDouble(valorMetrosString);
                double valorKg = Double.parseDouble(valorKgString);

                Double calculo = calcularIMC(valorMetros, valorKg);

                String mensaje;

                if(esObeso(valorMetros, valorKg)){
                    mensaje = "IMC = "+calculo+ " OBESO";


                }else if(sobrePeso(valorMetros, valorKg)){
                    mensaje = "IMC = "+calculo+ " SOBREPESO";
                }else {
                    mensaje = "IMC = "+calculo+ " NORMAL";
                }

                Intent intent = new Intent(Activity3.this, Activity4.class);
                DatoAEnviar = mensaje;
                Bundle bundle = new Bundle();
                bundle.putString("mensaje", DatoAEnviar);
                intent.putExtras(bundle);
                startActivity(intent);

            } else {
                Toast.makeText(Activity3.this, "Faltan datos", Toast.LENGTH_SHORT).show();
            }
        }else if(v.getId() == R.id.btn_fin){
            finishAffinity();


        }else if(v.getId() == R.id.sis_metrico){
            ll1.setVisibility(View.GONE);
            ll2.setVisibility(View.VISIBLE);
        }
    }

    private double calcularIMC(double metros, double kg) {

        double cal = kg / (metros*metros);
        cal = Double.parseDouble(String.format("%.2f", cal));
        return cal;
    }

    public boolean esObeso(double metros, double kg) {
        double imc  = calcularIMC(metros, kg);
        return imc >= 30;
    }
    public boolean sobrePeso(double metros, double kg) {
        double imc  = calcularIMC(metros, kg);
        return imc >= 25;
    }
    public boolean normal(double metros, double kg) {
        double imc  = calcularIMC(metros, kg);
        return imc <= 25;
    }


}