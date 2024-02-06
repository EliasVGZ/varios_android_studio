package listviews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listados.R;
import com.example.listados.adaptadores.AdaptadorPersonalizado_3_ArrayList;
import com.example.listados.adaptadores.AdaptadorPersonalizado_4_Optimizado;
import com.example.listados.clases.Planetas;

import java.util.ArrayList;

public class ListView_8_Optimizada extends AppCompatActivity {

    private ListView lv_planetas8;
    private ArrayList<Planetas> listadoPlanetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view8_optimizada);


        lv_planetas8 = findViewById(R.id.lv_planetas8);
        listadoPlanetas = new ArrayList<>();

        registerForContextMenu(lv_planetas8);


        int longitud = getResources().getStringArray(R.array.planetas).length;

        for (int i = 0; i < longitud; i++) {
            listadoPlanetas.add(new Planetas(
                    getResources().getStringArray(R.array.planetas)[i],
                    getResources().obtainTypedArray(R.array.fotos_planetas).getResourceId(i, -1)));

        }
        //Crear instancia del adaptador personalizado
        AdaptadorPersonalizado_4_Optimizado adaptador = new AdaptadorPersonalizado_4_Optimizado(
                this,
                R.layout.fila_diferentes_imagenes_y_texto,
                listadoPlanetas

        );
        lv_planetas8.setAdapter(adaptador);

        lv_planetas8.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nombrePlaneta = listadoPlanetas.get(i).getNombre();
                Toast.makeText(ListView_8_Optimizada.this, "Planeta: " + nombrePlaneta, Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Inflar el menu contextual

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v, //Recibe la vista pulsada
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;//Castearlo a un ADAPTER

        //String elemento=lv_planetas8.getAdapter().getItem(info.position).toString();//Conssigue nombre del planeta
        int posicion = info.position;
        Planetas planeta = (Planetas) lv_planetas8.getAdapter().getItem(posicion);
        menu.setHeaderTitle(planeta.getNombre());//Escribe el titular con el string que contiene el nombre del planeta

        inflater.inflate(R.menu.menu_contextual1, menu);
    }

    //Listener para el menu contextual
    @SuppressLint("NonConstantResourceId")
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
        }
        return super.onContextItemSelected(item);

    }

}