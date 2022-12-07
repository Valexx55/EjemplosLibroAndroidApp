package edu.val.ejemploslibroandroidapp.tema8;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;
import edu.val.ejemploslibroandroidapp.tema6.ActividadBienvenida;

public class ActividadFormularioPreferences extends AppCompatActivity {

    //1 definir los elementos visuales de interés en la clase
    private EditText editTextNombre;
    private EditText editTextEdad;
    private RadioButton radioButtonHombre;
    private RadioButton radioButtonMujer;
    private CheckBox checkBoxMayorEdad;
    private String nombre_guardado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_formulario);
        //leer el nombre
        this.nombre_guardado = PreferenciasFormulario.leerNombre(this);
        if (this.nombre_guardado != null)
        {
            Log.d(MainActivity.ETIQUETA_LOG, "Ya hay un nombre. Voy a Bienvenida");
            irABienvenida (this.nombre_guardado);
        }else {
            //si exsite, voy a la bienvenida
            Log.d(MainActivity.ETIQUETA_LOG, "NO hay un nombre. Me quedo aquí");
            initActivity();
        }

    }



    private void irABienvenida (String nombre)
    {
        Intent intent = new Intent(this, ActividadBienvenida.class);
        intent.putExtra("NOMBRE", nombre);
        startActivity(intent);

    }

    private void initActivity() {
        this.editTextNombre = findViewById(R.id.editTextTextPersonName);
        this.editTextEdad = findViewById(R.id.editTextNumber);
        this.radioButtonHombre = findViewById(R.id.radioButtonHombre);
        this.radioButtonMujer = findViewById(R.id.radioButtonMujer);
        this.checkBoxMayorEdad = findViewById(R.id.checkBox);

    }



    //3 detectar el evento

    /**
     * Esta función es invocada al pulsar el botón
     *
     * @param view el botón pulsado ( el control que recibe la acción )
     */
    public void botonAceptarPulsado(View view) {
        Log.d(MainActivity.ETIQUETA_LOG, "Ha pulsado el botón de aceptar");
        mostrarDatosFormulario();
        //guardar el nombre
        this.nombre_guardado = this.editTextNombre.getText().toString();
        PreferenciasFormulario.guardarNombre(this.nombre_guardado, this);
        Toast toast = Toast.makeText(this, "NOMBRE GUARDADO", Toast.LENGTH_LONG);
        toast.show();
        // ir a bienvenida
        irABienvenida(this.nombre_guardado);
    }

    private void mostrarDatosFormulario() {
        Log.d(MainActivity.ETIQUETA_LOG, "Nombre intro " + this.editTextNombre.getText().toString());
        Log.d(MainActivity.ETIQUETA_LOG, "Edad intro " + this.editTextEdad.getText().toString());
        Log.d(MainActivity.ETIQUETA_LOG, "Radio Button Hombre marcado " + this.radioButtonHombre.isChecked());
        Log.d(MainActivity.ETIQUETA_LOG, "Radio Button Mujer marcado " + this.radioButtonMujer.isChecked());
        Log.d(MainActivity.ETIQUETA_LOG, "Checkbox mayor de edad marcado " + this.checkBoxMayorEdad.isChecked());
    }


}