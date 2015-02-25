package com.fisei.visitapp.app.database;

/**
 * Created by diegoztc on 19/02/15.
 */

import android.content.Context;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;



import java.sql.SQLException;
import java.util.List;

public class DatabaseManagerPGSQL {


    public Connection conn;
    private Statement statement;

    private static final String url = "jdbc:postgresql://192.168.56.1:5432/sppp";
    private static final String user = "postgres";
    private static final String pass = "postgres";


    static private DatabaseManagerPGSQL instance;

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

    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException{
        statement = instance.conn.createStatement();
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

    private DatabaseManagerPGSQL() {

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
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
            Connection con = DriverManager.getConnection(url, user, pass);
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