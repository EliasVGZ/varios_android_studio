package com.example.listados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListView_2_Activity extends AppCompatActivity {

    private ListView lv_planetas2;
    private String[] planetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);

        lv_planetas2 = findViewById(R.id.lv_planetas2);


        //gestion del adaptador desde el recurso, createFromResource es solo cuando tienes el array en recursos
        //TODO ojo!! DEBEMOS USAR UN LAYOUT PROPIO DEL LISTVIEW en este caso --> simple_list_item_1
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.planetas, android.R.layout.simple_list_item_1);

        //TODO LA OTRA FORMA DE NAVEGAR POR EL ARRAY EN RECURSO
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                planetas);

        lv_planetas2.setAdapter(adaptador);

        //listener del listview
        lv_planetas2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String planetaSeleccionado = adapterView.getItemAtPosition(i).toString();//DEBEMOS PONER LA i EN POSITION
                Toast.makeText(ListView_2_Activity.this, "Has elegido: "+planetaSeleccionado, Toast.LENGTH_SHORT).show();
            }
        });



    }
}