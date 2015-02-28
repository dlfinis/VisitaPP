package com.fisei.visitapp.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import com.fisei.visitapp.app.R;
import com.fisei.visitapp.app.entity.*;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableUtils;

import java.io.*;
import java.sql.SQLException;



/**
 * Created by diegoztc on 19/02/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    protected AndroidConnectionSource connectionSource = new AndroidConnectionSource(this);

    final String state = Environment.getExternalStorageState();

    private static final String DATABASE_NAME = "sppp.db";

    private static final int DATABASE_VERSION =1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION,R.raw.ormlite_config);
//        DatabaseInitializer initializer = new DatabaseInitializer(context);
//        try {
//            initializer.createDatabase();
//            initializer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTableIfNotExists(connectionSource, Test.class);
            TableUtils.createTableIfNotExists(connectionSource, EstudianteInformacion.class);
            TableUtils.createTableIfNotExists(connectionSource, PasantiaPracticas.class);
            TableUtils.createTableIfNotExists(connectionSource, ResponsableIngreso.class);
            TableUtils.createTableIfNotExists(connectionSource, VisitaPractica.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Test.class, true);
            TableUtils.dropTable(connectionSource, EstudianteInformacion.class, true);
            TableUtils.dropTable(connectionSource, PasantiaPracticas.class, true);
            TableUtils.dropTable(connectionSource, VisitaPractica.class, true);
            TableUtils.dropTable(connectionSource, ResponsableIngreso.class, true);
            onCreate(db,connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public void close() {
        super.close();
        objectTestDao=null;
        objectRuntimeTestDao=null;
        objectEstudianteInformacionDao=null;
        objectPasantiaPracticasDao=null;
        objectResponsableIngresoDao=null;
        objectVisitaPracticaDao=null;

        objectRuntimeEstudianteInformacionDao=null;
        objectRuntimePasantiaPracticasDao=null;
        objectRuntimeResponsableIngresoDao=null;
        objectRuntimeVisitaPracticaDao=null;

    }

    //Objetos Dao

    private Dao<Test,Integer> objectTestDao=null;
    private RuntimeExceptionDao<Test,Integer> objectRuntimeTestDao=null;  
    
    

    private Dao<ResponsableIngreso,Integer> objectResponsableIngresoDao=null;
    private RuntimeExceptionDao<ResponsableIngreso,Integer> objectRuntimeResponsableIngresoDao=null;
    
    private Dao<EstudianteInformacion,Integer> objectEstudianteInformacionDao=null;
    private RuntimeExceptionDao<EstudianteInformacion,Integer> objectRuntimeEstudianteInformacionDao=null;

    private Dao<PasantiaPracticas,Integer> objectPasantiaPracticasDao=null;
    private RuntimeExceptionDao<PasantiaPracticas,Integer> objectRuntimePasantiaPracticasDao=null;

    private Dao<VisitaPractica,Integer> objectVisitaPracticaDao=null;
    private RuntimeExceptionDao<VisitaPractica,Integer> objectRuntimeVisitaPracticaDao=null;

    


    /**
     * Returns the Database Access Object (DAO) for our Test class. It will create it or just give the cached
     * value.
     */

    public Dao<Test, Integer> getTestDao() throws  SQLException{
        if(objectTestDao ==null)
        {
            objectTestDao=getDao(Test.class);
        }
        return objectTestDao;
    }

   public Dao<ResponsableIngreso,Integer> getResponsableIngresoDao() throws  SQLException{
        if(objectResponsableIngresoDao ==null)
        {
            objectResponsableIngresoDao=getDao(ResponsableIngreso.class);
        }
        return objectResponsableIngresoDao;
    } 
    
    public Dao<EstudianteInformacion,Integer> getEstudianteInformacionDao() throws  SQLException{
        if(objectEstudianteInformacionDao ==null)
        {
            objectEstudianteInformacionDao=getDao(EstudianteInformacion.class);
        }
        return objectEstudianteInformacionDao;
    }

    public Dao<PasantiaPracticas,Integer> getPasantiaPracticasDao() throws  SQLException{
        if(objectPasantiaPracticasDao ==null)
        {
            objectPasantiaPracticasDao=getDao(PasantiaPracticas.class);
        }
        return objectPasantiaPracticasDao;
    } 
    
    public Dao<VisitaPractica,Integer> getVisitaPracticaDao() throws  SQLException{
        if(objectVisitaPracticaDao ==null)
        {
            objectVisitaPracticaDao=getDao(VisitaPractica.class);
        }
        return objectVisitaPracticaDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our Estudiante class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */


    public RuntimeExceptionDao<Test,Integer> getRuntimeTestDao() throws  SQLException{
        if(objectRuntimeTestDao ==null)
        {
            objectRuntimeTestDao=getRuntimeExceptionDao(Test.class);
        }
        return objectRuntimeTestDao;
    }

    public RuntimeExceptionDao<ResponsableIngreso,Integer> getRuntimeResponsableIngresoDao() throws  SQLException{
        if(objectRuntimeResponsableIngresoDao ==null)
        {
            objectRuntimeResponsableIngresoDao=getRuntimeExceptionDao(ResponsableIngreso.class);
        }
        return objectRuntimeResponsableIngresoDao;
    }
    
    public RuntimeExceptionDao<EstudianteInformacion,Integer> getRuntimeEstudianteInformacionDao() throws  SQLException{
        if(objectRuntimeEstudianteInformacionDao ==null)
        {
            objectRuntimeEstudianteInformacionDao=getRuntimeExceptionDao(EstudianteInformacion.class);
        }
        return objectRuntimeEstudianteInformacionDao;
    }

    public RuntimeExceptionDao<PasantiaPracticas,Integer> getRuntimePasantiaPracticasDao() throws  SQLException{
        if(objectRuntimePasantiaPracticasDao ==null)
        {
            objectRuntimePasantiaPracticasDao=getRuntimeExceptionDao(PasantiaPracticas.class);
        }
        return objectRuntimePasantiaPracticasDao;
    } 
    
    public RuntimeExceptionDao<VisitaPractica,Integer> getRuntimeVisitaPracticaDao() throws  SQLException{
        if(objectRuntimeVisitaPracticaDao ==null)
        {
            objectRuntimeVisitaPracticaDao=getRuntimeExceptionDao(VisitaPractica.class);
        }
        return objectRuntimeVisitaPracticaDao;
    }





}
