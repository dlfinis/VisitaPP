package com.fisei.visitapp.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.fisei.visitapp.app.adapter.AdapterEstudiantes;
import com.fisei.visitapp.app.database.DatabaseManager;
import com.fisei.visitapp.app.dummy.Content;
import com.fisei.visitapp.app.entity.Estudiante;

import java.util.List;

/**
 * Created by diegoztc on 06/02/15.
 */
public class PracticeList_Fragment extends ListFragment {


        onEstudianteSelectedListener mCallback;

     // Interface que la Activity contenedora debe implementar
        // para poder tener comunicación
        public interface onEstudianteSelectedListener {
            public void onEstudianteSelected(String codeCC);
        }

         private List<Estudiante> getListaEstudiantes(){
             Bundle args = getArguments();
             if (args != null){
                String ccResponsable=args.getString("ccResponsable");
                 List<Estudiante> listaEstudiantes = DatabaseManager.getInstance().getAllEstudiante();
                 return listaEstudiantes;
             }else {
                 List<Estudiante> listaEstudiantes = DatabaseManager.getInstance().getAllEstudiante();
                 return listaEstudiantes;
             }



        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);
            DatabaseManager.init(getActivity());


            // Establecemos el Adapter cuando se crea el Fragment
//            setListAdapter(new ArrayAdapter<Estudiante>(getActivity(),
//                    android.R.layout.simple_list_item_1,ListaEstudiantes));

            setListAdapter(new AdapterEstudiantes(getActivity(),getListaEstudiantes()));


        }

        @Override
        public void onAttach(Activity activity) {
            // TODO Auto-generated method stub
            super.onAttach(activity);
            // Inicializamos nuestra variable de referencia del tipo
            // onEstudianteSelectedListener junto con el valor del objeto
            // activity que debe ser una Activity que implemente esta interface
            try {
                mCallback = (onEstudianteSelectedListener) activity;
            } catch (ClassCastException e) {
                Log.d("ClassCastException",
                        "La Activity debe implementar esta Interface");
            }
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            // TODO Auto-generated method stub
            super.onListItemClick(l, v, position, id);

            Estudiante est=(Estudiante)l.getItemAtPosition(position);
            // Llamamos al método que implementa la Activity pasandole
            // la posicion del elemento que hemos pulsado

            mCallback.onEstudianteSelected(est.getCCEstudiante());
        }


}
