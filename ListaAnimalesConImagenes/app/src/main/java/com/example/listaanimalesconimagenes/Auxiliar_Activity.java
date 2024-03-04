package com.example.listaanimalesconimagenes;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Auxiliar_Activity extends AppCompatActivity {


    //todo en auxiliar se puede cargar el onCreate
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_auxiliar);
//
//    }

    //TODO -> CLASE AUXILIAR PARA TENER EL MENU EN VARIOS LAYOUTS

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);


        return super.onCreateOptionsMenu(menu);
    }


    //Listener para menu de opciones!!!

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.opc_item1:
                Toast.makeText(this, "Has elegido OPCION 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sub_item1:
                Toast.makeText(this, "Has elegido SUB Opcion 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sub_item2:
                Toast.makeText(this, "Has elegido SUB Opcion 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_item2:
                Toast.makeText(this, "Has elegido OPCION 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_item3:
                Toast.makeText(this, "Has elegido OPCION 3", Toast.LENGTH_SHORT).show();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

}