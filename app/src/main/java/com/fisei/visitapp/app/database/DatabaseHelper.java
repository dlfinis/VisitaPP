package com.fisei.visitapp.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import com.fisei.visitapp.app.R;
import com.fisei.visitapp.app.entity.*;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.*;
import java.sql.SQLException;



/**
 * Created by diegoztc on 19/02/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    final String state = Environment.getExternalStorageState();

    private static final String DATABASE_NAME = "sppp.db";

    private static final int DATABASE_VERSION =1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DatabaseInitializer initializer = new DatabaseInitializer(context);
        try {
            initializer.createDatabase();
            initializer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTableIfNotExists(connectionSource, Test.class);
            TableUtils.createTableIfNotExists(connectionSource, Estudiante.class);
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
            TableUtils.dropTable(connectionSource, Estudiante.class, true);
            onCreate(db);
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
    }

    //Objetos Dao

    private Dao<Test,Integer> objectTestDao=null;
    private RuntimeExceptionDao<Test,Integer> objectRuntimeTestDao=null;  
    
    
    private Dao<Estudiante,Integer> objectEstudianteDao=null;
    private RuntimeExceptionDao<Estudiante,Integer> objectRuntimeEstudianteDao=null;

    private Dao<ResponsableIngreso,Integer> objectResponsableIngresoDao=null;
    private RuntimeExceptionDao<ResponsableIngreso,Integer> objectRuntimeResponsableIngresoDao=null;
    
    private Dao<EstudianteInfo,Integer> objectEstudianteInfoDao=null;
    private RuntimeExceptionDao<EstudianteInfo,Integer> objectRuntimeEstudianteInfoDao=null;

    private Dao<PasantiaPracticas,Integer> objectPasantiaPracticasDao=null;
    private RuntimeExceptionDao<PasantiaPracticas,Integer> objectRuntimePasantiaPracticasDao=null;



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
    public Dao<Estudiante,Integer> getEstudianteDao() throws  SQLException{
        if(objectEstudianteDao ==null)
        {
            objectEstudianteDao=getDao(Estudiante.class);
        }
        return objectEstudianteDao;
    }
   public Dao<ResponsableIngreso,Integer> getResponsableIngresoDao() throws  SQLException{
        if(objectResponsableIngresoDao ==null)
        {
            objectResponsableIngresoDao=getDao(ResponsableIngreso.class);
        }
        return objectResponsableIngresoDao;
    } 
    
    public Dao<EstudianteInfo,Integer> getEstudianteInfoDao() throws  SQLException{
        if(objectEstudianteInfoDao ==null)
        {
            objectEstudianteInfoDao=getDao(EstudianteInfo.class);
        }
        return objectEstudianteInfoDao;
    }

    public Dao<PasantiaPracticas,Integer> getPasantiaPracticasDao() throws  SQLException{
        if(objectPasantiaPracticasDao ==null)
        {
            objectPasantiaPracticasDao=getDao(PasantiaPracticas.class);
        }
        return objectPasantiaPracticasDao;
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

    public RuntimeExceptionDao<Estudiante,Integer> getRuntimeEstudianteDao() throws  SQLException{
        if(objectRuntimeEstudianteDao ==null)
        {
            objectRuntimeEstudianteDao=getRuntimeExceptionDao(Estudiante.class);
        }
        return objectRuntimeEstudianteDao;
    }

    public RuntimeExceptionDao<ResponsableIngreso,Integer> getRuntimeResponsableIngresoDao() throws  SQLException{
        if(objectRuntimeResponsableIngresoDao ==null)
        {
            objectRuntimeResponsableIngresoDao=getRuntimeExceptionDao(ResponsableIngreso.class);
        }
        return objectRuntimeResponsableIngresoDao;
    }
    
    public RuntimeExceptionDao<EstudianteInfo,Integer> getRuntimeEstudianteInfoDao() throws  SQLException{
        if(objectRuntimeEstudianteInfoDao ==null)
        {
            objectRuntimeEstudianteInfoDao=getRuntimeExceptionDao(EstudianteInfo.class);
        }
        return objectRuntimeEstudianteInfoDao;
    }

    public RuntimeExceptionDao<PasantiaPracticas,Integer> getRuntimePasantiaPracticasDao() throws  SQLException{
        if(objectRuntimePasantiaPracticasDao ==null)
        {
            objectRuntimePasantiaPracticasDao=getRuntimeExceptionDao(PasantiaPracticas.class);
        }
        return objectRuntimePasantiaPracticasDao;
    }





}
