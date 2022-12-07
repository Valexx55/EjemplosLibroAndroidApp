package edu.val.ejemploslibroandroidapp.tema5;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class ActividadFormularioValidacion extends AppCompatActivity {

    //1 definir los elementos visuales de interés en la clase
    private EditText editTextNombre;
    private EditText editTextEdad;
    private RadioButton radioButtonHombre;
    private RadioButton radioButtonMujer;
    private CheckBox checkBoxMayorEdad;
    private boolean forumlario_valido;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_formulario_validacion);


        //2 Asociar los atributos de la clase con los controles del layout
        initActivity();
        if (savedInstanceState!=null)

        {
            Log.d(MainActivity.ETIQUETA_LOG, "Hay info en el saco. La actividad viene de recrearse");
            this.forumlario_valido = savedInstanceState.getBoolean("VALIDADO");
        }
        else {
            Log.d(MainActivity.ETIQUETA_LOG, "La actividad es de nueva creación. Bundle vacío");

        }
        Log.d(MainActivity.ETIQUETA_LOG, "Formulario válido " + this.forumlario_valido);



    }

    private void initActivity ()
    {
        this.editTextNombre = findViewById(R.id.editTextTextPersonName);
        this.editTextEdad = findViewById(R.id.editTextNumber);
        this.radioButtonHombre = findViewById(R.id.radioButtonHombre);
        this.radioButtonMujer = findViewById(R.id.radioButtonMujer);
        this.checkBoxMayorEdad = findViewById(R.id.checkBox);
        this.forumlario_valido = false;

    }

    //3 detectar el evento

    /**
     * Esta función es invocada al pulsar el botón
     * @param view el botón pulsado ( el control que recibe la acción )
     */
    public void botonAceptarPulsado(View view) {
        Log.d(MainActivity.ETIQUETA_LOG, "Ha pulsado el botón de aceptar");
        mostrarDatosFormulario ();
        this.forumlario_valido = formularioValido();
        Log.d(MainActivity.ETIQUETA_LOG, "Validación OK " + this.forumlario_valido);

    }

    private void mostrarDatosFormulario ()
    {
        Log.d(MainActivity.ETIQUETA_LOG, "Nombre intro " + this.editTextNombre.getText().toString());
        Log.d(MainActivity.ETIQUETA_LOG, "Edad intro " + this.editTextEdad.getText().toString());
        Log.d(MainActivity.ETIQUETA_LOG, "Radio Button Hombre marcado " + this.radioButtonHombre.isChecked());
        Log.d(MainActivity.ETIQUETA_LOG, "Radio Button Mujer marcado " + this.radioButtonMujer.isChecked());
        Log.d(MainActivity.ETIQUETA_LOG, "Checkbox mayor de edad marcado " + this.checkBoxMayorEdad.isChecked());
    }

    private boolean nombreValido (String nombre)
    {
        boolean valido = false;

            valido = (nombre != null && nombre.matches("[a-zA-Z\\s]+"));//si es distinto de null y tiene al menos un caracter de la a a la z o espacios

        return valido;

    }
    private boolean edadValida (String edad)
    {
        boolean valido = false;


            valido = (edad != null && edad.matches("[0-9]+"));//si es un número Expresiones Regulares

        return valido;
    }


    /**
     *
     * @return true si el formulario es válido o false en caso contrario
     */
    private boolean formularioValido ()
    {
        boolean valido = false;

           valido = nombreValido(this.editTextNombre.getText().toString()) && edadValida(this.editTextEdad.getText().toString());

        return valido;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("VALIDADO", this.forumlario_valido);
    }

    @Override
    public void onBackPressed() {
        Log.d(MainActivity.ETIQUETA_LOG, "Botón hacia atrás pulsado");
        this.finish();
        //super.onBackPressed();
    }
}