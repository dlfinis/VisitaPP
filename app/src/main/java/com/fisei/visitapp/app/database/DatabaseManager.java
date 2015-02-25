package com.fisei.visitapp.app.database;

/**
 * Created by diegoztc on 19/02/15.
 */
import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import android.util.Log;
import com.fisei.visitapp.app.entity.*;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

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


    public List<ResponsableIngreso> getAllResponsableIngreso() {
        List<ResponsableIngreso> testList = null;
        try {
            testList= getHelper().getRuntimeResponsableIngresoDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }


    public ResponsableIngreso getResponsableIngreso(int id) {
        ResponsableIngreso test =new ResponsableIngreso();
        try {
            test= (ResponsableIngreso) getHelper().getRuntimeResponsableIngresoDao().queryForId(id);
        } catch (SQLException e) {
            Log.e("ID Responsable Inicio", e.getMessage());
            e.printStackTrace();
        }
        return test;
    }

    public ResponsableIngreso getResponsableIngreso(String cc) {
        ResponsableIngreso test =new ResponsableIngreso();
        try {

            QueryBuilder<ResponsableIngreso, Integer> qb =
                    getHelper().getRuntimeResponsableIngresoDao().queryBuilder();

            qb.where().eq("Cedula", cc);
            PreparedQuery<ResponsableIngreso> pq = qb.prepare();
            test= getHelper().getRuntimeResponsableIngresoDao().queryForFirst(pq);
        } catch (SQLException e) {
            Log.e("ID Responsable Inicio", e.getMessage());
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

    public List<String> getAllEstudianteCedula() {
        List<String> testList = null;
        try {
            QueryBuilder<Estudiante, Integer> qb =
                    getHelper().getRuntimeEstudianteDao().queryBuilder();

            qb.selectColumns("CCEstudiante");



            final GenericRawResults<String> rawResults =
                    getHelper().getRuntimeEstudianteDao().queryRaw(qb.prepareStatementString(), new RawRowMapper<String>() {
                public String mapRow(String[] columnNames, String[] resultColumns) {
                    return resultColumns[0];
                }
            });
           testList = rawResults.getResults();
            testList.add(0, "");


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

    public EstudianteInfo getEstudianteInfo(String cc) {
        EstudianteInfo test =new EstudianteInfo();
        try {

            QueryBuilder<EstudianteInfo, Integer> qb =
                    getHelper().getRuntimeEstudianteInfoDao().queryBuilder();

            qb.where().eq("CC", cc);
            PreparedQuery<EstudianteInfo> pq = qb.prepare();
            test= getHelper().getRuntimeEstudianteInfoDao().queryForFirst(pq);
        } catch (SQLException e) {
            Log.e("Estudiante Info", e.getMessage());
            e.printStackTrace();
        }
        return test;
    }

    public List<PasantiaPracticas> getAllPasantiaPracticas() {
        List<PasantiaPracticas> testList = null;
        try {
            testList= getHelper().getRuntimePasantiaPracticasDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }

    public List<PasantiaPracticas> getAllPasantiaPracticasByPasantia(int codPasantia) {

        List<PasantiaPracticas> testList = null;
        try {

            QueryBuilder<PasantiaPracticas, Integer> qb =
                    getHelper().getRuntimePasantiaPracticasDao().queryBuilder();

            qb.where().eq("CodPasantia", codPasantia);
            PreparedQuery<PasantiaPracticas> pq = qb.prepare();

            testList= getHelper().getRuntimePasantiaPracticasDao().query(pq);
        } catch (SQLException e) {
            Log.e("Pasantias Practicas", e.getMessage());
            e.printStackTrace();
        }
        return testList;
      }

}