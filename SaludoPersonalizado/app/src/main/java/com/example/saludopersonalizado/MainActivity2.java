package com.example.saludopersonalizado;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    private Button btnSaludar;
    private EditText et_nombre, et_nacimiento;
    private TextView tvSaludo;
    private RadioGroup rg_grupo;
    private RadioButton rbnSra, rbnSr;
    private CheckBox chkDespedida;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnSaludar = findViewById(R.id.btnSaludar);
        et_nombre = findViewById(R.id.et_nombre);
        et_nacimiento = findViewById(R.id.et_nacimiento);
        tvSaludo = findViewById(R.id.tvSaludo);
        rg_grupo = findViewById(R.id.rg_grupo);
        rbnSra = findViewById(R.id.rbnSra);
        rbnSr = findViewById(R.id.rbnSr);
        chkDespedida = findViewById(R.id.chkDespedida);

        btnSaludar.setOnClickListener(this);
        et_nombre.setOnClickListener(this);
        et_nacimiento.setOnClickListener(this);
        tvSaludo.setOnClickListener(this);
        rg_grupo.setOnClickListener(this);
        rbnSr.setOnClickListener(this);
        rbnSra.setOnClickListener(this);
        chkDespedida.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSaludar){

            String valorNombre = et_nombre.getText().toString();
            String valorNacimientoString = et_nacimiento.getText().toString();

            if (!valorNombre.isEmpty() && !valorNacimientoString.isEmpty()){
                int valorNacimiento = Integer.parseInt(valorNacimientoString);

                Calendar fechaNacimiento = Calendar.getInstance();
                fechaNacimiento.set(valorNacimiento, 0, 1);

                Calendar fechaActual = Calendar.getInstance();

                int edad = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);

                String mensajeEdad;

                if(edad > 18){
                    mensajeEdad = "Eres mayor de edad";
                } else {
                    mensajeEdad = "Eres menor de edad";
                }


                String saludo = "";
                if(rbnSra.isChecked()){
                    saludo = "Sra.";
                } else if(rbnSr.isChecked()){
                    saludo += "Sr.";
                }

                String mensajeSaludo = "Hola, "+saludo+" " + valorNombre + "\n" + mensajeEdad;

                if(chkDespedida.isChecked()){
                    mensajeSaludo += "\nHasta pronto";
                }

                tvSaludo.setText(mensajeSaludo);
            } else {

                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
