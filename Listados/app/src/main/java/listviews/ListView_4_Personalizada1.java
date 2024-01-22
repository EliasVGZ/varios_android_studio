package listviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listados.R;

public class ListView_4_Personalizada1 extends AppCompatActivity {

    private ListView lv_planetas4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view4_personalizada1);


        lv_planetas4 = findViewById(R.id.lv_planetas4);

        //TODO CREACION DE MI PROPIO ADAPTADOR, es adaptador del sistema pero algo personalizad, se rellena con layout personalizado que se crea en layout
        //THIS, LAYOUT PERSONALIZADO, ID DEL TEXTVIEW y el array de recurso
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.fila_simple,  R.id.tv_planeta, getResources().getStringArray(R.array.planetas));

        lv_planetas4.setAdapter(adapter);

        lv_planetas4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String planetaSeleccionado = adapterView.getItemAtPosition(i).toString();//DEBEMOS PONER LA i EN POSITION
                Toast.makeText(ListView_4_Personalizada1.this, "Has elegido: "+planetaSeleccionado, Toast.LENGTH_SHORT).show();
            }
        });





    }
}