package com.example.repaso;

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
        inflater.inflate(R.menu.menu_alumnos, menu);


        return super.onCreateOptionsMenu(menu);
    }


    //Listener para menu de opciones!!!

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.opc_eliminar:
                Toast.makeText(this, "Has elegido ELIMINAR", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_modificar:
                Toast.makeText(this, "Has elegido Modificar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opc_notificacion:
                Toast.makeText(this, "Has elegido notificacion", Toast.LENGTH_SHORT).show();


        }

        return super.onOptionsItemSelected(item);
    }

}