package com.example.saludopersonalizado;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private Button btnSaludar;
    private EditText et_nombre, et_nacimiento;
    private TextView tvSaludo, tvElegir;
    private RadioGroup rg_grupo, rgElegir;
    private RadioButton rbnSra, rbnSr, rbnAdios, rbnHastaPronto;
    private CheckBox chkDespedida;
    private LinearLayout llDespedida;
    String seleccionDespedida = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sentenciasFind();
        sentenciasSetear();

        chkDespedida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    llDespedida.setVisibility(View.VISIBLE);
                    if (rbnAdios.isChecked()) {
                        seleccionDespedida = rbnAdios.getText().toString();

                    } else if (rbnHastaPronto.isChecked()) {
                        seleccionDespedida = rbnHastaPronto.getText().toString();
                    }
                }else{
                    llDespedida.setVisibility(View.GONE);
                }

            }
        });


    }

    private void sentenciasSetear() {
        btnSaludar.setOnClickListener(this);
        et_nombre.setOnClickListener(this);
        et_nacimiento.setOnClickListener(this);
        tvSaludo.setOnClickListener(this);
        rg_grupo.setOnClickListener(this);
        rbnSr.setOnClickListener(this);
        rbnSra.setOnClickListener(this);

        rgElegir.setOnClickListener(this);
        chkDespedida.setOnClickListener(this);
        llDespedida.setOnClickListener(this);
        rbnAdios.setOnClickListener(this);
        rbnHastaPronto.setOnClickListener(this);
        tvElegir.setOnClickListener(this);
    }

    private void sentenciasFind() {

        btnSaludar = findViewById(R.id.btnSaludar);
        et_nombre = findViewById(R.id.et_nombre);
        et_nacimiento = findViewById(R.id.et_nacimiento);
        tvSaludo = findViewById(R.id.tvSaludo);
        rg_grupo = findViewById(R.id.rg_grupo);
        rbnSra = findViewById(R.id.rbnSra);
        rbnSr = findViewById(R.id.rbnSr);

        rgElegir = findViewById(R.id.rgElegir);
        chkDespedida = findViewById(R.id.chkDespedida);
        llDespedida = findViewById(R.id.llDespedida);
        rbnAdios = findViewById(R.id.rbnAdios);
        rbnHastaPronto = findViewById(R.id.rbnHastaPronto);
        tvElegir = findViewById(R.id.tvElegir);


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSaludar) {

            String valorNombre = et_nombre.getText().toString();
            String valorNacimientoString = et_nacimiento.getText().toString();

            if (!valorNombre.isEmpty() && !valorNacimientoString.isEmpty()) {
                int valorNacimiento = Integer.parseInt(valorNacimientoString);
                String mensajeEdad;

                if (esMayorDeEdad(valorNacimiento)) {
                    mensajeEdad = "Eres mayor de edad";
                } else {
                    mensajeEdad = "Eres mayor de edad";
                }

                String saludo = "";
                if (rbnSra.isChecked()) {
                    saludo = "Sra.";
                } else if (rbnSr.isChecked()) {
                    saludo += "Sr.";
                }

                //TODO NO FUNCIONA!!!
                /*
                if (chkDespedida.isChecked()) {
                    llDespedida.setVisibility(View.VISIBLE);
                    //CREO UN TOASTT PARA VERIFICAR SI AL CLICKEAR CHKDESPEDIDA HACE ALGO TODO -------NO FUNCIONA---------

                    Toast.makeText(this, "check despedida seleccionado", Toast.LENGTH_SHORT).show();
                    if (rbnAdios.isChecked()) {
                        seleccionDespedida = rbnAdios.getText().toString();

                    } else if (rbnHastaPronto.isChecked()) {
                        seleccionDespedida = rbnHastaPronto.getText().toString();
                    }

                } else if (!chkDespedida.isChecked()) {
                    llDespedida.setVisibility(View.GONE);
                }*/

                String mensajeSaludo = "Hola, " + saludo + " " + valorNombre + "\n" + mensajeEdad + "\n " + seleccionDespedida;
                tvSaludo.setText(mensajeSaludo);
            } else {

                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show();

            }
        }
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


}
