package edu.val.ejemploslibroandroidapp.tema7;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class SeleccionCalendario extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //personalizar el calendario
        Dialog calendario = null;
        int anio, mes, dia;
        Calendar calendario_actual;

            calendario_actual = Calendar.getInstance();
            anio = calendario_actual.get(Calendar.YEAR);
            mes = calendario_actual.get(Calendar.MONTH);
            dia = calendario_actual.get(Calendar.DATE);

            calendario = new DatePickerDialog(getActivity(), this, anio, mes, dia);

        return calendario;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month++;//
        String fecha = dayOfMonth + " / " + month + " / "+ year;
        Log.d(MainActivity.ETIQUETA_LOG, "Fecha seleccionada " + fecha);
        ActividadSeleccionFechaYHora asf = (ActividadSeleccionFechaYHora)getActivity();
        asf.mostrarFechaSeleccionada(fecha);

    }
}
