package com.fisei.visitapp.app.adapter;

import android.app.Activity;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.fisei.visitapp.app.Fragment.PracticeList_Fragment;
import com.fisei.visitapp.app.R;
import com.fisei.visitapp.app.entity.Estudiante;
import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by diegoztc on 19/02/15.
 */
public class AdapterEstudiantes extends ArrayAdapter {
// Hacemos que nuestra clase herede las características de un ArrayAdapter

    Activity context;
    NameValuePair[] datos;
    List<Estudiante> listaEstudiantes;

    /* Creamos las variables necesarias para capturar el contexto
    *  y los datos que se publicarán en la lista
    */


    public AdapterEstudiantes(Activity context, List<Estudiante> lista) {
        super(context, R.layout.listview_estudiantes,lista);
        this.context=context;
        this.listaEstudiantes=lista;
        // TODO Auto-generated constructor stub
    }



    /* Constructor de la clase, donde pasamos por parámetro los datos
     * a mostrar en la lista y el contexto
    */
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Rescatamos cada item del listview y lo inflamos con nuestro layout
        View item = convertView;
        item = context.getLayoutInflater().inflate(R.layout.listview_estudiantes, null);

        Estudiante est= listaEstudiantes.get(position);


        TextView cedula=(TextView) item.findViewById(R.id.txtListCedula);
        cedula.setText(est.getCCEstudiante());

        TextView nombre=(TextView) item.findViewById(R.id.txtListNombre);
        nombre.setText(est.getNombre()+" "+est.getApellido());

        return item;
    }
}

