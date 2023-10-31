package com.example.saludopersonalizado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout ll1;
    private Button btnSaludar;
    private EditText et_nombre, et_nacimiento;
    private TextView tvSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll1 = findViewById(R.id.ll1);
        btnSaludar = findViewById(R.id.btnSaludar);
        et_nombre = findViewById(R.id.et_nombre);
        et_nacimiento = findViewById(R.id.et_nacimiento);
        tvSaludo = findViewById(R.id.tvSaludo);

        ll1.setOnClickListener(this);
        btnSaludar.setOnClickListener(this);
        et_nombre.setOnClickListener(this);
        et_nacimiento.setOnClickListener(this);
        tvSaludo.setOnClickListener(this);


    }


    public void onClick(View v) {
        if(v.getId() == R.id.btnSaludar){
            String mensajeEdad;

            String valorNombre = et_nombre.getText().toString();
            String valorNacimientoString = et_nacimiento.getText().toString();

            if (!valorNombre.isEmpty() && !valorNacimientoString.isEmpty()){
                int valorNacimiento = Integer.parseInt(valorNacimientoString);

                Calendar fechaNacimiento = Calendar.getInstance();
                fechaNacimiento.set(valorNacimiento, 0, 1); // Asumiendo que el mes es en base 0 (enero es 0)

                Calendar fechaActual = Calendar.getInstance();

                int edad = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);

                if(edad > 18){
                    mensajeEdad = "Eres mayor de edad";
                }else{
                    mensajeEdad = "eres menor edad";
                }
                String mensajeSaludo = "Hola: "+valorNombre+ "\n"+mensajeEdad;
                tvSaludo.setText(mensajeSaludo);

            } else {
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /* NO DEJA CALCULAR LA EDAD CON LOCALDATE
    public void onClick(View v) {
        if(v.getId() == R.id.btnSaludar){
            String valorNombre = et_nombre.getText().toString();
            String valorNacimientoString = et_nacimiento.getText().toString();

            if (!valorNombre.isEmpty() && !valorNacimientoString.isEmpty()){
                int valorNacimiento = Integer.parseInt(valorNacimientoString);

                LocalDate fechaNacimiento = LocalDate.of(valorNacimiento, 1, 1);
                LocalDate fechaActual = LocalDate.now();

                Period period = Period.between(fechaNacimiento, fechaActual);
                int edad = period.getYears();

                String mensajeEdad;
                if (edad >= 18) {
                    mensajeEdad = "Eres mayor de edad";
                } else {
                    mensajeEdad = "Eres menor de edad";
                }

                String mensajeSaludo = "Hola " + valorNombre + "\n" + mensajeEdad;
                Toast.makeText(this, mensajeSaludo, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show();
            }
        }
    }*/


}