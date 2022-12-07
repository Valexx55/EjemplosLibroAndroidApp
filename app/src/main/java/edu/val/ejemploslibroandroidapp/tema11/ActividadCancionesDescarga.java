package edu.val.ejemploslibroandroidapp.tema11;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema10.BusquedaItunes;
import edu.val.ejemploslibroandroidapp.tema10.BusquedaItunes3;
import edu.val.ejemploslibroandroidapp.tema10.Cancion;
import edu.val.ejemploslibroandroidapp.tema10.ResultadoCanciones;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class ActividadCancionesDescarga extends AppCompatActivity implements SearchView.OnQueryTextListener, LoaderManager.LoaderCallbacks<ResultadoCanciones> {


    private SearchView searchView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView nresultados;
    private String ruta_cancion_descarga;
    private String url_cancion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_canciones_descarga);
        this.searchView = findViewById(R.id.cajaBuscaCanciones);
        this.searchView.setOnQueryTextListener(this);
        this.progressBar = findViewById(R.id.pbc);
        this.recyclerView = findViewById(R.id.rvc);
        this.nresultados = findViewById(R.id.nresultados);

    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        if (RedUtil.hayInternet(this)) {
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

        if (newText.equals("")) {
            ponerListaVacia();
        }
        return false;
    }

    private void ponerListaVacia() {
        ResultadoCanciones resultadoCancionesVacio = new ResultadoCanciones(0, new ArrayList<Cancion>(0));
        this.adapter = new AdapterCanciones(resultadoCancionesVacio);
        this.recyclerView.setAdapter(this.adapter);
        this.nresultados.setText("0 resultados");
    }

    public void actualizarListaCanciones(ResultadoCanciones resultadoCanciones) {
        this.progressBar.setVisibility(View.INVISIBLE);
        if (resultadoCanciones != null && resultadoCanciones.getResultCount() > 0) {
            this.nresultados.setText(resultadoCanciones.getResultCount() + " resultados");
            this.adapter = new AdapterCanciones(resultadoCanciones);
            this.recyclerView.setAdapter(this.adapter);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        } else {
            Log.d(MainActivity.ETIQUETA_LOG, "BÚSQUEDA SIN RESULTADOS");
            Toast.makeText(this, "BÚSQUEDA SIN RESULTADOS", Toast.LENGTH_LONG).show();

            ponerListaVacia();
        }
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


    public void reproducirCancion(View view) {
        Log.d(MainActivity.ETIQUETA_LOG, "Boton reproducir tocado");
        //TODO que se reproduzca la canción seleccionada


        String url_cancion = (String) view.getTag();

        Log.d(MainActivity.ETIQUETA_LOG, "URL CANCION = " + url_cancion);
        Uri uri = Uri.parse(url_cancion);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, uri);
        mediaPlayer.start();//reproduzco la canción
    }


    private void pedirPermisos() {
        String[] array_permisos = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(array_permisos, 303);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(MainActivity.ETIQUETA_LOG, "Permiso concedido");
            iniciarDescarga();
        } else {
            Log.d(MainActivity.ETIQUETA_LOG, "Permiso denegado");
            Toast toast = Toast.makeText(this, "NO SE PUEDE INICIAR LA DESCARGA. Permiso denegado", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void descargarCancion(View view) {
        Log.d(MainActivity.ETIQUETA_LOG, "Boton descargarCancion tocado");
        //TODO que se descaergar la canción seleccionada
        this.url_cancion = (String) view.getTag();

        Log.d(MainActivity.ETIQUETA_LOG, "URL CANCION = " + url_cancion);
        pedirPermisos();

    }

    private void iniciarDescarga() {
        Log.d(MainActivity.ETIQUETA_LOG, "Iniciando descarga...");
        DownloadManager.Request peticion = prepararPeticionDescarga (this.url_cancion);
        solicitarPeticionDescarga (peticion);

    }

    private void solicitarPeticionDescarga (DownloadManager.Request request)
    {
        DownloadManager downloadManager = null;
        long id_descarga = 0;
        DescargaReceiver descargaReceiver = null;
        IntentFilter intentFilter = null;

            intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
            descargaReceiver = new DescargaReceiver(this);
            registerReceiver(descargaReceiver, intentFilter);

            downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
            id_descarga = downloadManager.enqueue(request);
            descargaReceiver.setId_descarga(id_descarga);

    }

    public void actualizarEstadoDescarga (int estado_descarga)
    {
        if (estado_descarga==DownloadManager.STATUS_FAILED)
        {
            Log.d(MainActivity.ETIQUETA_LOG, "La descarga fue MAL");
        } else if (estado_descarga==DownloadManager.STATUS_SUCCESSFUL)
        {
            Log.d(MainActivity.ETIQUETA_LOG, "La descarga fue BIEN");

        }
    }


    private DownloadManager.Request prepararPeticionDescarga (String url_cancion)
    {
        DownloadManager.Request peticion = null;

            peticion = new DownloadManager.Request(Uri.parse(url_cancion));
            peticion.setMimeType("audio/mp3");
            peticion.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            peticion.setTitle("Muestra canción itunes");

            String ruta_local = getExternalFilesDir(null).getPath()+"/cancion1.mp3";
            Uri uri_destino = Uri.fromFile(new File(ruta_local));
            peticion.setDestinationUri(uri_destino);

        return peticion;
    }



















































}