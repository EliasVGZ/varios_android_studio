package com.example.saludopersonalizado_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity implements View.OnClickListener{

    private Button btnFinalizar;
    private CheckBox chkDespedida;
    private LinearLayout llDespedida;
    private RadioButton rbnAdios, rbnHastaPronto;
    private String datoRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        btnFinalizar = findViewById(R.id.btnFinalizar);
        chkDespedida = findViewById(R.id.chkDespedida);
        llDespedida = findViewById(R.id.llDespedida);
        rbnAdios = findViewById(R.id.rbnAdios);
        rbnHastaPronto = findViewById(R.id.rbnHastaPronto);

        llDespedida.setOnClickListener(this);
        rbnAdios.setOnClickListener(this);
        rbnHastaPronto.setOnClickListener(this);
        chkDespedida.setOnClickListener(this);
        btnFinalizar.setOnClickListener(this);

        Intent intent = getIntent();
        //recuperamos el objeto bundle que se le ha pasado con el intent
        Bundle bundle = intent.getExtras();

        //RECUPERAR DATO CON EL METODO ADECUADO
        String mensajeRecibido = bundle.getString("mensaje");

        //EN UNA SOLA SENTENCIA
        String datoRecibido=getIntent().getExtras().getString("mensaje");

        TextView tvRecibida=findViewById(R.id.tvMensajeRecibidoAct2);
        tvRecibida.setText(mensajeRecibido);

        chkDespedida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    llDespedida.setVisibility(View.VISIBLE);
                }else{
                    llDespedida.setVisibility(View.GONE);
                }
            }
        });


    }

   @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnFinalizar){
            if(!chkDespedida.isChecked()){
                datoRespuesta = "El usuario no quiso despedida";
                Intent intentRespuesta = new Intent();
                intentRespuesta.putExtra("respuesta", datoRespuesta);
                setResult(RESULT_OK, intentRespuesta);
            }else {
                String mensajeSaludo = "";
                if (rbnAdios.isChecked()) {
                    mensajeSaludo = "Adios";
                } else if (rbnHastaPronto.isChecked()) {
                    mensajeSaludo = "Hasta pronto";
                }
                datoRespuesta = mensajeSaludo;
            }

            // Enviar la respuesta
            Intent intentRespuesta = new Intent();
            intentRespuesta.putExtra("respuesta", datoRespuesta);
            setResult(RESULT_OK, intentRespuesta);

            finish();
        }else{
            Intent intentRespuesta = new Intent();
            datoRespuesta = "El usuario no ha contestado";
            intentRespuesta.putExtra("respuesta", datoRespuesta);
            setResult(RESULT_CANCELED, intentRespuesta);
        }

    }
}