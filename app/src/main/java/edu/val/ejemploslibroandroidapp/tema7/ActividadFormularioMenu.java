package edu.val.ejemploslibroandroidapp.tema7;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class ActividadFormularioMenu extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener {

    //1 definir los elementos visuales de interés en la clase
    private EditText editTextNombre;
    private EditText editTextEdad;
    private RadioButton radioButtonHombre;
    private RadioButton radioButtonMujer;
    private CheckBox checkBoxMayorEdad;
    private ImageView imagenAndroid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_formulario_menu);
        //2 Asociar los atributos de la clase con los controles del layout
        initActivity();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void limpiarFormulario ()
    {
        this.editTextNombre.setText("");
        this.editTextEdad.setText("");
        this.radioButtonHombre.setChecked(false);
        this.radioButtonMujer.setChecked(false);
        this.checkBoxMayorEdad.setChecked(false);
        Log.d(MainActivity.ETIQUETA_LOG, "Formulario limpio");

        Toast toast = Toast.makeText(this, "Formulario limpiado", Toast.LENGTH_LONG);
        toast.show();
    }

    private void salir ()
    {
        Log.d(MainActivity.ETIQUETA_LOG, "Saliendo ...");
        //this.finish();

        AlertDialog ad = new AlertDialog.Builder(this).create();
        //this es el contexto
        ad.setTitle("Salir");//definimos el titulo
        ad.setMessage("¿Desea salir?");//y el mensaje del aviso
        //y los botones con sus respectivas opciones ya programadas
        ad.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }});
        ad.setButton(AlertDialog.BUTTON_POSITIVE, "SÍ",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActividadFormularioMenu.this.finish();
                    }});
        ad.show(); //por último, lo mostramos
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(MainActivity.ETIQUETA_LOG, "Se ha tocado una opción del menú");
        switch (item.getItemId())
        {
            case R.id.opcionborrar:
                Log.d(MainActivity.ETIQUETA_LOG, "Se ha tocado la opción limpiar");
                limpiarFormulario ();
                break;
            case R.id.opcionsalir:
                Log.d(MainActivity.ETIQUETA_LOG, "Se ha tocado la opción salir");
                salir ();
                break;
            case android.R.id.home:
                Log.d(MainActivity.ETIQUETA_LOG, "Se ha tocado la opción salir con la flecha");
                salir ();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void initActivity ()
    {
        this.editTextNombre = findViewById(R.id.editTextTextPersonName);
        this.editTextEdad = findViewById(R.id.editTextNumber);
        this.radioButtonHombre = findViewById(R.id.radioButtonHombre);
        this.radioButtonMujer = findViewById(R.id.radioButtonMujer);
        this.checkBoxMayorEdad = findViewById(R.id.checkBox);

        //ocultarBarra
        getSupportActionBar().hide();
        //mostarBarrar
        getSupportActionBar().show();
        //dibujar la flecha hacia atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //dibujamos el menú contextual
        this.imagenAndroid = findViewById(R.id.imageView);
        registerForContextMenu(this.imagenAndroid);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, 3, 3, "BORRAR");

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Log.d(MainActivity.ETIQUETA_LOG, "Opcion tocada = " + item.getItemId());
        this.imagenAndroid.setVisibility(View.INVISIBLE);

        return super.onContextItemSelected(item);

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


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}