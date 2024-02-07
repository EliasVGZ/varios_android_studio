package listviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listados.R;

public class ListView_2_Activity extends AppCompatActivity {

    private ListView lv_planetas2;
    private String[] planetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);

        lv_planetas2 = findViewById(R.id.lv_planetas2);


        //gestion del adaptador desde el recurso, createFromResource es solo cuando tienes el array en recursos
        //TODO ojo!! DEBEMOS USAR UN LAYOUT PROPIO DEL LISTVIEW en este caso --> simple_list_item_1, NO FUNCIONA
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.planetas, android.R.layout.simple_list_item_1);

        // Aquí deberías inicializar el array 'planetas'
        planetas = getResources().getStringArray(R.array.planetas);

        //TODO LA OTRA FORMA DE NAVEGAR POR EL ARRAY EN RECURSO, FUNCIONA!!!!!!!!!!
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                planetas);

        lv_planetas2.setAdapter(adaptador2);

        //listener del listview
        lv_planetas2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String planetaSeleccionado = adapterView.getItemAtPosition(i).toString();//DEBEMOS PONER LA i EN POSITION
                Toast.makeText(ListView_2_Activity.this, "Has elegido: "+planetaSeleccionado, Toast.LENGTH_SHORT).show();
            }
        });



    }
}