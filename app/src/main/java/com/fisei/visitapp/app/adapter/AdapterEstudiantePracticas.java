package com.fisei.visitapp.app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.fisei.visitapp.app.R;
import com.fisei.visitapp.app.entity.PasantiaPracticas;
import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by diegoztc on 19/02/15.
 */
public class AdapterEstudiantePracticas extends ArrayAdapter {
// Hacemos que nuestra clase herede las características de un ArrayAdapter

    Activity context;
    NameValuePair[] datos;
    List<PasantiaPracticas> listaPracticas;

    /* Creamos las variables necesarias para capturar el contexto
    *  y los datos que se publicarán en la lista
    */


    public AdapterEstudiantePracticas(Activity context, List<PasantiaPracticas> lista) {
        super(context, R.layout.listview_estudiantes,lista);
        this.context=context;
        this.listaPracticas=lista;
        // TODO Auto-generated constructor stub
    }



    /* Constructor de la clase, donde pasamos por parámetro los datos
     * a mostrar en la lista y el contexto
    */
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Rescatamos cada item del listview y lo inflamos con nuestro layout
        View item = convertView;
        item = context.getLayoutInflater().inflate(R.layout.listview_estudiante_practicas, null);

        PasantiaPracticas practica= listaPracticas.get(position);


        TextView entidad=(TextView) item.findViewById(R.id.txtLEEntidad);

        TextView horas=(TextView) item.findViewById(R.id.txtLEEHoras);
        TextView finicio=(TextView) item.findViewById(R.id.txtLEFechaInicio);
        TextView ffin=(TextView) item.findViewById(R.id.txtLEFechaFin);
        TextView estado=(TextView) item.findViewById(R.id.txtLEEstado);

        entidad.setText(practica.getEntidad());
        horas.setText(String.valueOf(practica.getHorasPracticas()));
        finicio.setText(String.valueOf(practica.getFechaInicio()));
        ffin.setText(String.valueOf(practica.getFechaFin()));
        estado.setText(String.valueOf(practica.getEstado()));

        return item;
    }
}

