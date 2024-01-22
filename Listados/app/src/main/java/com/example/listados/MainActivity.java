package com.example.listados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import listviews.ListView_1_Activity;
import listviews.ListView_2_Activity;
import listviews.ListView_3_Dinamica_Activity;
import listviews.ListView_4_Personalizada1;
import listviews.ListView_5_combox;
import spinners.Spinner_1_Activity;
import spinners.Spinner_2_Activity;
import spinners.Spinner_3_ActivityDesdeRecurso;
import spinners.Spinner_4_DinamicoFigurado;
import spinners.Spinner_5_DinamicoAcitivity;
import spinners.Spinner_6_combox;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_spinner, btn2_spinner, btn3_spinner, btn4_spinner, btn5_spinner, btn1_listview, btn2_listview, btn3_listview,btn4_listview;
    private Spinner sp_eleccion;

    private String seleccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btn_spinner = findViewById(R.id.btn_spinner);
//        btn2_spinner = findViewById(R.id.btn2_spinner);
//        btn3_spinner = findViewById(R.id.btn3_spinner);
//        btn4_spinner = findViewById(R.id.btn4_spinner);
//        btn5_spinner = findViewById(R.id.btn5_spinner);
//        btn1_listview = findViewById(R.id.btn1_listview);
//        btn2_listview = findViewById(R.id.btn2_listview);
//        btn3_listview = findViewById(R.id.btn3_listview);
//        btn4_listview = findViewById(R.id.btn4_listview);

        /*btn_spinner.setOnClickListener(this);
        btn2_spinner.setOnClickListener(this);
        btn3_spinner.setOnClickListener(this);
        btn4_spinner.setOnClickListener(this);
        btn5_spinner.setOnClickListener(this);
        btn1_listview.setOnClickListener(this);
        btn2_listview.setOnClickListener(this);
        btn3_listview.setOnClickListener(this);
        btn4_listview.setOnClickListener(this);*/

        sp_eleccion = findViewById(R.id.sp_eleccion);

        String[] opciones = {" ","Spinner", "ListView"};

        //utilizar un adaptador `para coger datos del array.
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);

        //ASGINAR ADAPTADOR AL SPINNER
        sp_eleccion.setAdapter(adaptador);

        sp_eleccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seleccion = sp_eleccion.getSelectedItem().toString();//CASTEAMOS A STRING
                Intent intent = new Intent();

                switch(sp_eleccion.getSelectedItemPosition()) {
                    case 0:
                        break;
                    case 1:
                        intent.setClass(MainActivity.this, Spinner_6_combox.class);
                        break;
                    case 2:
                        intent.setClass(MainActivity.this, ListView_5_combox.class);

                }

                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });







    }


    public void onClick(View v) {
        Intent intent = new Intent();
//        switch (v.getId()){
//            case R.id.btn_spinner:
//                intent.setClass(this, Spinner_1_Activity.class);
//                break;
//            case R.id.btn2_spinner:
//                intent.setClass(this, Spinner_2_Activity.class);
//                break;
//            case R.id.btn3_spinner:
//                intent.setClass(this, Spinner_3_ActivityDesdeRecurso.class);
//                break;
//            case R.id.btn4_spinner:
//                intent.setClass(this, Spinner_4_DinamicoFigurado.class);
//                break;
//            case R.id.btn5_spinner:
//                intent.setClass(this, Spinner_5_DinamicoAcitivity.class);
//                break;
//            //gestion de listas
//            case R.id.btn1_listview:
//                intent.setClass(this, ListView_1_Activity.class);
//                break;
//            case R.id.btn2_listview:
//                intent.setClass(this, ListView_2_Activity.class);
//                break;
//            case R.id.btn3_listview:
//                intent.setClass(this, ListView_3_Dinamica_Activity.class);
//                break;
//
//
//        }//end swtich
        //startActivity(intent);

    }
}