package com.example.alarma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.time_picker);
    }
    public void onClickBotones(View view){
        String mensaje = "Esto es el aviso actual de mi alarma";

        int hora = timePicker.getCurrentHour();
        int minutos = timePicker.getCurrentMinute();

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);

        if(intent.resolveActivity(getPackageManager()) != null){
            // Configurar la alarma con la hora y los minutos seleccionados
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, mensaje);
            intent.putExtra(AlarmClock.EXTRA_HOUR, hora);
            intent.putExtra(AlarmClock.EXTRA_MINUTES, minutos);

            startActivity(intent);
        }else{
            Toast.makeText(this, "No existe una aplicacion alarma", Toast.LENGTH_SHORT).show();
        }
    }
}