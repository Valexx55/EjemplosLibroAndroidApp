package edu.val.ejemploslibroandroidapp.tema11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema10.Cancion;
import edu.val.ejemploslibroandroidapp.tema10.ResultadoCanciones;

public class AdapterCanciones extends RecyclerView.Adapter<CancionViewHolder> {


    private ResultadoCanciones rc;


    public AdapterCanciones (ResultadoCanciones rc)
    {
        this.rc = rc;
    }

    @NonNull
    @Override
    public CancionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CancionViewHolder cancionViewHolder = null;

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View fila_cancion = layoutInflater.inflate(R.layout.fila_cancion, parent, false);
            cancionViewHolder = new CancionViewHolder(fila_cancion);

        return cancionViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CancionViewHolder holder, int position) {

        Cancion cancion = rc.getResults().get(position);
        holder.cargarCancionEnHolder(cancion);

    }

    @Override
    public int getItemCount() {
        return rc.getResultCount();
    }
}
