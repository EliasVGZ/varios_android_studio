package com.example.ejemplodiaanteriorexamen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_enviar;
    private TextView tv_respuesta_act2;
    private Button btn_enviar, btn_recibir;
    private String valor, datoEnviar;
    private static final int CODIGO_LLAMADA_ACT2 = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_enviar = findViewById(R.id.et_enviar);
        btn_enviar = findViewById(R.id.btn_enviar);
        btn_recibir = findViewById(R.id.btn_recibir);
        tv_respuesta_act2 = findViewById(R.id.tv_respuesta_act2);

        et_enviar.setOnClickListener(this);
        btn_enviar.setOnClickListener(this);
        btn_recibir.setOnClickListener(this);
        tv_respuesta_act2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_enviar){
            valor = et_enviar.getText().toString();

            if(!valor.isEmpty()){
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                datoEnviar = valor;
                Bundle bundle = new Bundle();
                bundle.putString("envio", datoEnviar);
                intent.putExtras(bundle);

                startActivityForResult(intent, CODIGO_LLAMADA_ACT2);

            }else{
                Toast.makeText(this, "Mensaje no puede estar vacio", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODIGO_LLAMADA_ACT2){
            if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Volviendo a menu", Toast.LENGTH_SHORT).show();

            }else if (resultCode == RESULT_OK){
                tv_respuesta_act2.setText(data.getStringExtra("devolver"));


            }
        }
    }
}