package com.fisei.visitapp.app.Fragment;

import com.fisei.visitapp.app.R;
import com.fisei.visitapp.app.dummy.Content;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by diegoztc on 06/02/15.
 */
public class PracticeContent_Fragment extends Fragment {

    public static final String CODECC = "CCEstudiante";
    String codeCC="0";
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    // Comprobamos si se recupera de un estado anterior
        if (savedInstanceState != null){
            codeCC = savedInstanceState.getString("CCEstudiante");
        }
        return inflater.inflate(R.layout.fragment_practice_content, container, false);
    }
    @Override
    public void onStart() {
    // TODO Auto-generated method stub
        super.onStart();
    
    // Comprobamos si tenemos argumentos
        Bundle args = getArguments();
        if (args != null){
    
    // Si tenemos argumentos, establecemos la posicion segun el codigo
            updateContent(args.getString(CODECC));
        }else if(!codeCC.equals("0")){
    
    // Si la variable de instancia es diferente a -1
    // quiere decir que nos hemos recuperado de un estado anterior
    // y actualizamos el contenido
            updateContent(codeCC);
        }
    }
    public void updateContent(String codeCC){

    // Instanciamos el TextView y establecemos el contenido
        TextView tvContenido = (TextView)getActivity().findViewById(R.id.tvContenido);
        tvContenido.setText("Alfa");

    // Guardamos el codigo del elemento que estamos consultando
        this.codeCC=codeCC;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
    // Guardamos el estado de la posicion del elemento
    // que estábamos consultando
        outState.putString(CODECC,codeCC);
    }

}
