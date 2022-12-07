package edu.val.ejemploslibroandroidapp.tema10;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema11.RedUtil;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class ActividadCanciones extends AppCompatActivity implements SearchView.OnQueryTextListener, LoaderManager.LoaderCallbacks<ResultadoCanciones> {


    private SearchView searchView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView nresultados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_canciones);
        this.searchView = findViewById(R.id.cajaBuscaCanciones);
        this.searchView.setOnQueryTextListener(this);
        this.progressBar = findViewById(R.id.pbc);
        this.recyclerView = findViewById(R.id.rvc);
        this.nresultados = findViewById(R.id.nresultados);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        if (RedUtil.hayInternet(this))
        {
            Log.d(MainActivity.ETIQUETA_LOG, "SÍ HAY INTERNET");
            //TODO buscar
            this.progressBar.setVisibility(View.VISIBLE);
            BusquedaItunes busquedaItunes = new BusquedaItunes(this);
            busquedaItunes.execute(query);

            //CON ExecutorService
           /* BusquedaItunes2 busquedaItunes2 = new BusquedaItunes2(query);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(busquedaItunes2);*/

            //CON AsyncTaskLoader
            /*LoaderManager lm = LoaderManager.getInstance(this);
            lm.initLoader(37, null, this);*/

        } else {
            Log.d(MainActivity.ETIQUETA_LOG, "NO HAY INTERNET");
            Toast.makeText(this, "SIN CONEXIÓN A INTERNET", Toast.LENGTH_LONG).show();
        }


        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return false;
    }

    public void actualizarListaCanciones(ResultadoCanciones resultadoCanciones) {
        this.progressBar.setVisibility(View.INVISIBLE);

        //TODO ACTUALIZAR EL RECYCLER VIEW
        Log.d(MainActivity.ETIQUETA_LOG, "Listado recibido 1 AsyncTask = " + resultadoCanciones.toString());
    }

    public static void actualizarListaCancionesS(ResultadoCanciones resultadoCanciones) {
        //TODO ACTUALIZAR EL RECYCLER VIEW
        Log.d(MainActivity.ETIQUETA_LOG, "Listado recibido 2 ExecutorService = " + resultadoCanciones.toString());
    }

    @NonNull
    @Override
    public Loader<ResultadoCanciones> onCreateLoader(int id, @Nullable Bundle args) {

        BusquedaItunes3 busquedaItunes3 = null;

        busquedaItunes3 = new BusquedaItunes3(this, "enrique");


        return busquedaItunes3;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ResultadoCanciones> loader, ResultadoCanciones rc) {

        Log.d(MainActivity.ETIQUETA_LOG, "onLoadFinished");
        Log.d(MainActivity.ETIQUETA_LOG, "Listado recibido 3 AsyncTaskLoader= " + rc.toString());
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ResultadoCanciones> loader) {

    }


}