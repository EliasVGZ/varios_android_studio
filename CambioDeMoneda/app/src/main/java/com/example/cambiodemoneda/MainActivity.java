package com.example.cambiodemoneda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
    private String DatoAEnviar;

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

    /**
     * COMO PASAR VARIOS BUNDLES A OTRA ACTIVITY
     *
     * // Crear el primer Bundle
     * Bundle bundle1 = new Bundle();
     * bundle1.putString("clave1", "valor1");
     * bundle1.putInt("clave2", 42);
     *
     * // Crear el segundo Bundle
     * Bundle bundle2 = new Bundle();
     * bundle2.putString("clave3", "valor3");
     * bundle2.putBoolean("clave4", true);
     *
     * // Crear el Intent y adjuntar ambos Bundles
     * Intent intent = new Intent(this, TuOtraActividad.class);
     * intent.putExtras(bundle1);
     * intent.putExtras(bundle2);
     *
     * // Iniciar la nueva actividad con el Intent que contiene ambos Bundles
     * startActivity(intent);
     *
     * Y A LA HORA DE RECIBIRLO EN EL OTRO ACTIVITY
     *
     * // En la actividad receptora
     * Bundle bundle1 = getIntent().getExtras();
     * if (bundle1 != null) {
     *     String valor1 = bundle1.getString("clave1");
     *     int valor2 = bundle1.getInt("clave2");
     *     // Hacer algo con los datos del primer Bundle
     * }
     *
     * Bundle bundle2 = getIntent().getExtras();
     * if (bundle2 != null) {
     *     String valor3 = bundle2.getString("clave3");
     *     boolean valor4 = bundle2.getBoolean("clave4");
     *     // Hacer algo con los datos del segundo Bundle
     * }
     *
     */


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCambiar){
            String valorMoneda = etValor.getText().toString();

            // Reemplazar comas por puntos
            valorMoneda = valorMoneda.replace(",", ".");

            if(!valorMoneda.isEmpty()){
                tvCambio.setVisibility(View.VISIBLE);
                Double valorM = Double.parseDouble(valorMoneda);


                if(rbnPeseta.isChecked()){
                    Double euros = pesetaAeuros(valorM);
                    @SuppressLint("DefaultLocale") String textoFormateado = String.format("%.2f", euros); //dos digitos como maxIMO
                    DatoAEnviar = valorM + " Pesetas equivalen a " + textoFormateado + " euros";

                }else if(rbnEuro.isChecked()){
                    Double pesetas = eurosApestas(valorM);
                    @SuppressLint("DefaultLocale") String textoFormateado = String.format("%.2f", pesetas);
                    DatoAEnviar = valorM + " Euros equivalen a " + textoFormateado + " pesetas";

                }


                Intent intent = new Intent(this, Activity2.class);
                //INSTANCIA DE OBJETO BUNDLE
                Bundle bundle = new Bundle();
                //LE PASAMOS AL BUNDLE UNA KEY Y EL MENSAJE
                bundle.putString("mensajeMoneda", DatoAEnviar);
                //METEMOS AL INTENT EL PAQUETE BUNDLE, EL EXTRAS SERA EN PLURAL
                intent.putExtras(bundle);

                startActivity(intent);
            }else{
                tvCambio.setVisibility(View.GONE);
                Toast.makeText(this, "No has añadido valor", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Double pesetaAeuros(double pesetas){
        return pesetas / 166.38;
    }

    public double eurosApestas(double euros){
        return euros * 166.38;
    }

}