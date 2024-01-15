package com.example.listados;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_spinner, btn2_spinner, btn3_spinner, btn4_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_spinner = findViewById(R.id.btn_spinner);
        btn2_spinner = findViewById(R.id.btn2_spinner);
        btn3_spinner = findViewById(R.id.btn3_spinner);
        btn4_spinner = findViewById(R.id.btn4_spinner);
        btn_spinner.setOnClickListener(this);
        btn2_spinner.setOnClickListener(this);
        btn3_spinner.setOnClickListener(this);
        btn4_spinner.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
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
        }//end swtich
        startActivity(intent);

    }
}