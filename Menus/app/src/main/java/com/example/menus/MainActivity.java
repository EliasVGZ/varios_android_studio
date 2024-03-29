package com.example.menus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private TextView tv_hello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_hello = findViewById(R.id.tv_hello);

        //Asociar el menu contextual a la TextView
        registerForContextMenu(tv_hello);

    }

    //Inflar el menu contextual

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
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
        }
        return super.onContextItemSelected(item);

    }





    //TODO --------------------------------------------------------------------------------------------------todo//

    //Inflar mi Menu de opciones
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