package com.example.intent_filter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_implicito, btn_explicito;
    private EditText et_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_implicito = findViewById(R.id.btn_implicito);
        btn_explicito = findViewById(R.id.btn_explicito);
        et_web = findViewById(R.id.et_web);

        btn_explicito.setOnClickListener(this);
        btn_implicito.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_explicito){//LLAMADA A UNA WEB EXPLICITAMENTE
            String url = et_web.getText().toString();
            if(url.isEmpty()){
                Toast.makeText(this, "Introduce URL", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(this, ExplicitActivity.class);
                intent.putExtra("url_key", url);
                startActivity(intent);
            }
        }

        else if (v.getId() == R.id.btn_implicito){//LLAMADA A UNA WEB IMPLICITAMENTE
            String url = et_web.getText().toString();
            if(url.isEmpty()){
                Toast.makeText(this, "Introduce URL ", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                //verificar si hay algun explorado para abrir
                if(intent.resolveActivity(getPackageManager() )!= null){
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "ESTA ACCION NO SE PUEDE REALIZAR!!", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}