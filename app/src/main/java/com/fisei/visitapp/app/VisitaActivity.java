package com.fisei.visitapp.app;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fisei.visitapp.app.Fragment.VisitaContent_Fragment;
import com.fisei.visitapp.app.Fragment.VisitaList_Fragment;


public class VisitaActivity extends FragmentActivity implements VisitaList_Fragment.onEstudianteSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visita);



        //args.putString("ccResponsable",ccResponsable);
        // Comprobamos si estamos usando la version con
        // con el FrameLayout
        if (findViewById(R.id.fragment_container_visita) != null) {
            if (savedInstanceState != null) {
                return;
            }
            // Establecemos el ListFragment en el caso de que sea la version
            // de un panel (SmartPhone)
            VisitaList_Fragment fragment = new VisitaList_Fragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_visita, fragment, null).commit();
        }
    }
    @Override
    public void onEstudianteSelected(String ccEstudiante) {

        SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        // TODO Auto-generated method stub
        // Comprobamos si tenemos disponible el Fragment de
        // contenido
        VisitaContent_Fragment contFragment = (VisitaContent_Fragment) getSupportFragmentManager()
                .findFragmentById(R.id.scvSistemas);
        if (contFragment != null) {
            // Si está disponible, estamos en la versión de 2 paneles
            contFragment.updateContent(ccEstudiante);
        } else {
            // Si no está disponible, estamos en el layout
            // del FrameLayout, y tenemos que cambiar los Fragment
            contFragment = new VisitaContent_Fragment();
            Bundle args = new Bundle();


            args.putString("ccResponsable",sharedPrefs.getString("prefCC","00"));

            // Establecemos la posición que hemos elegido
            args.putString(VisitaContent_Fragment.CODECC, ccEstudiante);
            contFragment.setArguments(args);

            // Reemplazamos el Fragment que había por el nuevo
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_visita, contFragment)
                    .addToBackStack(null).commit();
        }
    }

}
