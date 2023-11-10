package com.example.saludopersonalizado_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button btnSaludar;
    private String DatoAEnviar;
    private EditText et_nombre, et_nacimiento;
    private RadioButton rbnSra, rbnSr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSaludar = findViewById(R.id.btnSaludar);
        et_nombre = findViewById(R.id.et_nombre);
        et_nacimiento = findViewById(R.id.et_nacimiento);
        rbnSra = findViewById(R.id.rbnSra);
        rbnSr = findViewById(R.id.rbnSr);

        btnSaludar.setOnClickListener(this);
        et_nombre.setOnClickListener(this);
        et_nacimiento.setOnClickListener(this);
        rbnSra.setOnClickListener(this);
        rbnSr.setOnClickListener(this);

        btnSaludar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnSaludar) {

                    // Obtener valores de los campos de texto
                    String valorNombre = et_nombre.getText().toString();
                    String valorNacimientoString = et_nacimiento.getText().toString();

                    if (!valorNombre.isEmpty() && !valorNacimientoString.isEmpty()) {
                        int valorNacimiento = Integer.parseInt(valorNacimientoString);

                        String mensajeEdad;
                        if (esMayorDeEdad(valorNacimiento)) {
                            mensajeEdad = "Eres mayor de edad";
                        } else {
                            mensajeEdad = "Eres menor de edad";
                        }

                        String saludo = "";
                        if (rbnSra.isChecked()) {
                            saludo = "Sra.";
                        } else if (rbnSr.isChecked()) {
                            saludo += "Sr.";
                        }

                        Intent intent = new Intent(MainActivity.this, Activity2.class);
                        //DATO A ENVIAR
                        DatoAEnviar = "Hola, " + saludo + " " + valorNombre + "\n" + mensajeEdad + "\n ";
                        //INSTANCIA DE OBJETO BUNDLE
                        Bundle bundle = new Bundle();
                        //LE PASAMOS AL BUNDLE UNA KEY Y EL MENSAJE
                        bundle.putString("mensaje", DatoAEnviar);
                        //METEMOS AL INTENT EL PAQUETE BUNDLE, EL EXTRAS SERA EN PLURAL
                        intent.putExtras(bundle);

                        startActivity(intent);


                    } else {
                        Toast.makeText(MainActivity.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }

    public int calcularEdad(int añoNacimiento) {
        Calendar fechaActual = Calendar.getInstance();
        int yearActual = fechaActual.get(Calendar.YEAR);
        if (añoNacimiento > yearActual) {
            Toast.makeText(this, "Año no valido", Toast.LENGTH_SHORT).show();
        }

        return yearActual - añoNacimiento;
    }

    public boolean esMayorDeEdad(int añoNacimiento) {
        int edad = calcularEdad(añoNacimiento);
        return edad >= 18;
    }


    @Override
    public void onClick(View v) {

    }
}