package com.example.components_basicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.components_basicos.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private ToggleButton toggleButton;
    private Switch switchWifi;
    private CheckBox checkBoxAcepto;
    private EditText et_AlfaNumerico, et_numero, et_decimal;
    private Button btn_normal;
    private RadioGroup rg_grupo;
    private RadioButton rbnSi, rbnNo, rbnAveces;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO DE MOMENTO TRABAJO CON EL ACTIVITY MAIN setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_main);

        //METODO PARA INICIALIZAR VARIABLES
        sentenciasFind();
        //METODOS PARA ESCUCHADORES
        sentenciasListeners();
    }

    private void sentenciasListeners() {
        toggleButton.setOnClickListener(this);
        switchWifi.setOnClickListener(this);
        checkBoxAcepto.setOnClickListener(this);

        et_AlfaNumerico.setOnClickListener(this);
        et_numero.setOnClickListener(this);
        et_decimal.setOnClickListener(this);

        btn_normal.setOnClickListener(this);

        rbnSi.setOnClickListener(this);
        rbnNo.setOnClickListener(this);
        rbnAveces.setOnClickListener(this);



        //Escuchador para CHECKBOX
        checkBoxAcepto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(MainActivity.this, "Cambio de estado: Acepto", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Cambio de estado: No Acepto", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //escuchador para el radio group
        rg_grupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbnsi){
                    Toast.makeText(MainActivity.this, "Cambio de estado: ELEGIDO SI", Toast.LENGTH_SHORT).show();
                }else if(checkedId == R.id.rbno){
                    Toast.makeText(MainActivity.this, "Cambio de estado: ELEGIDO NO", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Cambio de estado: ELEGIDO A VECES", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void sentenciasFind() {
        toggleButton= findViewById(R.id.toggle1);
        switchWifi = findViewById(R.id.swWifi);
        checkBoxAcepto = findViewById(R.id.chk_acepto);

        et_AlfaNumerico = findViewById(R.id.et_text_normal);
        et_numero = findViewById(R.id.et_numero);
        et_decimal = findViewById(R.id.et_decimal);

        btn_normal = findViewById(R.id.btn_estandar);

        rg_grupo = findViewById(R.id.rg_grupo);
        rbnSi = findViewById(R.id.rbnsi);
        rbnNo =findViewById(R.id.rbno);
        rbnAveces = findViewById(R.id.rbaveces);
    }

    //PROBANDO LAS TOAST
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.toggle1){
            if(toggleButton.isChecked()){
                //Toast + control + espacio
                Toast.makeText(this, "toggle ACTIVADO", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "toggle DESACTIVADO", Toast.LENGTH_SHORT).show();

            }//end toggle
        }else if(v.getId() == R.id.swWifi){
            if(switchWifi.isChecked()){
                Toast.makeText(this, "Wifi activado", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "WIFI DESACTIVADO", Toast.LENGTH_LONG).show();
            }

            //fin del switch
        }else if(v.getId() == R.id.chk_acepto){
            if(checkBoxAcepto.isChecked()){
                Toast.makeText(this, "checbox ACEPTADO", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "checkbox NO ACEPTADO", Toast.LENGTH_SHORT).show();
            }

            // fin checkbox
        }else if(v.getId() == R.id.btn_estandar){
            //capturar entrada de cada EditText y construir un mensaje
            String mensaje;
            mensaje = et_decimal.getText().toString() +
                    "\n" +et_numero.getText().toString()+
                    "\n"+ et_AlfaNumerico.getText().toString();
            //Visualizar el mensaje con el Toast
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();

        }else if(v.getId() ==R.id.rbnsi){
            Toast.makeText(this, "onclick boton si", Toast.LENGTH_SHORT).show();
        }else if(v.getId() == R.id.rbno){
            Toast.makeText(this, "onclik boton no", Toast.LENGTH_SHORT).show();
        }else if(v.getId() == R.id.rbaveces){
            Toast.makeText(this, "onclick boton a veces", Toast.LENGTH_SHORT).show();
        }
    }


}