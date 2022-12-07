package edu.val.ejemploslibroandroidapp.tema4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import edu.val.ejemploslibroandroidapp.R;

public class ActividadIconFont extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_icon_font);


        this.textView = findViewById(R.id.texto);

        //cargar la fuente
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fuenteanimales.otf");
        this.textView.setTypeface(typeface);

        //this.textView.setText("HOLA");
        this.textView.setText(R.string.caballito_de_mar);

    }
}