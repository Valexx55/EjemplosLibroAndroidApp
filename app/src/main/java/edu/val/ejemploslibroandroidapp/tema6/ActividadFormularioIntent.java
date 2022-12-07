package edu.val.ejemploslibroandroidapp.tema6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class ActividadFormularioIntent extends AppCompatActivity {

    //1 definir los elementos visuales de interés en la clase
    private EditText editTextNombre;
    private EditText editTextEdad;
    private RadioButton radioButtonHombre;
    private RadioButton radioButtonMujer;
    private CheckBox checkBoxMayorEdad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_formulario);
        //2 Asociar los atributos de la clase con los controles del layout
        initActivity();

    }

    private void initActivity ()
    {
        this.editTextNombre = findViewById(R.id.editTextTextPersonName);
        this.editTextEdad = findViewById(R.id.editTextNumber);
        this.radioButtonHombre = findViewById(R.id.radioButtonHombre);
        this.radioButtonMujer = findViewById(R.id.radioButtonMujer);
        this.checkBoxMayorEdad = findViewById(R.id.checkBox);

    }

    //3 detectar el evento

    /**
     * Esta función es invocada al pulsar el botón
     * @param view el botón pulsado ( el control que recibe la acción )
     */
    public void botonAceptarPulsado(View view) {
        Log.d(MainActivity.ETIQUETA_LOG, "Ha pulsado el botón de aceptar");
        mostrarDatosFormulario ();
       //ir a la pantalla de Bienvenida - Explícito
        Intent intent_bienvenida = new Intent(this, ActividadBienvenida.class);
        String nombre_usuario = this.editTextNombre.getText().toString();
        intent_bienvenida.putExtra("NOMBRE", nombre_usuario);
        startActivity(intent_bienvenida);


    }

    private void mostrarDatosFormulario ()
    {
        Log.d(MainActivity.ETIQUETA_LOG, "Nombre intro " + this.editTextNombre.getText().toString());
        Log.d(MainActivity.ETIQUETA_LOG, "Edad intro " + this.editTextEdad.getText().toString());
        Log.d(MainActivity.ETIQUETA_LOG, "Radio Button Hombre marcado " + this.radioButtonHombre.isChecked());
        Log.d(MainActivity.ETIQUETA_LOG, "Radio Button Mujer marcado " + this.radioButtonMujer.isChecked());
        Log.d(MainActivity.ETIQUETA_LOG, "Checkbox mayor de edad marcado " + this.checkBoxMayorEdad.isChecked());
    }



}