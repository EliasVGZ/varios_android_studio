package com.example.menus_contextuales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


//Todo, va a heredar de Auxiliar_Acitivyt, para tener menu en otra activity
public class MainActivity extends Auxiliar_Activity implements View.OnClickListener {


    private TextView tv_etiqueta;
    private ImageView iv_imagen;
    private Button btn_activity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_etiqueta = findViewById(R.id.tv_etiqueta);
        iv_imagen = findViewById(R.id.iv_imagen);
        btn_activity2 = findViewById(R.id.btn_activity2);



        //Asociar el menu contextual a las vistas
        registerForContextMenu(tv_etiqueta);
        registerForContextMenu(iv_imagen);

        btn_activity2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_activity2){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }

    }

    //Inflar el menu contextual

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v, //Recibe la vista pulsada
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        if(v.getId() == R.id.tv_etiqueta){
            inflater.inflate(R.menu.menu_contextual1, menu);//Menu asociado a la etiqueta

        }else if(v.getId() == R.id.iv_imagen){
            inflater.inflate(R.menu.menu_contextual_imagen, menu);//Menu asociada a la imágen
        }
    }

    //Listener para el menu contextual
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.opc_ctx_item1:
                Toast.makeText(this, "Has elegido opcion contextual 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_ctx_item2:
                Toast.makeText(this, "Has elegido opcion contextual 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_ctx_item3:
                Toast.makeText(this, "Has elegido opcion contextual 3", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_img_item1:
                Toast.makeText(this, "OPCION A", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_img_item2:
                Toast.makeText(this, "OPCION B", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_img_item3:
                Toast.makeText(this, "OPCION C", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);

    }



}