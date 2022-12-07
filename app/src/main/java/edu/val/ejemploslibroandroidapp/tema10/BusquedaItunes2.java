package edu.val.ejemploslibroandroidapp.tema10;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class BusquedaItunes2 implements Runnable {

    private String busqueda;
    private final static String URI_ITUNES =  "https://itunes.apple.com/search/?media=music&term=";
    private ResultadoCanciones rc;

    public BusquedaItunes2(String busqueda)
    {
        this.busqueda = busqueda;
    }

    @Override
    public void run() {



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
                this.rc = gson.fromJson(inputStreamReader, ResultadoCanciones.class);

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




        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                ActividadCanciones.actualizarListaCancionesS(BusquedaItunes2.this.rc);
            }
        });



    }


}
