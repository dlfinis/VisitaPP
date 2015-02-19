package com.fisei.visitapp.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import com.fisei.visitapp.app.database.DatabaseHelper;
import com.fisei.visitapp.app.entity.Test;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;


public class TestActivity extends ActionBarActivity {

    private final String LOG_TAG = getClass().getSimpleName();
    /**
     * You'll need this in your class to cache the helper in the class.
     */
    private DatabaseHelper databaseHelper = null;

    EditText edtTPK;
    EditText edtTValue;
    Button btnTOk;
    Button btnTCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.i(LOG_TAG, "creating " + getClass() + " at " + System.currentTimeMillis());
        edtTPK = (EditText) findViewById(R.id.edtTPk);
        edtTValue = (EditText) findViewById(R.id.edtTValue);
        btnTOk = (Button) findViewById(R.id.btnTOk);
        btnTCancel = (Button) findViewById(R.id.btnTCancel);
        doTestStuff(edtTPK, edtTValue);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*
        * You'll need this in your class to release the helper when done.
        */
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }


    /**
     * You'll need this in your class to get the helper from the manager once per class.
     */
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }


    private void doTestStuff(EditText pk, EditText value) {
        try {
            // get our dao
            Dao<Test, Integer> testDao = getHelper().getTestDAO();
            // query for all of the data objects in the database
            List<Test> list = testDao.queryForAll();

            Test object = testDao.queryForId(1);
            pk.setText(object.getPk());
            value.setText(object.getValue());
            // our string builder for building the content-view
            StringBuilder sb = new StringBuilder();
            sb.append("got ").append(list.size()).append(" entries in ").append("oncreate").append("\n");
            // if we already have items in the database
//            int simpleC = 0;
//            for (Test test : list) {
//                sb.append("------------------------------------------\n");
//                sb.append("[").append(simpleC).append("] = ").append(test).append("\n");
//                simpleC++;
//            }
//            sb.append("------------------------------------------\n");
//            for (SimpleData simple : list) {
//                simpleDao.delete(simple);
//                sb.append("deleted id ").append(simple.id).append("\n");
//                Log.i(LOG_TAG, "deleting simple(" + simple.id + ")");
//                simpleC++;
//            }
//            int createNum;
//            do {
//                createNum = new Random().nextInt(3) + 1;
//            } while (createNum == list.size());
//            for (int i = 0; i < createNum; i++) {
//// create a new simple object
//                long millis = System.currentTimeMillis();
//                SimpleData simple = new SimpleData(millis);
//// store it in the database
//                simpleDao.create(simple);
//                Log.i(LOG_TAG, "created simple(" + millis + ")");
//// output it
//                sb.append("------------------------------------------\n");
//                sb.append("created new entry #").append(i + 1).append(":\n");
//                sb.append(simple).append("\n");
//                try {
//                    Thread.sleep(5);
//                } catch (InterruptedException e) {
//// ignore
//                }
//            }
//            tv.setText(sb.toString());
//            Log.i(LOG_TAG, "Done with page at " + System.currentTimeMillis());
//        } catch (SQLException e) {
//            Log.e(LOG_TAG, "Database exception", e);
//            tv.setText("Database exeption: " + e);
//            return;
//        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
