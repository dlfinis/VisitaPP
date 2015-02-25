package com.fisei.visitapp.app;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.fisei.visitapp.app.Fragment.PracticeContent_Fragment;
import com.fisei.visitapp.app.Fragment.PracticeList_Fragment;


public class PracticeActivity extends FragmentActivity implements PracticeList_Fragment.onEstudianteSelectedListener
{
    String ccResponsable="0705756187";
   Bundle arg_cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);


        Intent iin= getIntent();
        arg_cc = iin.getExtras();

        //args.putString("ccResponsable",ccResponsable);
        // Comprobamos si estamos usando la version con
        // con el FrameLayout
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
        // Establecemos el ListFragment en el caso de que sea la version
        // de un panel (SmartPhone)
            PracticeList_Fragment fragment = new PracticeList_Fragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, null).commit();
        }
    }
    @Override
    public void onEstudianteSelected(String ccEstudiante) {
        // TODO Auto-generated method stub
        // Comprobamos si tenemos disponible el Fragment de
        // contenido
        PracticeContent_Fragment contFragment = (PracticeContent_Fragment) getSupportFragmentManager()
                .findFragmentById(R.id.lnlEstudiante);
        if (contFragment != null) {
        // Si está disponible, estamos en la versión de 2 paneles
            contFragment.updateContent(ccEstudiante);
        } else {
        // Si no está disponible, estamos en el layout
        // del FrameLayout, y tenemos que cambiar los Fragment
            contFragment = new PracticeContent_Fragment();
            Bundle args = new Bundle();

            if(arg_cc!=null){
                args.putString("ccResponsable",arg_cc.getString("ccResponsable"));
            }
        // Establecemos la posición que hemos elegido
            args.putString(PracticeContent_Fragment.CODECC, ccEstudiante);
            contFragment.setArguments(args);

        // Reemplazamos el Fragment que había por el nuevo
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, contFragment)
                    .addToBackStack(null).commit();
        }
    }

}