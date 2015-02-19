package com.fisei.visitapp.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.fisei.visitapp.app.R;
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
    private static final String DATABASE_NAME ="sppp";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_PATH = "/com/fisei/visitapp/app/database/";


    //Objetos DAO
    private Dao<Test,Integer> testDAO = null;
    private RuntimeExceptionDao<Test,Integer> testRuntimeDAO=null;


    public Dao<Test, Integer> getTestDAO(Class<Test> testClass) throws SQLException {
        if(testDAO==null) testDAO=getTestDAO(Test.class);
        return testDAO;
    }


    public RuntimeExceptionDao<Test, Integer> getTestRuntimeDAO(Class<Test> testClass) throws SQLException{
        if(testRuntimeDAO==null) testRuntimeDAO = getTestRuntimeDAO(Test.class);
        return testRuntimeDAO;
    }

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
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            Log.e(DatabaseHelper.class.getSimpleName(), "onCreate()");
            TableUtils.createTable(connectionSource,Test.class);
        }catch (SQLException ex){
            Log.e(DatabaseHelper.class.getSimpleName(), "Don't load",ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.e(DatabaseHelper.class.getSimpleName(), "onCreate()");
            TableUtils.dropTable(connectionSource, Test.class, true);
        }catch (SQLException ex){
            Log.e(DatabaseHelper.class.getSimpleName(), "Don't load",ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void close() {
        super.close();
        testDAO=null;
        testRuntimeDAO=null;
    }
}
