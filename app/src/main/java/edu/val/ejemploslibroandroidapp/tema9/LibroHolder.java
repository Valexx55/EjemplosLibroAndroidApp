package edu.val.ejemploslibroandroidapp.tema9;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.val.ejemploslibroandroidapp.R;

public class LibroHolder extends RecyclerView.ViewHolder {

    private ImageView imagen_libro;
    private TextView titulo_libro;

    public LibroHolder(@NonNull View itemView) {
        super(itemView);
        this.imagen_libro = itemView.findViewById(R.id.imagenlibro);
        this.titulo_libro = itemView.findViewById(R.id.titulolibro);

    }

    public void cargarLibroEnHolder (Libro l)
    {
        this.imagen_libro.setImageResource(l.getImagen());
        this.titulo_libro.setText(l.getTitulo());
    }
}
