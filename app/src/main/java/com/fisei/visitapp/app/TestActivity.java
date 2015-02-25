package com.fisei.visitapp.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.fisei.visitapp.app.adapter.AdapterEstudiantes;
import com.fisei.visitapp.app.database.DatabaseHelper;
import com.fisei.visitapp.app.database.DatabaseManager;
import com.fisei.visitapp.app.entity.Estudiante;
import com.fisei.visitapp.app.entity.Test;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.*;


public class TestActivity extends Activity {

    private final String LOG_TAG = getClass().getSimpleName();


    EditText edtTPK;
    EditText edtTValue;
    Button btnTOk;
    Button btnTCancel;
    ListView lstTest;
    ImageView imgvImage;

    List<Estudiante> listaEstudiantes;
    // Creamos un adapter personalizado
    AdapterEstudiantes adapterEstudiantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_test);
        DatabaseManager.init(this);

        Log.i(LOG_TAG, "creating " + getClass() + " at " + System.currentTimeMillis());
        edtTPK = (EditText) findViewById(R.id.edtTPk);
        edtTValue = (EditText) findViewById(R.id.edtTValue);
        btnTOk = (Button) findViewById(R.id.btnTOk);
        btnTCancel = (Button) findViewById(R.id.btnTCancel);
        lstTest= (ListView) findViewById(R.id.lstTTest);
        imgvImage= (ImageView) findViewById(R.id.imvImagen);

        setListaEstudiantes(lstTest);
        //setListView(lstTest);
        //setInfo(edtTPK,edtTValue,imgvImage);
    }

    private void setInfo(EditText pk,EditText value,ImageView img)
    {
        Test test = DatabaseManager.getInstance().getTest(1);
        Log.e("Mensaje",test.toString()+"\n");
        value.setText(test.getValue());


    }



    private void setListView(ListView lstTest){
         List testList = DatabaseManager.getInstance().getAllEstudiante();


        List<String> titles = new ArrayList<String>();
        for (Object tl : testList) {
            titles.add(tl.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        lstTest.setAdapter(adapter);

         }

    private void setListaEstudiantes(ListView lstTest){


// Al adapter personalizado le pasamos el contexto y la lista que contiene
// Añadimos el adapter al listview


        listaEstudiantes =DatabaseManager.getInstance().getAllEstudiante();

        adapterEstudiantes= new AdapterEstudiantes(this,listaEstudiantes);

        lstTest.setAdapter(adapterEstudiantes);


         }






}
