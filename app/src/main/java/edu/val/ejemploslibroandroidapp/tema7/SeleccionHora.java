package edu.val.ejemploslibroandroidapp.tema7;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class SeleccionHora extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog reloj= null;
        int hora, minutos;
        Calendar calendar;

            calendar = Calendar.getInstance();
            hora = calendar.get (Calendar.HOUR_OF_DAY);
            minutos = calendar.get (Calendar.MINUTE);

            reloj = new TimePickerDialog(getActivity(), this, hora, minutos, true);


        return reloj;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String hora_final, hora, minuto;

            hora = (hourOfDay<10) ? "0"+hourOfDay : ""+hourOfDay;
            minuto = (minute<10) ? "0"+minute : ""+minute;
            hora_final = hora + ":" + minuto;

            Log.d(MainActivity.ETIQUETA_LOG, "Hora elegida= " + hora_final);
            ActividadSeleccionFechaYHora ash = (ActividadSeleccionFechaYHora) getActivity();
            ash.mostrarHoraSeleccionada(hora_final);
    }
}
