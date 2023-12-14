package com.example.a2023_eliasvierafernandez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Activity4 extends AppCompatActivity {

    private EditText et_peso;
    private TextView tv_imc;
    private ImageButton ib_normal, ib_sobrespo, ib_obesidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        et_peso = findViewById(R.id.et_peso);
        tv_imc = findViewById(R.id.tv_imc);
        ib_normal = findViewById(R.id.ib_normal);
        ib_sobrespo = findViewById(R.id.ib_sobrespo);
        ib_obesidad = findViewById(R.id.ib_obesidad);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String mensajeRecibido = bundle.getString("mensaje");
        double calculoRecibido = bundle.getDouble("calculo");


        if (calculoRecibido >= 18.5 && calculoRecibido <= 24.9) {
            et_peso.setText(mensajeRecibido);
            tv_imc.setText("IMC = " + calculoRecibido);
            ib_normal.setVisibility(View.VISIBLE);

        } else if (calculoRecibido >= 25 && calculoRecibido <= 29.99) {
            et_peso.setText(mensajeRecibido);
            tv_imc.setText("IMC = " + calculoRecibido);
            ib_sobrespo.setVisibility(View.VISIBLE);


        } else if (calculoRecibido > 30) {
            et_peso.setText(mensajeRecibido);
            tv_imc.setText("IMC = " + calculoRecibido);
            ib_obesidad.setVisibility(View.VISIBLE);

        }


    }
}