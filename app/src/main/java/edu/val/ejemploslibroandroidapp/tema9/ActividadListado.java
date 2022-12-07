package edu.val.ejemploslibroandroidapp.tema9;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.val.ejemploslibroandroidapp.R;

public class ActividadListado extends AppCompatActivity {

    private RecyclerView recyclerView;
    private static final int TAMANIO_LISTA_LIBROS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_listado);
        this.recyclerView = findViewById(R.id.recview);

        List<Libro> lista_libros =  generarListaLibros();
        AdapterListaLibros adapterListaLibros = new AdapterListaLibros(lista_libros);

        this.recyclerView.setAdapter(adapterListaLibros);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true);
        this.recyclerView.setLayoutManager(layoutManager);




    }

    private List<Libro> generarListaLibros ()
    {
        List<Libro> lista_libros = null;
        Libro libro_aux = null;

            lista_libros = new ArrayList<Libro>(TAMANIO_LISTA_LIBROS);

            for (int i = 0; i < TAMANIO_LISTA_LIBROS; i++)
                {
                    libro_aux = new Libro(R.mipmap.ic_launcher,"TITULO "+ i);
                    lista_libros.add(libro_aux);
                }

        return lista_libros;

    }



}