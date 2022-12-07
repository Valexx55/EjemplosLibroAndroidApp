package edu.val.ejemploslibroandroidapp.tema6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class ActividadFormularioColor extends AppCompatActivity {

    //1 definir los elementos visuales de interés en la clase
    private EditText editTextNombre;
    private EditText editTextEdad;
    private RadioButton radioButtonHombre;
    private RadioButton radioButtonMujer;
    private CheckBox checkBoxMayorEdad;

    private Button botonColorFavorito;

    private ActivityResultLauncher<Intent> mStartForResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_formulario_color);
        //2 Asociar los atributos de la clase con los controles del layout
        initActivity();
        initActivity2();


    }

    private void initActivity ()
    {
        this.editTextNombre = findViewById(R.id.editTextTextPersonName);
        this.editTextEdad = findViewById(R.id.editTextNumber);
        this.radioButtonHombre = findViewById(R.id.radioButtonHombre);
        this.radioButtonMujer = findViewById(R.id.radioButtonMujer);
        this.checkBoxMayorEdad = findViewById(R.id.checkBox);

        this.botonColorFavorito = findViewById(R.id.botonColorFavorito);


    }

    /**
     *
     * Intent chooser = Intent.createChooser(intent_implicito_web, "ELIJA APP");
     *
     * <intent-filter>
     *                 <action android:name="android.intent.action.VIEW" />
     *                 <category android:name="android.intent.category.DEFAULT" />
     *                 <category android:name="android.intent.category.BROWSABLE" />
     *                 <!-- Accepts URIs that begin with "http://www.example.com/gizmos” -->
     *                 <data android:scheme="https" android:host="www.marca.com" />
     *                 <data android:scheme="https" android:host="www.as.com" />
     *                 <data android:scheme="https" android:host="www.linkedin.com" />
     *                 <data android:scheme="https" android:host="www.soloboxeo.com" />
     *                 <data android:scheme="https" android:host="www.libertaddigital.com" android:path="/chic/" />
     *                 <data android:scheme="https" android:host="www.elespanol.com" android:path="/jaleos/" />
     *
     *             </intent-filter>
     */

    private void initActivity2 ()
    {
        this.mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            // Handle the Intent
                            int color = intent.getIntExtra("COLOR_ELEGIDO", 0);
                            ActividadFormularioColor.this.botonColorFavorito.setBackgroundColor(color);

                        }
                    }
                });

    }






    //3 detectar el evento

    /**
     * Esta función es invocada al pulsar el botón
     * @param view el botón pulsado ( el control que recibe la acción )
     */
    public void botonAceptarPulsado(View view) {
        Log.d(MainActivity.ETIQUETA_LOG, "Ha pulsado el botón de aceptar");
        mostrarDatosFormulario ();

    }

    private void mostrarDatosFormulario ()
    {
        Log.d(MainActivity.ETIQUETA_LOG, "Nombre intro " + this.editTextNombre.getText().toString());
        Log.d(MainActivity.ETIQUETA_LOG, "Edad intro " + this.editTextEdad.getText().toString());
        Log.d(MainActivity.ETIQUETA_LOG, "Radio Button Hombre marcado " + this.radioButtonHombre.isChecked());
        Log.d(MainActivity.ETIQUETA_LOG, "Radio Button Mujer marcado " + this.radioButtonMujer.isChecked());
        Log.d(MainActivity.ETIQUETA_LOG, "Checkbox mayor de edad marcado " + this.checkBoxMayorEdad.isChecked());
    }

    public void seleccionarColor (View view)
    {
        Log.d(MainActivity.ETIQUETA_LOG, "Toca color ");
        //VERSION 1 DE SUBACTIVIDADES
        Intent intent = new Intent(this, SeleccionColorActivity.class);
        //startActivityForResult(intent, 333);
        this.mStartForResult.launch(intent);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //este método, será invocado automáticamente a la vuelta y ya tendré el color seleccionado en la otra actividad

        if (requestCode == 333)
        {
            Log.d(MainActivity.ETIQUETA_LOG, "Es la respuesta a mi petición ");
            if (resultCode == RESULT_OK)
            {
                Log.d(MainActivity.ETIQUETA_LOG, "La subactividad finalizó bien");
                //obtener el color
                int color = data.getIntExtra("COLOR_ELEGIDO", 0);
                this.botonColorFavorito.setBackgroundColor(color);

            }
        }

    }
}