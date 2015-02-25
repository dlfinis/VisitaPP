package com.fisei.visitapp.app;

/**
 * Created by diegoztc on 21/02/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.fisei.visitapp.app.database.DatabaseManager;


import java.util.List;


public class SeleccionVisitaActivity extends Activity {


    private List<String> getListaEstudiantesCedula(){
        List<String> listaEstudiantes = DatabaseManager.getInstance().getAllEstudianteCedula();
        return listaEstudiantes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_visita);

        DatabaseManager.init(this);

        Spinner spinner1 = (Spinner) findViewById(R.id.spnSVCarrera);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,getListaEstudiantesCedula());
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter);



        Spinner spinner2 = (Spinner) findViewById(R.id.spnSVEstudiante);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.estudiante, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter1);

        Button btnSVAceptar =(Button)findViewById(R.id.btnSVAceptar);
        btnSVAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent visitaIntent = new Intent(SeleccionVisitaActivity.this, VisitaSistemasActivity.class);
                startActivity(visitaIntent);
            }
        });

        Button btnSVCancelar =(Button)findViewById(R.id.btnSVCancelar);
        btnSVCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }


}
