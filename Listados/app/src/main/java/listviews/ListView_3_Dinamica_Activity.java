package listviews;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listados.R;

import java.util.ArrayList;

public class ListView_3_Dinamica_Activity extends AppCompatActivity implements View.OnClickListener{

    private ListView lv_planetas3;
    private EditText et_planetas3;
    private Button btn_aceptar;
    private ArrayList<String> arrayListPlanetas;
    private ArrayAdapter<String> adaptadorPlanetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view3_dinamica);

        lv_planetas3 = findViewById(R.id.lv_planetas3);
        et_planetas3 = findViewById(R.id.et_planetas3);
        btn_aceptar = findViewById(R.id.btn_aceptar);

        et_planetas3.setOnClickListener(this);
        btn_aceptar.setOnClickListener(this);

        arrayListPlanetas = new ArrayList<>();
        adaptadorPlanetas = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayListPlanetas);
        lv_planetas3.setAdapter(adaptadorPlanetas);

        lv_planetas3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {//TODO EN LISTVIEW SE DEBE USAR GETITEMATPOSITION PARA ESCUCHAR
                String elementoSeleccionado = adapterView.getItemAtPosition(i).toString();//DEBEMOS PONER LA i EN POSITION

                Toast.makeText(ListView_3_Dinamica_Activity.this, "Has elegido "+elementoSeleccionado, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_aceptar:
                String seleccion = et_planetas3.getText().toString();
                if(!seleccion.isEmpty()){
                    arrayListPlanetas.add(seleccion);
                    et_planetas3.setText("");
                    adaptadorPlanetas.notifyDataSetChanged();
                }else{
                    Toast.makeText(this, "No puede estar vacio", Toast.LENGTH_SHORT).show();
                }
                break;
        }


    }
}