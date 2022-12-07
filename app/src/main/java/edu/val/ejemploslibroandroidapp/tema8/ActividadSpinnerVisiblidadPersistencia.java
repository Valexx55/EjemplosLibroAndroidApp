package edu.val.ejemploslibroandroidapp.tema8;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class ActividadSpinnerVisiblidadPersistencia extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //RELLENAR EL SELECTOR SPINNER CON DATOS
    //ESCUCHAR LAS SELECCIONES DEL SPINNER

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_spinner_visiblidad);


        ArrayAdapter<CharSequence> nuevo_adapter = ArrayAdapter.createFromResource(this, R.array.array_opciones_visibilidad, android.R.layout.simple_spinner_item);
        nuevo_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spinner = findViewById(R.id.spinnerv);
        spinner.setAdapter(nuevo_adapter);

        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(MainActivity.ETIQUETA_LOG, "Opción nueva seleccionada en el spinner");
        TextView textView = (TextView)view;
        String texto_activo = textView.getText().toString();
        Log.d(MainActivity.ETIQUETA_LOG, "Opción tocada " + textView.getText().toString());
        //TODO cambiar la visibilidad del elemento ImageView
        ImageView imageView = findViewById(R.id.imagenmuestra);
        switch (texto_activo)
        {
            case "VISIBLE":
                    imageView.setVisibility(View.VISIBLE);
                break;
            case "INVISIBLE":
                    imageView.setVisibility(View.INVISIBLE);
                break;
            case "GONE":
                    imageView.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //SERÍA INVOCADA CUANDO CAMBIA EL ADAPTER Y UNA OPCIÓN SELECCIONADA DEJA DE ESTAR DISPONIBLE
    }
}