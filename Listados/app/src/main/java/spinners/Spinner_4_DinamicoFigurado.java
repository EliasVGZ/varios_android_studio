package spinners;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.listados.R;

import java.util.ArrayList;

public class Spinner_4_DinamicoFigurado extends AppCompatActivity {


    private ArrayList<String>  arrayListPlanetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner4);

        Spinner spPlanetas4 = findViewById(R.id.spPlanetas4);

        //Simulamos la carga dinamica del string
        arrayListPlanetas = new ArrayList<>();
        arrayListPlanetas.add("Mercurio");
        arrayListPlanetas.add("Venus");
        arrayListPlanetas.add("Marte");
        arrayListPlanetas.add("Tierra");
        arrayListPlanetas.add("Jupiter");
        //..y asi con los demas

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayListPlanetas);

        spPlanetas4.setAdapter(adaptador);

        spPlanetas4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elementoSeleccionado = spPlanetas4.getSelectedItem().toString();//CASTEAMOS A STRING
                //PODEMOS ACCEDER AL ELEMENTO SELECCIONADO DESDE EL ADAPTADOR
                String elementoSeleccionado2 = parent.getItemAtPosition(position).toString(); //ELIGE EL  ITEM SELECCIONADO
                Toast.makeText(Spinner_4_DinamicoFigurado.this, "Has elegido "+elementoSeleccionado+ "\n"+elementoSeleccionado2, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //NO TIENE INTERES PARA NOSOTRS

            }
        });








    }
}