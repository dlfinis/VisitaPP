package com.fisei.visitapp.app;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.fisei.visitapp.app.Fragment.PracticeContent_Fragment;
import com.fisei.visitapp.app.Fragment.PracticeList_Fragment;


public class PracticeActivity extends FragmentActivity implements PracticeList_Fragment.onTitleSelectedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
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
    public void onTitleSelected(int position) {
// TODO Auto-generated method stub
// Comprobamos si tenemos disponible el Fragment de
// contenido
        PracticeContent_Fragment contFragment = (PracticeContent_Fragment) getSupportFragmentManager()
                .findFragmentById(R.id.tvContenido);
        if (contFragment != null) {
// Si está disponible, estamos en la versión de 2 paneles
            contFragment.updateContent(position);
        } else {
// Si no está disponible, estamos en el layout
// del FrameLayout, y tenemos que cambiar los Fragment
            contFragment = new PracticeContent_Fragment();
            Bundle args = new Bundle();
// Establecemos la posición que hemos elegido
            args.putInt(PracticeContent_Fragment.POSITION, position);
            contFragment.setArguments(args);
// Reemplazamos el Fragment que había por el nuevo
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, contFragment)
                    .addToBackStack(null).commit();
        }
    }

}