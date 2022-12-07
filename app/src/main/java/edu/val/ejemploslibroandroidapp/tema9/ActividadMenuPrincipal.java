package edu.val.ejemploslibroandroidapp.tema9;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.apendiceA.ActividadBotones;
import edu.val.ejemploslibroandroidapp.tema10.ActividadCanciones;
import edu.val.ejemploslibroandroidapp.tema11.ActividadCancionesDescarga;
import edu.val.ejemploslibroandroidapp.tema11.ActividadSeleccionContactos;
import edu.val.ejemploslibroandroidapp.tema11.ActividadSeleccionContactos2;
import edu.val.ejemploslibroandroidapp.tema12.ActividadBienvenidaInicio;
import edu.val.ejemploslibroandroidapp.tema13.FotoActivity;
import edu.val.ejemploslibroandroidapp.tema13.MapsActivity;
import edu.val.ejemploslibroandroidapp.tema1y2.Actividad2;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;
import edu.val.ejemploslibroandroidapp.tema3.ActividadFormulario;
import edu.val.ejemploslibroandroidapp.tema3.ActividadSpinnerVisiblidad;
import edu.val.ejemploslibroandroidapp.tema3.ActividadVideoView;
import edu.val.ejemploslibroandroidapp.tema3.ActividadWebViewFrameLayout;
import edu.val.ejemploslibroandroidapp.tema4.ActividadFormularioTemas;
import edu.val.ejemploslibroandroidapp.tema4.ActividadIconFont;
import edu.val.ejemploslibroandroidapp.tema5.ActividadCuadrosSetTag;
import edu.val.ejemploslibroandroidapp.tema5.ActividadCuadrosSetTagInflar;
import edu.val.ejemploslibroandroidapp.tema5.ActividadEstados;
import edu.val.ejemploslibroandroidapp.tema5.ActividadFormularioI18n;
import edu.val.ejemploslibroandroidapp.tema5.ActividadFormularioValidacion;
import edu.val.ejemploslibroandroidapp.tema6.ActividadFormularioColor;
import edu.val.ejemploslibroandroidapp.tema6.ActividadFormularioIntent;
import edu.val.ejemploslibroandroidapp.tema6.ActividadFormularioIntentSerial;
import edu.val.ejemploslibroandroidapp.tema6.ActividadIntentImplicitoWeb;
import edu.val.ejemploslibroandroidapp.tema7.ActividadFormularioMenu;
import edu.val.ejemploslibroandroidapp.tema7.ActividadSeleccionFechaYHora;
import edu.val.ejemploslibroandroidapp.tema8.ActividadBaseDatos;
import edu.val.ejemploslibroandroidapp.tema8.ActividadFormularioPreferences;
import edu.val.ejemploslibroandroidapp.tema8.ActividadSpinnerVisiblidadPersistencia;

/**
 * Gracias por adquirir "Creación de Aplicaciones con Android"
 * <p>
 *
 * La colección de enlaces web referida en los videos puede encontrarla en el directorio /res/raw/enlaces_libro_android.ods
 * en formato de una hoja de cálculo (Excel)
 *
 * Esta aplicación es un material complementario de la obra, por lo cual, se ve sujeto a los
 * derechos de propiedad intelectual de la misma. Su distribución y publicación están prohibidas.
 * <p>
 *
 * Esta es la Actividad Principal, que carga el menú lateral que da acceso a todos los
 * ejemplos programdos en cada vídeo.
 * <p>
 *
 * Puede alterar y jugar con el código todo lo que desee. De hecho, de eso se trata y le animo a ello.
 * Que amplíe y modifique los ejemplos a su gusto para prácticar.
 * <p>
 *
 * Pero antes, está bien que mantenga una copia original, por si necesita recuperar el estado original de los ejemplos.
 * <p>
 *
 * Si lo desea o necesita, puede contactar con el autor de la obra en su perfil
 * profesional https://www.linkedin.com/in/valerianomoreno/
 */

public class ActividadMenuPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private boolean menu_visible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_menu_principal);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        this.drawerLayout = findViewById(R.id.drawl);
        this.navigationView = findViewById(R.id.navview);

        this.navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(MainActivity.ETIQUETA_LOG, "Hamburguesa tocada");
                if (menu_visible) {
                    //ocultarlo
                    drawerLayout.closeDrawers();
                    menu_visible = false;

                } else {
                    //mostrarlo
                    drawerLayout.openDrawer(GravityCompat.START);
                    menu_visible = true;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int num_opcion = item.getOrder();
        Log.d(MainActivity.ETIQUETA_LOG, "Opción menú lateral seleccionada " + num_opcion);
        Class clase = null;

        switch (num_opcion) {
            case 0:
                clase = MainActivity.class;
                break;
            case 1:
                clase = Actividad2.class;
                break;
            case 2:
                clase = ActividadFormulario.class;
                break;
            case 3:
                clase = ActividadSpinnerVisiblidad.class;
                break;
            case 4:
                clase = ActividadVideoView.class;
                break;
            case 5:
                clase = ActividadWebViewFrameLayout.class;
                break;
            case 6:
                clase = ActividadFormularioTemas.class;
                break;
            case 7:
                clase = ActividadIconFont.class;
                break;
            case 8:
                clase = ActividadEstados.class;
                break;
            case 9:
                clase = ActividadFormularioValidacion.class;
                break;
            case 10:
                clase = ActividadFormulario.class;
                break;
            case 11:
                clase = ActividadFormularioI18n.class;
                break;
            case 12:
                clase = ActividadCuadrosSetTag.class;
                break;
            case 13:
                clase = ActividadCuadrosSetTagInflar.class;
                break;
            case 14:
                clase = ActividadFormularioIntent.class;
                break;
            case 15:
                clase = ActividadIntentImplicitoWeb.class;
                break;
            case 16:
                clase = ActividadFormularioIntentSerial.class;
                break;
            case 17:
                clase = ActividadFormularioColor.class;
                break;
            case 18:
                clase = ActividadFormularioMenu.class;
                break;
            case 19:
                clase = ActividadSeleccionFechaYHora.class;
                break;
            case 20:
                clase = ActividadSpinnerVisiblidadPersistencia.class;
                break;
            case 21:
                clase = ActividadFormularioPreferences.class;
                break;
            case 22:
                clase = ActividadBaseDatos.class;
                break;
            case 23:
                clase = ActividadListado.class;
                break;
            case 24:
                clase = ActividadFragmentoDeslizante.class;
                break;
            case 25:
                clase = ActividadFragmentoDeslizanteTabs.class;
                break;
            case 26:
                clase = ActividadFormularioValidacionMaterial.class;
                break;
            case 27:
                clase = ActividadListadoBusqueda.class;
                break;
            case 28:
                clase = ActividadFormularioValidacionMaterialRound.class;
                break;
            case 29:
                clase = ActividadCanciones.class;
                break;
            case 30:
                clase = ActividadCancionesDescarga.class;
                break;
            case 31:
                clase = ActividadSeleccionContactos.class;
                break;
            case 32:
                clase = ActividadSeleccionContactos2.class;
                break;
            case 33:
                clase = ActividadBienvenidaInicio.class;
                break;
            case 34:
                clase = FotoActivity.class;
                break;
            case 35:
                clase = MapsActivity.class;
                break;
            case 36:
                clase = ActividadBotones.class;
                break;
        }


        drawerLayout.closeDrawers();
        menu_visible = false;

        Intent i = new Intent(this, clase);
        startActivity(i);

        return false;
    }
}

