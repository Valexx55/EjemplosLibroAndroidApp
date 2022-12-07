package edu.val.ejemploslibroandroidapp.tema9;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class ActividadListadoBusqueda extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private static final int TAMANIO_LISTA_LIBROS = 100;
    private List<Libro> lista_libros;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_listado_busqueda);

        this.recyclerView = findViewById(R.id.recview);
        this.searchView = findViewById(R.id.searchView2);

        this.lista_libros =  generarListaLibros();
        AdapterListaLibros adapterListaLibros = new AdapterListaLibros(this.lista_libros);

        this.recyclerView.setAdapter(adapterListaLibros);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.recyclerView.setLayoutManager(layoutManager);

        this.searchView.setOnQueryTextListener(this);



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


    private List<Libro> filtrarLista (String filtro, List<Libro> lista_actual)
    {
        List<Libro> lista_nueva = null;

            lista_nueva = new ArrayList<Libro>();

            for (Libro l : lista_actual)
            {
                if (l.getTitulo().contains(filtro))
                {
                    lista_nueva.add(l);
                }
            }

        return lista_nueva;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d(MainActivity.ETIQUETA_LOG, "Busqueda introducida = " + query);
        //filtrar el listado sólo con los items que coinciden con la búsquedad del usuario

            this.lista_libros = filtrarLista (query, this.lista_libros);
            AdapterListaLibros adapterListaLibros = new AdapterListaLibros(this.lista_libros);
            this.recyclerView.setAdapter(adapterListaLibros);


        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d(MainActivity.ETIQUETA_LOG, "Contenido modifacado = " + newText);
        if (newText.equals(""))
        {
            this.lista_libros = generarListaLibros();
            AdapterListaLibros adapterListaLibros = new AdapterListaLibros(this.lista_libros);
            this.recyclerView.setAdapter(adapterListaLibros);
        }
        return false;
    }
}