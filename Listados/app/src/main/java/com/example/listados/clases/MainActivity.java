package com.example.listados.clases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.listados.R;

import listviews.ListView_1_Activity;
import listviews.ListView_2_Activity;
import listviews.ListView_3_Dinamica_Activity;
import listviews.ListView_4_Personalizada1;
import listviews.ListView_5_Personalizada_DiferentesImagenes;
import listviews.ListView_6_Personalizada2_DiferentesImagenes_TypedArray;
import listviews.ListView_7_Personalizado3_ArrayList;
import spinners.Spinner_1_Activity;
import spinners.Spinner_2_Activity;
import spinners.Spinner_3_ActivityDesdeRecurso;
import spinners.Spinner_4_DinamicoFigurado;
import spinners.Spinner_5_DinamicoAcitivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_spinner, btn2_spinner, btn3_spinner, btn4_spinner, btn5_spinner, btn1_listview, btn2_listview,
            btn3_listview,btn4_listview, btn5_listview, btn6_listview, btn7_listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_spinner = findViewById(R.id.btn_spinner);
        btn2_spinner = findViewById(R.id.btn2_spinner);
        btn3_spinner = findViewById(R.id.btn3_spinner);
        btn4_spinner = findViewById(R.id.btn4_spinner);
        btn5_spinner = findViewById(R.id.btn5_spinner);
        btn1_listview = findViewById(R.id.btn1_listview);
        btn2_listview = findViewById(R.id.btn2_listview);
        btn3_listview = findViewById(R.id.btn3_listview);
        btn4_listview = findViewById(R.id.btn4_listview);
        btn5_listview = findViewById(R.id.btn5_listview);
        btn6_listview = findViewById(R.id.btn6_listview);
        btn7_listview = findViewById(R.id.btn7_listview);

        btn_spinner.setOnClickListener(this);
        btn2_spinner.setOnClickListener(this);
        btn3_spinner.setOnClickListener(this);
        btn4_spinner.setOnClickListener(this);
        btn5_spinner.setOnClickListener(this);
        btn1_listview.setOnClickListener(this);
        btn2_listview.setOnClickListener(this);
        btn3_listview.setOnClickListener(this);
        btn4_listview.setOnClickListener(this);
        btn5_listview.setOnClickListener(this);
        btn6_listview.setOnClickListener(this);
        btn7_listview.setOnClickListener(this);


    }


    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_spinner:
                intent.setClass(this, Spinner_1_Activity.class);
                break;
            case R.id.btn2_spinner:
                intent.setClass(this, Spinner_2_Activity.class);
                break;
            case R.id.btn3_spinner:
                intent.setClass(this, Spinner_3_ActivityDesdeRecurso.class);
                break;
            case R.id.btn4_spinner:
                intent.setClass(this, Spinner_4_DinamicoFigurado.class);
                break;
            case R.id.btn5_spinner:
                intent.setClass(this, Spinner_5_DinamicoAcitivity.class);
                break;
            //todo gestion de listas

            case R.id.btn1_listview:
                intent.setClass(this, ListView_1_Activity.class);
                break;
            case R.id.btn2_listview:
                intent.setClass(this, ListView_2_Activity.class);
                break;
            case R.id.btn3_listview:
                intent.setClass(this, ListView_3_Dinamica_Activity.class);
                break;
            case R.id.btn4_listview:
                intent.setClass(this, ListView_4_Personalizada1.class);
                break;
            case R.id.btn5_listview:
                intent.setClass(this, ListView_5_Personalizada_DiferentesImagenes.class);
                break;
            case R.id.btn6_listview:
                intent.setClass(this, ListView_6_Personalizada2_DiferentesImagenes_TypedArray.class);
                break;
            case R.id.btn7_listview:
                intent.setClass(this, ListView_7_Personalizado3_ArrayList.class);
                break;

        }//end swtich
        startActivity(intent);

    }
}