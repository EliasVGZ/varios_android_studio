package listviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listados.R;
import com.example.listados.adaptadores.AdaptadorPersonalizado_1;

public class ListView_5_Personalizada_DiferentesImagenes extends AppCompatActivity {


    private ListView lv_planetas5;
    private int[] arrayIdFotoPlanetas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view5_personalizada_diferentes_imagenes);

        lv_planetas5 = findViewById(R.id.lv_planetas5);

        //Auxiliar para crear un array de entero con las imágenes
        arrayIdFotoPlanetas = new int[]{R.drawable.jupiter,
                R.drawable.marte, R.drawable.tierra,
                R.drawable.mercurio,
                R.drawable.neptuno, R.drawable.saturno,
         R.drawable.urano, R.drawable.venus};


        //Crear instancia del adaptador personalizado
        AdaptadorPersonalizado_1 adaptador = new AdaptadorPersonalizado_1(
                this,
                R.layout.fila_diferentes_imagenes_y_texto,
                getResources().getStringArray(R.array.planetas),
                arrayIdFotoPlanetas
        );

        lv_planetas5.setAdapter(adaptador);

        lv_planetas5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String planetaSeleccionado = adapterView.getItemAtPosition(i).toString();//DEBEMOS PONER LA i EN POSITION
                Toast.makeText(ListView_5_Personalizada_DiferentesImagenes.this, "Planeta: "+planetaSeleccionado, Toast.LENGTH_SHORT).show();
            }
        });




    }
}