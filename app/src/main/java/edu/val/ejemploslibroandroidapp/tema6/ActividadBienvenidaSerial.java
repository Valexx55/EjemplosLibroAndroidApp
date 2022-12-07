package edu.val.ejemploslibroandroidapp.tema6;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class ActividadBienvenidaSerial extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_bienvenida);

        this.textView = findViewById(R.id.textViewBienvenida);
        InfoFormulario infoFormulario = getIntent().getParcelableExtra("FORMULARIO");
        Log.d(MainActivity.ETIQUETA_LOG, "INfo recibida "+ infoFormulario);

        this.textView.setText("BIENVENIDO " + infoFormulario.getNombre());




    }
}