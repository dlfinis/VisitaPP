package com.fisei.visitapp.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class HomeActivity extends ActionBarActivity {


    TextView txtNombre;
    Button btnHEstudiante;
    Button btnHVisita;
    Button btnHSalir;

    private static final int RESULT_SETTINGS = 1;


    Bundle args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       // showUserSettings();
        Intent iin = getIntent();
        args = iin.getExtras();

        txtNombre = (TextView) findViewById(R.id.txtHNombre);
        btnHEstudiante = (Button) findViewById(R.id.btnHEstudiante);
        btnHVisita = (Button) findViewById(R.id.btnHVisita);
        btnHSalir = (Button) findViewById(R.id.btnHSalir);

        if (args != null) {
            txtNombre.setText(args.getString("nomCompletoResponsable"));
            Log.e("codigo", args.getString("ccResponsable"));
        }

        btnHEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Estudiante...", Toast.LENGTH_LONG).show();

                Intent practicasIntent = new Intent(HomeActivity.this, PracticeActivity.class);
                if (args != null) {
                    practicasIntent.putExtra("ccResponsable", args.getString("ccResponsable"));

                }

                startActivity(practicasIntent);

            }
        });

        btnHVisita.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent seleccionIntent = new Intent(HomeActivity.this, SeleccionVisitaActivity.class);
                startActivity(seleccionIntent);
            }
        });


        btnHSalir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
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
               // showUserSettings();
                break;

        }

    }

    private void showUserSettings() {
        SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        StringBuilder builder = new StringBuilder();

        builder.append("\n CC: "
                + sharedPrefs.getString("prefCC", "NULL"));

        builder.append("\n Clave:"
                + sharedPrefs.getString("prefClave","NULL"));

        Log.e("Mensaje",builder.toString());

    }
}