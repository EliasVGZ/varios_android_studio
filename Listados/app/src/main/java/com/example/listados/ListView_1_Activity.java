package com.example.listados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListView_1_Activity extends AppCompatActivity {

    private ListView lv_planetas1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view1);

        lv_planetas1 = findViewById(R.id.lv_planetas1);


        //escuchador de la listview
        lv_planetas1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Puedes obtener el elemento seleccionado usando getItemAtPosition(i)
                String elementoSeleccionado = adapterView.getItemAtPosition(i).toString();


                Toast.makeText(ListView_1_Activity.this, "Has elegido "+elementoSeleccionado+ "\n", Toast.LENGTH_SHORT).show();
            }
        });

    }
}