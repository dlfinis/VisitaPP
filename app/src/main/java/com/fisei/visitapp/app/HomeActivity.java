package com.fisei.visitapp.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.fisei.visitapp.app.database.DatabaseManager;
import com.fisei.visitapp.app.entity.ResponsableIngreso;


public class HomeActivity extends ActionBarActivity {


    TextView txtNombre;
    Button btnHEstudiante;
    Button btnHVisita;
    Button btnHSalir;

    SharedPreferences sharedPrefs ;

    private static final int RESULT_SETTINGS = 1;


    Bundle args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DatabaseManager.init(this);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);



        txtNombre = (TextView) findViewById(R.id.txtHNombre);
        btnHEstudiante = (Button) findViewById(R.id.btnHEstudiante);
        btnHVisita = (Button) findViewById(R.id.btnHVisita);
        btnHSalir = (Button) findViewById(R.id.btnHSalir);

        showUserSettings();


        btnHEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent practicasIntent = new Intent(HomeActivity.this, PracticeActivity.class);
                if (args != null) {
                    practicasIntent.putExtra("ccResponsable",sharedPrefs.getString("prefCC","00"));

                }

                startActivity(practicasIntent);

            }
        });

        btnHVisita.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent seleccionIntent = new Intent(HomeActivity.this, VisitaActivity.class);
                startActivity(seleccionIntent);
            }
        });


        btnHSalir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                        finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home_configuracion:
                Intent i = new Intent(this,SettingsActivity.class);
                startActivityForResult(i, RESULT_SETTINGS);
                break;

        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SETTINGS:
                showUserSettings();
                break;

        }

    }

    private void showUserSettings() {

        if(sharedPrefs!=null) {
            StringBuilder builder = new StringBuilder();

            ResponsableIngreso responsableIngreso =
                    DatabaseManager.getInstance().getResponsableIngreso(sharedPrefs.getString("prefCC", "00"));

            if (responsableIngreso != null) {
                txtNombre.setText(responsableIngreso.getNombres() + " " + responsableIngreso.getApellidos());
                btnHEstudiante.setEnabled(true);
                btnHVisita.setEnabled(true);
            } else {
                txtNombre.setText("Usuario");
                btnHEstudiante.setEnabled(false);
                btnHVisita.setEnabled(false);
            }
        }else
        {
            txtNombre.setText("Usuario");
            btnHEstudiante.setEnabled(false);
            btnHVisita.setEnabled(false);
        }

    }
}