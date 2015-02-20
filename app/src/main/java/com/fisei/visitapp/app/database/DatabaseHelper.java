package com.fisei.visitapp.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.fisei.visitapp.app.R;
import com.fisei.visitapp.app.entity.Estudiante;
import com.fisei.visitapp.app.entity.Test;
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


    private static final String DATABASE_NAME = "sppp.db";
    private static final String DATABASE_PATH = "/data/data/com.fisei.visitapp.app/databases/";
    private static final int DATABASE_VERSION =1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_PATH+DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);

        boolean dbexist = checkdatabase();
        if (!dbexist) {

            // If database did not exist, try copying existing database from assets folder.
            try {
                InputStream myinput = context.getAssets().open(DATABASE_NAME);
                String outfilename = DATABASE_PATH + DATABASE_NAME;
                Log.i(DatabaseHelper.class.getName(), "DB Path : " + outfilename);
                OutputStream myoutput = new FileOutputStream(outfilename);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myinput.read(buffer)) > 0) {
                    myoutput.write(buffer, 0, length);
                }
                myoutput.flush();
                myoutput.close();
                myinput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*
    * Check whether or not database exist
    */
    private boolean checkdatabase() {
        boolean checkdb = false;

        String myPath = DATABASE_PATH + DATABASE_NAME;
        File dbfile = new File(myPath);
        checkdb = dbfile.exists();

        Log.i(DatabaseHelper.class.getName(), "DB Exist : " + checkdb);

        return checkdb;
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




}
