package com.fisei.visitapp.app.Fragment;

import android.widget.ListView;
import com.fisei.visitapp.app.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fisei.visitapp.app.adapter.AdapterEstudiantePracticas;
import com.fisei.visitapp.app.database.DatabaseManager;
import com.fisei.visitapp.app.entity.EstudianteInformacion;
import com.fisei.visitapp.app.entity.PasantiaPracticas;

import java.util.List;

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
        return inflater.inflate(R.layout.fragment_info_estudiante, container, false);
    }
    @Override
    public void onStart() {
    // TODO Auto-generated method stub
        super.onStart();
        DatabaseManager.init(getActivity());
    
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

        try {
            // Instanciamos el TextView y establecemos el contenido
            EstudianteInformacion est = DatabaseManager.getInstance().getEstudianteInformacion(codeCC);

            TextView txtENombre = (TextView) getActivity().findViewById(R.id.txtENombre);
            TextView txtECedula = (TextView) getActivity().findViewById(R.id.txtECedula);
            TextView txtEEmail = (TextView) getActivity().findViewById(R.id.txtEEmail);
            TextView txtECarrera = (TextView) getActivity().findViewById(R.id.txtECarrera);
            TextView txtENumCreditos = (TextView) getActivity().findViewById(R.id.txtENumCreditos);
            TextView txtEHorasPracticas = (TextView) getActivity().findViewById(R.id.txtEHorasPracticas);
            TextView txtENumPracticas = (TextView) getActivity().findViewById(R.id.txtENumPracticas);

            txtENombre.setText(est.getNombres());
            txtECedula.setText(codeCC);
            txtEEmail.setText(est.getEmail());
            txtECarrera.setText(est.getCarrera());
            txtENumCreditos.setText(String.valueOf(est.getNumCreditos()));
            txtEHorasPracticas.setText(String.valueOf(est.getHorasPasantias()));
            txtENumPracticas.setText(String.valueOf(est.getNumPracticas()));

            ListView lstEPracticas = (ListView) getActivity().findViewById(R.id.lstEPracticas);


            List<PasantiaPracticas> listaPracticas = DatabaseManager.getInstance().getAllPasantiaPracticasByPasantia(est.getCodPasantia());


            lstEPracticas.setAdapter(new AdapterEstudiantePracticas(getActivity(), listaPracticas));

        }catch (Exception ex)
        {
            ex.printStackTrace();

        }
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
