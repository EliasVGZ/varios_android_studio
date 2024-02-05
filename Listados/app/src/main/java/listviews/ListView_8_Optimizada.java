package listviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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


        //int longitud = Math.min(nombres.length, imagenes.length()); TODO VERIFICA QUE AMBOS ARRAYS SEAN IGUALES
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
}