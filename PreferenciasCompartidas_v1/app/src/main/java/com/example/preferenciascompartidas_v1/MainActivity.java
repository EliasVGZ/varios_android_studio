package com.example.preferenciascompartidas_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencia = PreferenceManager.getDefaultSharedPreferences(this); //Instanciamos el SharedPreferences
    }

    @SuppressLint("NonConstantResourceId")
    public void onClickBtn(View v) {
        switch (v.getId()) {
            case R.id.btn_guardar_pre:
                SharedPreferences.Editor editorPreferencia = preferencia.edit();
                editorPreferencia.putString("nombre", "Yo mismo");
                editorPreferencia.putString("email", "email_elias@gmail.com");
                editorPreferencia.apply();
                Toast.makeText(this, "Datos se han guardado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_recuperar_pre:
                String nombre = preferencia.getString("nombre", "NO HAY NOMBRE PARA AUN");
                String email = preferencia.getString("email", "NO HAY EMAIL AUN");
                Toast.makeText(this, "Nombre: "+nombre+ " \n"+"Email: "+email, Toast.LENGTH_SHORT).show();

                break;

        }
    }
}