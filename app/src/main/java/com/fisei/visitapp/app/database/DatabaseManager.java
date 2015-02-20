package com.fisei.visitapp.app.database;

/**
 * Created by diegoztc on 19/02/15.
 */
import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import android.util.Log;
import com.fisei.visitapp.app.entity.Estudiante;
import com.fisei.visitapp.app.entity.Test;

public class DatabaseManager {

    static private DatabaseManager instance;

    static public void init(Context ctx) {
        if (null==instance) {
            instance = new DatabaseManager(ctx);
        }
    }

    static public DatabaseManager getInstance() {
        return instance;
    }

    private DatabaseHelper helper;
    private DatabaseManager(Context ctx) {
        helper = new DatabaseHelper(ctx);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }


    public List<Test> getAllTest() {
        List<Test> testList = null;
        try {
            testList= getHelper().getRuntimeTestDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }
    public Test getTest(int id) {
        Test test =new Test();
        try {
           test=  getHelper().getRuntimeTestDao().queryForId(id);
        } catch (SQLException e) {
            Log.e("ID", e.getMessage());
            e.printStackTrace();
        }
        return test;
    }

    public List<Estudiante> getAllEstudiante() {
        List<Estudiante> testList = null;
        try {
            testList= getHelper().getRuntimeEstudianteDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }


    public Estudiante getEstudiante(int id) {
        Estudiante test =new Estudiante();
        try {
           test= (Estudiante) getHelper().getRuntimeEstudianteDao().queryForId(id);
        } catch (SQLException e) {
            Log.e("ID", e.getMessage());
            e.printStackTrace();
        }
        return test;
    }
}