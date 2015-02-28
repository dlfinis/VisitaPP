package com.fisei.visitapp.app.database;

/**
 * Created by diegoztc on 19/02/15.
 */

import android.util.Log;
import org.postgresql.util.PSQLException;

import java.sql.*;

public class DatabaseManagerPGSQL {


    public Connection conn;
    private Statement statement;

    //private static  String direccion = "jdbc:postgresql://192.168.56.1:5432/";
    private static  String direccion = "";
    private static  String url = "jdbc:postgresql://%s:%d/%s";

    private static  int puerto = 0;

    private static  String bd = "sppp";
    private static  String usuario = "postgres";
    private static  String clave = "postgres";




    static private DatabaseManagerPGSQL instance=null;


    public static void setDireccion(String direccion) {
        DatabaseManagerPGSQL.direccion = direccion;
    }


    public static String getUrl() {
        return String.format("jdbc:postgresql://%s:%d/%s",direccion,puerto,bd);
    }

    public static void setPuerto(int puerto) {
        DatabaseManagerPGSQL.puerto = puerto;
    }

    public static void setBd(String bd) {
        DatabaseManagerPGSQL.bd = bd;
    }

    public static void setUsuario(String usuario) {
        DatabaseManagerPGSQL.usuario = usuario;
    }

    public static void setClave(String clave) {
        DatabaseManagerPGSQL.clave = clave;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static String getClave() {
        return clave;
    }

    /**
     *
     * @return PostgreSQL Connection object
     */
    static public DatabaseManagerPGSQL getInstance() {
        if ( instance == null ) {

            instance = new DatabaseManagerPGSQL();
        }
        return instance;
    }

    public static void setInstance(DatabaseManagerPGSQL instance) {
        DatabaseManagerPGSQL.instance = instance;
    }

    protected DatabaseManagerPGSQL() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(getUrl(),getUsuario(),getClave());
        }catch (Exception sqle) {
            sqle.printStackTrace();

        }
    }



    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException{
        statement = instance.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet res = statement.executeQuery(query);
        return res;
    }
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public int insert(String insertQuery) throws SQLException {
        statement = instance.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;

    }


    /**
     * @desc Method to getPreparedStatement
     * @param query String
     * @return boolean
     * @throws SQLException
     */
    public PreparedStatement getPreparedStatement(String query ) throws SQLException {
        return instance.conn.prepareStatement(query);
    }
    /**
     * @desc Method to insert data to a table
     * @param pst PreparedStatemenet The Insert query
     * @return integer
     * @throws SQLException
     */
    public int insertPrepared(PreparedStatement pst) throws SQLException {
        int result = pst.executeUpdate();
        return result;

    }

    /**
     * @desc Method to insert data to a table
     * @param selectQuery String The select query
     * @return boolean
     * @throws SQLException
     */
    public Object queryScalar(String selectQuery) throws SQLException {
        statement = instance.conn.createStatement();
        ResultSet result = statement.executeQuery(selectQuery);
        result.next();
        return result.getObject(1);

    }


    /**
     * @desc Method to get if exists somenthing
     * @param selectQuery String The select query
     * @return boolean
     * @throws SQLException
     */
    public Boolean queryExits(String selectQuery) throws SQLException {
        statement = instance.conn.createStatement();
        ResultSet result = statement.executeQuery(selectQuery);
        result.next();
        return result.getBoolean(1);

    }






   public static Connection getConnection() throws Exception {

        String url = "jdbc:postgresql://192.168.56.1:5432/";
        String dbName = "sppp";
        String driver = "org.postgresql.jdbc.Driver";
        String userName = "postgresql";
        String password = "postgresql";

        Class.forName(driver).newInstance();
        Connection conn = DriverManager.getConnection(url + dbName, userName,password);

        return conn;
    }




    public void testDB() {

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(direccion, usuario, clave);
            /* System.out.println("Database connection success"); */

            String result = "Database connection success\n";
            Log.e("Connection Database",result);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select current_timestamp");

            Log.e("Test SQL",rs.getString(0));
            //ResultSetMetaData rsmd = rs.getMetaData();

//            while(rs.next()) {
//                result += rsmd.getColumnName(1) + ": " + rs.getInt(1) + "\n";
//                result += rsmd.getColumnName(2) + ": " + rs.getString(2) + "\n";
//                result += rsmd.getColumnName(3) + ": " + rs.getString(3) + "\n";
//            }

        }
        catch(Exception e) {
            e.printStackTrace();

        }



    }


}

