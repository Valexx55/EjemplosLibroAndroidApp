package edu.val.ejemploslibroandroidapp.tema6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import edu.val.ejemploslibroandroidapp.R;

public class ActividadBienvenida extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_bienvenida);

        this.textView = findViewById(R.id.textViewBienvenida);

        String nombre_usuario = getIntent().getStringExtra("NOMBRE");
        this.textView.setText("BIENVENIDO " + nombre_usuario);
    }
}