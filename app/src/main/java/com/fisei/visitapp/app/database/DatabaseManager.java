package com.fisei.visitapp.app.database;

/**
 * Created by diegoztc on 19/02/15.
 */
import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import android.util.Log;
import com.fisei.visitapp.app.entity.*;
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



    public void addResponsableIngreso(ResponsableIngreso resp) {
        try {
            getHelper().getResponsableIngresoDao().create(resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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


    public ResponsableIngreso getResponsableIngresoById(int id) {
        ResponsableIngreso test =new ResponsableIngreso();
        try {
            test= (ResponsableIngreso) getHelper().getRuntimeResponsableIngresoDao().queryForId(id);
        } catch (SQLException e) {
            Log.e("ID ResponsableIngreso", e.getMessage());
            e.printStackTrace();
        }
        return test;
    }

    public ResponsableIngreso getResponsableIngreso(String cc) {
        ResponsableIngreso test =null;
        try {

            QueryBuilder<ResponsableIngreso, Integer> qb =
                    getHelper().getRuntimeResponsableIngresoDao().queryBuilder();

            qb.where().eq("CCResponsable", cc);
            PreparedQuery<ResponsableIngreso> pq = qb.prepare();
            test= getHelper().getRuntimeResponsableIngresoDao().queryForFirst(pq);
        } catch (SQLException e) {
            Log.e("ID Responsable Inicio", e.getMessage());
            e.printStackTrace();
        }
        return test;
    }


    public void addEstudianteInformacion(EstudianteInformacion est) {
        try {
            getHelper().getEstudianteInformacionDao().create(est);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public EstudianteInformacion getEstudianteInformacionById(int id) {
        EstudianteInformacion test = null;
        try {
            test= (EstudianteInformacion) getHelper().getRuntimeEstudianteInformacionDao().queryForId(id);
        } catch (SQLException e) {
            Log.e("ID EstudianteInformacion", e.getMessage());
            e.printStackTrace();
        }
        return test;
    }

    public EstudianteInformacion getEstudianteInformacion(String cc) {
        EstudianteInformacion test =new EstudianteInformacion();
        try {

            QueryBuilder<EstudianteInformacion, Integer> qb =
                    getHelper().getRuntimeEstudianteInformacionDao().queryBuilder();

            qb.where().eq("CCEstudiante", cc);
            PreparedQuery<EstudianteInformacion> pq = qb.prepare();
            test= getHelper().getRuntimeEstudianteInformacionDao().queryForFirst(pq);
        } catch (SQLException e) {
            Log.e("Estudiante Info", e.getMessage());
            e.printStackTrace();
        }
        return test;
    }

    public EstudianteInformacion getEstudianteInformacion(int id) {
        EstudianteInformacion test = new EstudianteInformacion();
        try {
            test = (EstudianteInformacion) getHelper().getRuntimeEstudianteInformacionDao().queryForId(id);
        } catch (SQLException e) {
            Log.e("ID EstudianteInformacion", e.getMessage());
            e.printStackTrace();
        }
        return test;
    }

    
    public List<String> getAllEstudiantesCC() {
        List<String> testList = null;
        try {
            QueryBuilder<EstudianteInformacion, Integer> qb =
                    getHelper().getRuntimeEstudianteInformacionDao().queryBuilder();

            qb.selectColumns("CCEstudiante");



            final GenericRawResults<String> rawResults =
                    getHelper().getRuntimeEstudianteInformacionDao().queryRaw(qb.prepareStatementString(), new RawRowMapper<String>() {
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
    
    

    public List<EstudianteInformacion> getAllEstudiantesInformacion() {
        List<EstudianteInformacion> testList = null;
        try {
            testList= getHelper().getRuntimeEstudianteInformacionDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }

    public List<EstudianteInformacion> getAllEstudiantesInformacionByResponsable(String ccResponsable) {
        List<EstudianteInformacion> testList = null;
        try {

            QueryBuilder<EstudianteInformacion, Integer> qb =
                    getHelper().getRuntimeEstudianteInformacionDao().queryBuilder();

            qb.where().eq("CCResponsable", ccResponsable);
            PreparedQuery<EstudianteInformacion> pq = qb.prepare();
            testList= getHelper().getRuntimeEstudianteInformacionDao().query(pq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }


    public List<EstudianteInformacion> getAllEstudiantesInformacionByProceso(String ccResponsable,String proceso) {
        List<EstudianteInformacion> testList = null;
        try {

            List<PasantiaPracticas> lpst=getAllPasantiaPracticas();

            for (PasantiaPracticas pst :lpst)
            {

                if(pst.getEstado().equals(proceso))
                {
                    testList.add(getEstudianteInformacion(pst.getCodEstudiante()));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return testList;
    }





    public void addPasantiaPracticas(PasantiaPracticas ppt) {
        try {
            getHelper().getPasantiaPracticasDao().create(ppt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public VisitaPractica getVisitaPracticaById(int id) {
        VisitaPractica test = null;
        try {
            test= (VisitaPractica) getHelper().getRuntimeVisitaPracticaDao().queryForId(id);
        } catch (SQLException e) {
            Log.e("ID VisitaPractica", e.getMessage());
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

    public PasantiaPracticas getLastPasantiaPracticasByEstudiante(int codEstudiante) {

        PasantiaPracticas test=null;
        try {

            QueryBuilder<PasantiaPracticas, Integer> qb =
                    getHelper().getRuntimePasantiaPracticasDao().queryBuilder();

            qb.where().eq("CodEstudiante", codEstudiante);
            qb.orderBy("CodPractica",false).limit(1L);


            PreparedQuery<PasantiaPracticas> pq = qb.prepare();

            test= (PasantiaPracticas) getHelper().getRuntimePasantiaPracticasDao().query(pq);
        } catch (SQLException e) {
            Log.e("PasantiasPracticas", e.getMessage());
            e.printStackTrace();
        }
        return test;
    }


    public void addVisitaPractica(VisitaPractica vsp) {
        try {
            getHelper().getVisitaPracticaDao().create(vsp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PasantiaPracticas getPasantiaPracticasById(int id) {
        PasantiaPracticas test = null;
        try {
            test= (PasantiaPracticas) getHelper().getRuntimePasantiaPracticasDao().queryForId(id);
        } catch (SQLException e) {
            Log.e("ID PasantiaPracticas", e.getMessage());
            e.printStackTrace();
        }
        return test;
    }

    public List<VisitaPractica> getAllVisitaPractica() {
        List<VisitaPractica> testList = null;
        try {
            testList= getHelper().getRuntimeVisitaPracticaDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }

    public List<VisitaPractica> getAllVisitaPracticaByPasantia(int codPractica) {

        List<VisitaPractica> testList = null;
        try {

            QueryBuilder<VisitaPractica, Integer> qb =
                    getHelper().getRuntimeVisitaPracticaDao().queryBuilder();

            qb.where().eq("CodPractica", codPractica);
            PreparedQuery<VisitaPractica> pq = qb.prepare();

            testList= getHelper().getRuntimeVisitaPracticaDao().query(pq);

        } catch (SQLException e) {
            Log.e("Visita Practicas", e.getMessage());
            e.printStackTrace();
        }
        return testList;
    }

}