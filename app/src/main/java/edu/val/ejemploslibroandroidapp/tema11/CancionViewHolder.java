package edu.val.ejemploslibroandroidapp.tema11;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema10.Cancion;

public class CancionViewHolder extends RecyclerView.ViewHolder {

    private TextView titulo_cancion;
    private TextView artista_cancion;
    private ImageView icono_reproduccion;
    private ImageView icono_descarga;



    public CancionViewHolder(@NonNull View itemView) {
        super(itemView);
        this.titulo_cancion = itemView.findViewById(R.id.nombre_cancion);
        this.artista_cancion = itemView.findViewById(R.id.nombre_artista);
        this.icono_reproduccion = itemView.findViewById(R.id.icreproduccion);
        this.icono_descarga = itemView.findViewById(R.id.icdescarga);


    }

    public void cargarCancionEnHolder(Cancion cancion)
    {
        this.artista_cancion.setText(cancion.getArtistName());
        this.titulo_cancion.setText(cancion.getTrackName());
        this.icono_reproduccion.setTag(cancion.getPreviewUrl());
        this.icono_descarga.setTag(cancion.getPreviewUrl());


    }





}
