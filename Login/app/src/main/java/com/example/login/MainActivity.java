package com.example.login;

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


    private EditText et_usuario, et_contrasena;
    private Button btn_iniciar;
    private String datoEnviar;
    private static final int CODIGO_LLAMADA_ACT2 = 1;
    private TextView tv_respuestaAct2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_usuario = findViewById(R.id.et_usuario);
        et_contrasena = findViewById(R.id.et_contrasena);
        btn_iniciar = findViewById(R.id.btn_iniciar);
        tv_respuestaAct2 = findViewById(R.id.tv_RespuestaAct2);

        et_usuario.setOnClickListener(this);
        et_contrasena.setOnClickListener(this);
        btn_iniciar.setOnClickListener(this);
        tv_respuestaAct2.setOnClickListener(this);

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_iniciar){
                    String valorUsuario = et_usuario.getText().toString();
                    String valorContrasena = et_contrasena.getText().toString();

                    Intent intent = new Intent(MainActivity.this, Activity2.class);
                    datoEnviar = "Usuario: "+valorUsuario+ " Contraseña: "+valorContrasena;

                    Bundle bundle = new Bundle();
                    bundle.putString("mensaje", datoEnviar);

                    intent.putExtras(bundle);
                    startActivityForResult(intent, CODIGO_LLAMADA_ACT2);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODIGO_LLAMADA_ACT2){
            if(resultCode == RESULT_OK){
                tv_respuestaAct2.setVisibility(View.VISIBLE);
                tv_respuestaAct2.setText(data.getStringExtra("respuesta"));
                Toast.makeText(this, "Mensaje recibido", Toast.LENGTH_SHORT).show();
            }else{
                //operaciones si la actividad llamada NO finalizo segun lo previsto
                Toast.makeText(this, "Mensaje no recibido", Toast.LENGTH_SHORT).show();
            }
        }
    }
}