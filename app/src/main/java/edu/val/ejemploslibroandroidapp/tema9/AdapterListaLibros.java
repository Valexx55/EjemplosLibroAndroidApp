package edu.val.ejemploslibroandroidapp.tema9;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.val.ejemploslibroandroidapp.R;

public class AdapterListaLibros extends RecyclerView.Adapter<LibroHolder> {

    private List<Libro> lista_libros;

    public AdapterListaLibros (List<Libro> lista_libros)
    {
        this.lista_libros = lista_libros;
    }

    @NonNull
    @Override
    public LibroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LibroHolder libroHolder = null;

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View fila_libro = layoutInflater.inflate(R.layout.fila_libro, parent, false);
            libroHolder = new LibroHolder(fila_libro);


        return libroHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LibroHolder holder, int position) {

            Libro l = lista_libros.get(position);
            holder.cargarLibroEnHolder(l);
    }

    @Override
    public int getItemCount() {
        return this.lista_libros.size();
    }
}
