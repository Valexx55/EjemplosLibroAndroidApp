package edu.val.ejemploslibroandroidapp.tema10;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class BusquedaItunes3 extends AsyncTaskLoader<ResultadoCanciones> {

    private String busqueda;
    private final static String URI_ITUNES = "https://itunes.apple.com/search/?media=music&term=";

    public BusquedaItunes3(@NonNull Context context, String busqueda) {
        super(context);
        this.busqueda = busqueda;
        Log.d(MainActivity.ETIQUETA_LOG, "BusquedaItunes3 constructor");
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Nullable
    @Override
    public ResultadoCanciones loadInBackground() {
        ResultadoCanciones resultadoCanciones = null;
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        InputStreamReader inputStreamReader = null;
        Gson gson = null;

        try {

            busqueda = URLEncoder.encode(busqueda, "UTF-8");
            busqueda = URI_ITUNES + busqueda;
            Log.d(MainActivity.ETIQUETA_LOG, "Llamando a " + busqueda);
            url = new URL(busqueda);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Log.d(MainActivity.ETIQUETA_LOG, "Respuesta OK ");
                inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                gson = new Gson();
                resultadoCanciones = gson.fromJson(inputStreamReader, ResultadoCanciones.class);

            } else {
                Log.d(MainActivity.ETIQUETA_LOG, "Respuesta FALLO ");
            }


        } catch (Throwable fallo) {
            Log.e(MainActivity.ETIQUETA_LOG, "Se ha producido un fallo ", fallo);
        } finally {

            try {
                inputStreamReader.close();
                httpURLConnection.disconnect();
            } catch (IOException ioException) {
                Log.e(MainActivity.ETIQUETA_LOG, "Fallo al liberar recursos ", ioException);
            }


        }


        return resultadoCanciones;
    }
}
