package edu.val.ejemploslibroandroidapp.tema6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class ActividadIntentImplicitoWeb extends AppCompatActivity {

    private static final String PREFIJO_GOOGLE = "https://www.google.com/search?q=";
    private EditText terminoBusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_intent_implicito_web);

        this.terminoBusqueda = findViewById(R.id.terminoBusqueda);
    }

    public void buscar (View view)
    {
        String busqueda = terminoBusqueda.getText().toString();
        Log.d(MainActivity.ETIQUETA_LOG, "Término de búsqueda " + busqueda);

        //abrir el navegador con un página de google que busque el término del usuario
        openWebPage(busqueda);
    }

    public void openWebPage(String busqueda) {
        Uri webpage = Uri.parse(PREFIJO_GOOGLE+busqueda);
        Log.d(MainActivity.ETIQUETA_LOG, "URI =  " + webpage.toString());

        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);//IMPLÍCITO
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            Log.d(MainActivity.ETIQUETA_LOG, "Al menos hay una actividad en el teléfono que puede llevar a cabo la acción del intent");
            startActivity(intent);
        }
    }


}