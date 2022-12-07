package edu.val.ejemploslibroandroidapp.tema13;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class FotoActivity extends AppCompatActivity {


    private Uri uri_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
    }

    private void pedirPermisosFoto() {
        String[] apermisos = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(apermisos, 36);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            Log.d(MainActivity.ETIQUETA_LOG, "Permisos FOTO concedido");
            lanzarCamara();

        } else {
            Log.d(MainActivity.ETIQUETA_LOG, "Permisos FOTO denegado");
            Toast.makeText(this, "PERMISO TOMAR FORO DENEGADO", Toast.LENGTH_LONG).show();
        }
    }

    public void tomarFoto(View view) {
        pedirPermisosFoto();
    }


    private void lanzarCamara() {
        this.uri_foto = obtenerRutaFichero();
        if (uri_foto!=null)
        {
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri_foto);
            startActivityForResult(intent, 656);

        } else {
            Toast.makeText(this, "NO ES POSIBLE LANZAR LA CÁMARA", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK)
        {
            Log.d(MainActivity.ETIQUETA_LOG, "Foto bien tirada");
            ImageView imageView = findViewById(R.id.foto_tomada);
            imageView.setImageURI(uri_foto);

        } else {
            Log.d(MainActivity.ETIQUETA_LOG, "Foto cancelada");
        }



    }

     private Uri obtenerRutaFichero() {
        Uri uri = null;

            Date fecha_actual = new Date();
            String momemento_actual = new SimpleDateFormat("yyyyMMdd_HHmmss").format(fecha_actual);
            String nombre_fichero = "LIBRO_A_" + momemento_actual + ".jpg";

            //String ruta_foto = getExternalFilesDir(null).getPath() + "/" + nombre_fichero;
            String ruta_foto = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath()+"/"+nombre_fichero;

            Log.d(MainActivity.ETIQUETA_LOG, "ruta foto privada " + ruta_foto);
            File f = new File(ruta_foto);

            try {
                f.createNewFile();
                //uri = Uri.fromFile(f);
                uri = FileProvider.getUriForFile(this, "edu.val.ejemploslibroandroidapp", f);
                Log.d(MainActivity.ETIQUETA_LOG, "Uri _foto publica = " + uri);

            } catch (Throwable throwable) {
                Log.e(MainActivity.ETIQUETA_LOG, "error generando el fichero de destino ", throwable);
            }


        return uri;
    }


















    //private String

    /*private void lanzarCamara() {

        try {
            this.uri_foto = crearFicheroImagen();
            Intent intent_foto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent_foto.putExtra(MediaStore.EXTRA_OUTPUT,uri_foto);

            startActivityForResult(intent_foto, 300);
        }catch (Exception e)
        {
            Log.e(MainActivity.ETIQUETA_LOG, "ERORRROR", e);
        }

    }

    private Uri crearFicheroImagen() {
        Uri uri_dest = null;
        String momento_actual = null;
        String nombre_fichero = null;
        File f = null;

        momento_actual = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); //así nos garantizamos emplear un sufijo aleatorio: el nombre del archivo de la imagen incluirá el momento exacto
        nombre_fichero = "LA_" + momento_actual + ".jpg";


        String ruta_foto = getExternalFilesDir(null).getPath() + "/" + nombre_fichero;
        //ruta_foto =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath()+"/"+nombre_fichero;
        Log.d(MainActivity.ETIQUETA_LOG, "RUTA FOTO = " + ruta_foto);
        f = new File(ruta_foto);

        try {
            if (f.createNewFile()) {
                Log.d(MainActivity.ETIQUETA_LOG, "Fichero creado");
                //uri_dest = FileProvider.getUriForFile(this, "edu.val.ejemploslibroandroidapp", f);
                uri_dest = FileProvider.getUriForFile(this, "edu.val.ejemploslibroandroidapp", f);//  FileProvider.getUriForFile(this, "edu.val.ejemploslibroandroidapp", f);
                Log.d(MainActivity.ETIQUETA_LOG, "URI FOTO = " + uri_dest.toString());

            } else {
                Log.d(MainActivity.ETIQUETA_LOG, "Fichero NO creado (ya existía)");
            }
        } catch (Exception e) {
            Log.e(MainActivity.ETIQUETA_LOG, "Error creando el fichero", e);
        }

        return uri_dest;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(resultCode)
        {
            case RESULT_OK:
                Log.i(MainActivity.ETIQUETA_LOG, "Ha tirado bien la  foto");
                ImageView imageView = findViewById(R.id.foto_tomada);
                imageView.setImageURI(uri_foto);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                break;
            case RESULT_CANCELED:
                Log.i(MainActivity.ETIQUETA_LOG, "No ha seleccionado foto");
                break;
        }
    }*/
}