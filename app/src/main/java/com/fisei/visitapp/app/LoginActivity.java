package com.fisei.visitapp.app;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.fisei.visitapp.app.database.DatabaseManager;

import com.fisei.visitapp.app.database.DatabaseManagerPGSQL;
import com.fisei.visitapp.app.entity.ResponsableIngreso;

import java.sql.*;

import static android.widget.Toast.LENGTH_SHORT;


public class LoginActivity extends Activity {


    EditText edtLCedula;
    EditText edtLClave;

    Button  btnLAceptar;
    Button  btnLCancelar;

    Bundle args = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DatabaseManager.init(this);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        btnLAceptar = (Button) findViewById(R.id.btnLAceptar);
        btnLCancelar = (Button) findViewById(R.id.btnLCancelar);

        edtLCedula = (EditText) findViewById(R.id.edtLCedula);
        edtLClave = (EditText) findViewById(R.id.edtLClave);

        //setOnClicks();

        DBLoad db = new DBLoad();
        db.execute();
        goHome();
    }

    class DBLoad extends AsyncTask<Void, Integer, Void>
    {
        Connection con = null;

        protected void onPreExecute()
        {           super.onPreExecute();
            //display progressdialog.
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        protected Void doInBackground(Void... params) {
            try {
                Log.e("Timestamp Test",
                        DatabaseManagerPGSQL.getInstance().queryScalar("Select current_timestamp").toString());


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
        }



        protected void onPostExecute(Void... params)
        {
        }

    }

    protected  void goHome()
    {

        Intent inicioIntent = new Intent(LoginActivity.this, HomeActivity.class);
        inicioIntent.putExtra("ccResponsable","0705756187");
        inicioIntent.putExtra("nomCompletoResponsable",
                "Silvia" + " " + "Escobar");

        startActivity(inicioIntent);



    }
    protected void setOnClicks()
    {

        btnLAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ResponsableIngreso responsableIngreso=
                        DatabaseManager.getInstance().getResponsableIngreso(edtLCedula.getText().toString());

                if(responsableIngreso!=null)
                {

                    Log.e("IngresoResponsable", responsableIngreso.toString()+"\n"+edtLClave.getText().toString());


                    if(responsableIngreso.getClave().equals(edtLClave.getText().toString().trim()))
                    {
                        Toast.makeText(getApplicationContext(),"Bienvenido...",Toast.LENGTH_LONG).show();

                        Intent inicioIntent = new Intent(LoginActivity.this, HomeActivity.class);
                        inicioIntent.putExtra("ccResponsable", responsableIngreso.getCCResponsable());
                        inicioIntent.putExtra("nomCompletoResponsable",
                                responsableIngreso.getNombres()+" "+responsableIngreso.getApellidos());

                        startActivity(inicioIntent);

                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Clave no es válida",Toast.LENGTH_SHORT).show();
                    }


                }else
                {
                    Toast.makeText(getApplicationContext(),"Usuario no existe",LENGTH_SHORT).show();
                }

            }
        });

        btnLCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtLCedula.setText("");
                edtLClave.setText("");

            }
        });
    }




}
