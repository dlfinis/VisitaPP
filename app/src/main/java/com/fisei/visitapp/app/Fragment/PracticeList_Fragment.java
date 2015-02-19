package com.fisei.visitapp.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.fisei.visitapp.app.dummy.Content;

/**
 * Created by diegoztc on 06/02/15.
 */
public class PracticeList_Fragment extends ListFragment {


        onTitleSelectedListener mCallback;

        // Interface que la Activity contenedora debe implementar
        // para poder tener comunicación
        public interface onTitleSelectedListener {
            public void onTitleSelected(int position);
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);

            // Establecemos el Adapter cuando se crea el Fragment
            setListAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, Content.title));
        }

        @Override
        public void onAttach(Activity activity) {
            // TODO Auto-generated method stub
            super.onAttach(activity);
            // Inicializamos nuestra variable de referencia del tipo
            // onTituloSelectedListener junto con el valor del objeto
            // activity que debe ser una Activity que implemente esta interface
            try {
                mCallback = (onTitleSelectedListener) activity;
            } catch (ClassCastException e) {
                Log.d("ClassCastException",
                        "La Activity debe implementar esta Interface");
            }
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            // TODO Auto-generated method stub
            super.onListItemClick(l, v, position, id);

            // Llamamos al método que implementa la Activity pasandole
            // la posicion del elemento que hemos pulsado
            mCallback. onTitleSelected(position);
        }


}
