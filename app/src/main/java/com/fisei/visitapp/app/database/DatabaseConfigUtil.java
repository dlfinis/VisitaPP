package com.fisei.visitapp.app.database;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by diegoztc on 19/02/15.
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    public static void main(String args[]) throws IOException, SQLException {
        writeConfigFile("ormlite_config.txt");
    }
}
