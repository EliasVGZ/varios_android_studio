package com.example.autocompletartexto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView ac_nombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ac_nombres = findViewById(R.id.ac_nombres);

        //RECUPERAMOS LOS DATOS QUE VAN A FORMAR LA LISTA DEL AUTOCMPLETA
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.nombres));
        ac_nombres.setAdapter(adaptador);

        //ESCUCHADOR DEL AUTOCOMPLTE
        ac_nombres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccion = parent.getItemAtPosition(position).toString();
                String seleccion2 = ac_nombres.getText().toString();//SOLO SIRVE PARA NOMBRES, NO PARA OBJETOS
                Toast.makeText(MainActivity.this, "Has elegido el nombre: "+seleccion2, Toast.LENGTH_SHORT).show();
            }
        });

    }
}