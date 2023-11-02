package com.example.cambiodemoneda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RadioGroup rg_moneda;
    private RadioButton rbnPeseta, rbnEuro;
    private Button btnCambiar;
    private EditText etValor;
    private TextView tvCambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sentenciasFind();
        sentenciasSetOnClick();




    }

    private void sentenciasFind() {

        rg_moneda= findViewById(R.id.rg_moneda);
        rbnPeseta = findViewById(R.id.rbnPeseta);
        rbnEuro = findViewById(R.id.rbnEuro);
        btnCambiar = findViewById(R.id.btnCambiar);
        etValor = findViewById(R.id.etValor);
        tvCambio = findViewById(R.id.tvCambio);
    }
    private void sentenciasSetOnClick() {

        rg_moneda.setOnClickListener(this);
        rbnPeseta.setOnClickListener(this);
        rbnEuro.setOnClickListener(this);
        btnCambiar.setOnClickListener(this);
        etValor.setOnClickListener(this);
        tvCambio.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCambiar){
            String valorMoneda = etValor.getText().toString();

            if(!valorMoneda.isEmpty()){
                tvCambio.setVisibility(View.VISIBLE);
                Double valorM = Double.parseDouble(valorMoneda);
                String mensajeMoneda = null;

                if(rbnPeseta.isChecked()){
                    Double euros = pesetaAeuros(valorM);
                    String textoFormateado = String.format("%.2f", euros);
                    mensajeMoneda = valorM + " Pesetas equivalen a " + textoFormateado + " euros";
                }else if(rbnEuro.isChecked()){
                    Double pesetas = eurosApestas(valorM);
                    String textoFormateado = String.format("%.2f", pesetas);
                    mensajeMoneda = valorM + " Euros equivalen a " + textoFormateado + " pesetas";
                }
                tvCambio.setText(mensajeMoneda);

            }else{
                tvCambio.setVisibility(View.GONE);
                Toast.makeText(this, "No has añadido valor", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Double pesetaAeuros(double pesetas){

        Double euros = pesetas / 166.38;
        euros = Double.parseDouble(String.format("%.2f", euros)); //MAXIMO 2 DECIMALES
        return euros;
    }

    public double eurosApestas(double euros){
        Double pesetas = euros * 166.38;
        pesetas = Double.parseDouble(String.format("%.2f", pesetas));
        return pesetas;
    }
}