package com.fisei.visitapp.app.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.fisei.visitapp.app.SettingsActivity;
import com.fisei.visitapp.app.entity.EstudianteInformacion;
import com.fisei.visitapp.app.entity.PasantiaPracticas;
import com.fisei.visitapp.app.entity.ResponsableIngreso;
import com.fisei.visitapp.app.entity.VisitaPractica;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by diegoztc on 26/02/15.
 */
public class PgsqlDataAsyncTask  {

    public PgsqlDataAsyncTask() {

    }

    static private PgsqlDataAsyncTask instance;

    static public PgsqlDataAsyncTask getInstance() {

        if ( instance == null ) {
            instance = new PgsqlDataAsyncTask();
        }

        return instance;
    }




    
    public DataBaseCheck getDataBaseCheck()
    {
        return new DataBaseCheck();

    }

    public UserCheck getUserCheck(Activity activity)
    {
        return new UserCheck(activity);

    }


    public TEstudianteInformacion getTEstudianteInformacion(Activity activity)
    {
        return new TEstudianteInformacion(activity);

    }

    public TPasantiaPracticas getTPasantiaPracticas(Activity activity)
    {
        return new TPasantiaPracticas(activity);

    }
    
    public TVisitaPractica getTVisitaPractica(Activity activity)
    {
        return new TVisitaPractica(activity);
    }

    public TVisitaPracticaBD getTVisitaPracticaBD(Activity activity)
    {
        return new TVisitaPracticaBD(activity);
    }

    public TResponsableIngreso getTResponsableIngreso(Activity activity)
    {
        return new TResponsableIngreso(activity);

    }

    /**
     * Class to check User if exists in the database
     */
    public class DataBaseCheck extends AsyncTask<Void, Integer, Boolean>
    {
        @Override
        protected Boolean doInBackground(Void... voids) {


            try {
                Log.e("TTest1",DatabaseManagerPGSQL.getInstance().queryScalar("Select current_timestamp").toString());
                return true;

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
                Log.e("Result Connection","true");
            else
                Log.e("Result Connection","false");

        }
    }


    /**
     * Class to check User if exists in the database
     */
    public class UserCheck extends AsyncTask<Map<String,String>, Integer, Boolean>
    {
        String query="select exists(select * from vw_responsable_ingreso \n" +
                "where \"CCResponsable\"='%s' and\n" +
                "\"Clave\"='%s')";
        Context context;

        public UserCheck(Activity activity) {
            this.context=activity.getApplicationContext();
        }

        @Override
        protected Boolean doInBackground(Map<String, String>... userMap) {

            try {

                query=String.format(query,userMap[0].get("CCResponsable"),userMap[0].get("ClaveResponsable"));

                if(DatabaseManagerPGSQL.getInstance().queryExits(query)) {
                    return true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            if(!result)
            {
                Toast.makeText(context, "Conexión no Valida!\nRevise su número y clave", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        protected void onCancelled() {
            Toast.makeText(context, "Tarea cancelada!", Toast.LENGTH_SHORT).show();
        }




    }

    /**
     * Class to load data of view vw_estudiante_informacion
     */
    public class TEstudianteInformacion extends AsyncTask<String, Integer, Boolean>
    {
        String query="select * from vw_estudiante_informacion\n" +
                "where \"CCResponsable\"='%s'\n";

        String queryMaxId="select max(\"CodEstudiante\") from vw_estudiante_informacion\n" +
                "where \"CCResponsable\"='%s'\n";

        ProgressDialog progressDialog;
        Context context;
        Activity activity;

        public TEstudianteInformacion(Activity activity) {
            this.context=activity;
            this.activity=activity;

        }

        @Override
        protected Boolean doInBackground(String... ccResponsable) {

            try {

                query=String.format(query,ccResponsable[0]);
                queryMaxId=String.format(query,ccResponsable[0]);

                ResultSet resultSet=DatabaseManagerPGSQL.getInstance().query(query);


                resultSet.last();
                int size=resultSet.getRow();
                resultSet.beforeFirst();

                int factor=(int)size/100;
                factor= factor<1?1:factor;

                int cn=0;
                while (resultSet.next()) {

                    boolean est_exists=
                            DatabaseManager.getInstance().
                                    getEstudianteInformacionById(resultSet.getInt("CodEstudiante")) != null;

                    //Log.e("est",est_exists+" "+cn+" "+factor+" "+size);
                    if(!est_exists) {
                        EstudianteInformacion est = new EstudianteInformacion();


                        est.setCodEstudiante(resultSet.getInt("CodEstudiante"));
                        est.setCCEstudiante(resultSet.getString("CCEstudiante"));
                        est.setNombres(resultSet.getString("Nombres"));
                        est.setApellidos(resultSet.getString("Apellidos"));
                        est.setSexo(resultSet.getString("Sexo"));
                        est.setConvencional(resultSet.getString("Convencional"));
                        est.setMovil(resultSet.getString("Movil"));
                        est.setEmail(resultSet.getString("Email"));
                        est.setNumCreditos(resultSet.getInt("NumCreditos"));
                        est.setCarrera(resultSet.getString("Carrera"));
                        est.setCodPasantia(resultSet.getInt("CodPasantia"));
                        est.setHorasPasantias(resultSet.getInt("HorasPasantias"));
                        est.setNumPracticas(resultSet.getInt("NumPracticas"));
                        est.setCodResponsable(resultSet.getInt("CodResponsable"));
                        est.setCCResponsable(resultSet.getString("CCResponsable"));

                        DatabaseManager.getInstance().addEstudianteInformacion(est);
                    }
                    cn+=factor;

                    for(int i=0;i<100;i++)
                    {
                        publishProgress(i);
                    }




                    if(isCancelled())
                        break;
                    return true;

                }

                return true;

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();
            progressDialog.setProgress(progreso);
        }
        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(activity);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    TEstudianteInformacion.this.cancel(true);
                }
            });
            progressDialog.setMessage("Alfa");
            progressDialog.setProgress(0);
            progressDialog.show();
        }
        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                progressDialog.dismiss();
            }
            else
            {
                progressDialog.dismiss();
                Toast.makeText(context, "Error\n De carga de datos de Estudiantes.", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        protected void onCancelled() {
            Toast.makeText(context, "Tarea cancelada!", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Class to load data of view vw_estudiante_pasantias_informacion
     */
    public class TPasantiaPracticas extends AsyncTask<String, Integer, Boolean>
    {
        String query="select * from  vw_estudiante_pasantias_informacion " +
                "where \"CCResponsable\" ='%s'";



        Context context;

        public TPasantiaPracticas(Activity activity) {

            this.context=activity.getApplicationContext();
        }

        @Override
        protected Boolean doInBackground(String... ccResponsable) {

            try {

                query=String.format(query,ccResponsable[0]);

                ResultSet resultSet=DatabaseManagerPGSQL.getInstance().query(query);


                resultSet.last();
                int size=resultSet.getRow();
                resultSet.beforeFirst();

                int factor=(int)size/100;
                factor= factor<1?1:factor;

                int cn=0;
                while (resultSet.next()) {


                    boolean ppt_exists=
                            DatabaseManager.getInstance().
                                    getPasantiaPracticasById(resultSet.getInt("CodPractica")) != null;

                    if(!ppt_exists) {
                        PasantiaPracticas ppt = new PasantiaPracticas();

                        ppt.setCodPractica(resultSet.getInt("CodPractica"));
                        ppt.setCodPasantia(resultSet.getInt("CodPasantia"));
                        ppt.setCodEstudiante(resultSet.getInt("CodEstudiante"));
                        ppt.setCodResponsable(resultSet.getInt("CodResponsable"));
                        ppt.setCodEstudiante(resultSet.getInt("CodEstudiante"));
                        ppt.setCCResponsable(resultSet.getString("CCResponsable"));
                        ppt.setEntidad(resultSet.getString("Entidad"));
                        ppt.setHorasPracticas(resultSet.getInt("HorasPractica"));
                        ppt.setEstado(resultSet.getString("Estado"));
                        ppt.setFechaInicio(resultSet.getString("FechaInicio"));
                        ppt.setFechaFin(resultSet.getString("FechaFin"));

                        DatabaseManager.getInstance().addPasantiaPracticas(ppt);

                    }
                    cn+=factor;
                    publishProgress(cn);


                    if(isCancelled())
                        break;

                }

                return true;

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();
        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {

            }
            else
            {
                Toast.makeText(context, "Error\n De carga de datos de Pasantias - Practicas.", Toast.LENGTH_SHORT).show();

            }
        }
        @Override
        protected void onCancelled() {
            Toast.makeText(context, "Tarea cancelada!", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Class to load data of view  vw_visita_practica
     */
    public class TVisitaPractica extends AsyncTask<String, Integer, Boolean>
    {
        String query="select * from  vw_visita_practica " +
                "where \"CCResponsable\" ='%s'";

        Context context;

        public TVisitaPractica(Activity activity) {

            this.context=activity.getApplicationContext();

        }

        @Override
        protected Boolean doInBackground(String... ccResponsable) {

            try {

                query=String.format(query,ccResponsable);

                ResultSet resultSet=DatabaseManagerPGSQL.getInstance().query(query);


                resultSet.last();
                int size=resultSet.getRow();
                resultSet.beforeFirst();

                int factor=(int)size/100;
                factor= factor<1?1:factor;

                int cn=0;
                while (resultSet.next()) {


                    boolean vspt_exists=
                            DatabaseManager.getInstance().
                                    getVisitaPracticaById(resultSet.getInt("CodVisita")) != null;

                    if(!vspt_exists) {
                        VisitaPractica vprac = new VisitaPractica();

                        vprac.setCodVisita(resultSet.getInt("CodVisita"));
                        vprac.setCodPractica(resultSet.getInt("CodPractica"));
                        vprac.setCCResponsable(resultSet.getString("CCResponsable"));
                        vprac.setObservaciones(resultSet.getString("Observaciones"));
                        vprac.setFechaVisita(resultSet.getString("FechaVisita"));
                        vprac.setOpcion1(resultSet.getBoolean("Opcion1"));
                        vprac.setOpcion2(resultSet.getBoolean("Opcion2"));
                        vprac.setOpcion3(resultSet.getBoolean("Opcion3"));
                        vprac.setOpcion4(resultSet.getBoolean("Opcion4"));
                        vprac.setOpcion5(resultSet.getBoolean("Opcion5"));
                        vprac.setOpcion6(resultSet.getBoolean("Opcion6"));
                        vprac.setOpcion7(resultSet.getBoolean("Opcion7"));
                        vprac.setOpcion8(resultSet.getBoolean("Opcion8"));

                        byte[] imgBytes = resultSet.getBytes("Imagen");

                        vprac.setImagen(imgBytes);

                        DatabaseManager.getInstance().addVisitaPractica(vprac);

                    }
                    cn+=factor;
                    publishProgress(cn);

                    if(isCancelled())
                        break;

                }

                return true;

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();
        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
            }
            else
            {
                Toast.makeText(context, "Error\n De carga de datos de Visita - Practica.", Toast.LENGTH_SHORT).show();

            }
        }
        @Override
        protected void onCancelled() {
            Toast.makeText(context, "Tarea cancelada!", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * Class to check User if exists in the database
     */
    public class VisitaPracticaCheck extends AsyncTask<Integer, Void , Boolean>
    {
        String query="select exists(select * from vw_visita_practica \n" +
                "where \"CodVisita\"='%d' ";
        Context context;

        @Override
        protected Boolean doInBackground(Integer... cod) {

            try {

                query=String.format(query,cod[0]);

                if(DatabaseManagerPGSQL.getInstance().queryExits(query)) {
                    return true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }


    /**
     * Class to load data of view  vw_visita_practica
     */
    public class TVisitaPracticaBD extends AsyncTask<Void, Integer, Boolean>
    {
        String query="select * from  vw_visita_practica " +
                "where \"CCResponsable\" ='%s'";

        String queryInsert="INSERT INTO visita_practica(\n" +
                "            cod_vist, cod_prac, observaciones, fecha_visita, opc1, opc2, \n" +
                "            opc3, opc4, opc5, opc6, opc7, opc8, imagen)\n" +
                "    VALUES (?, ?, ?, ?, ?, ?, \n" +
                "            ?, ?, ?, ?, ?, ?, ?);\n";


        final ProgressDialog progressDialog;
        Context context;

        public TVisitaPracticaBD(Activity activity) {


            this.context=activity.getApplicationContext();
            progressDialog =new ProgressDialog(context);
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {


                List<VisitaPractica> vstp = DatabaseManager.getInstance().getAllVisitaPractica();

                int size=vstp.size();

                int factor=(int)size/100;
                factor= factor<1?1:factor;

                int cn=0;
                boolean value=false;
                    for (VisitaPractica visitaPractica : vstp) {



                        value=new VisitaPracticaCheck().execute(visitaPractica.getCodVisita()).get();

                        if(!value)
                        {
                            PreparedStatement pst =
                                    DatabaseManagerPGSQL.getInstance().getPreparedStatement(queryInsert);
                            pst.setInt(1,visitaPractica.getCodVisita());
                            pst.setInt(2,visitaPractica.getCodPractica());
                            pst.setString(3,visitaPractica.getObservaciones());
                            pst.setString(4,visitaPractica.getFechaVisita());

                            pst.setBoolean(5,visitaPractica.isOpcion1());
                            pst.setBoolean(6,visitaPractica.isOpcion2());
                            pst.setBoolean(7,visitaPractica.isOpcion3());
                            pst.setBoolean(8,visitaPractica.isOpcion4());
                            pst.setBoolean(9,visitaPractica.isOpcion5());
                            pst.setBoolean(10,visitaPractica.isOpcion6());
                            pst.setBoolean(11,visitaPractica.isOpcion7());
                            pst.setBoolean(12,visitaPractica.isOpcion8());

                            InputStream imagenInputStream = new ByteArrayInputStream(visitaPractica.getImagen());
                            pst.setBinaryStream(13,imagenInputStream);

                            DatabaseManagerPGSQL.getInstance().insertPrepared(pst);

                            if(isCancelled())
                                break;

                            cn+=factor;
                            publishProgress(cn);
                        }

                    }






                return true;

            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }




            return false;


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();
            progressDialog.setProgress(progreso);
        }
        @Override
        protected void onPreExecute() {
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    TVisitaPracticaBD.this.cancel(true);
                }
            });
            progressDialog.setProgress(0);
            progressDialog.show();
        }
        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                progressDialog.dismiss();
            }
            else
            {
                progressDialog.dismiss();
                Toast.makeText(context, "Error\n De carga de datos de Visita - Practica.", Toast.LENGTH_SHORT).show();

            }
        }
        @Override
        protected void onCancelled() {
            Toast.makeText(context, "Tarea cancelada!", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * Class to load data of view  vw_responsable_ingreso
     */
    public class TResponsableIngreso extends AsyncTask<String, Integer, Boolean>
    {
        String query="select * from  vw_responsable_ingreso " +
                "where \"CCResponsable\" ='%s'";
        Context context;

        public TResponsableIngreso(Activity activity) {

            this.context=activity.getApplicationContext();
        }

        @Override
        protected Boolean doInBackground(String... ccResponsable) {

            try {

                query=String.format(query,ccResponsable[0]);

                ResultSet resultSet=DatabaseManagerPGSQL.getInstance().query(query);


                resultSet.last();
                int size=resultSet.getRow();
                resultSet.beforeFirst();

                int factor=(int)size/100;
                factor= factor<1?1:factor;

                int cn=0;
                while (resultSet.next()) {


                    boolean resp_exists=
                            DatabaseManager.getInstance().
                                    getResponsableIngresoById(resultSet.getInt("CodResponsable")) != null;

                    if(!resp_exists) {

                        ResponsableIngreso resp = new ResponsableIngreso();

                        resp.setCodResponsable(resultSet.getInt("CodResponsable"));
                        resp.setCCResponsable(resultSet.getString("CCResponsable"));
                        resp.setNombres(resultSet.getString("Nombres"));
                        resp.setApellidos(resultSet.getString("Apellidos"));
                        resp.setClave(resultSet.getString("Clave"));


                        DatabaseManager.getInstance().addResponsableIngreso(resp);
                    }

                    cn+=factor;

                    publishProgress(cn);



                    if(isCancelled())
                        break;

                }

                return true;

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();
        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
            }
            else
            {
                Toast.makeText(context, "Error\n De carga de datos de Usuario.", Toast.LENGTH_SHORT).show();

            }
        }
        @Override
        protected void onCancelled() {
            Toast.makeText(context, "Tarea cancelada!", Toast.LENGTH_SHORT).show();
        }

    }




}
