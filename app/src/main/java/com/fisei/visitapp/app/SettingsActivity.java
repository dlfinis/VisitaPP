package com.fisei.visitapp.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import com.fisei.visitapp.app.check.NetworkUtils;
import com.fisei.visitapp.app.database.DatabaseManager;
import com.fisei.visitapp.app.database.DatabaseManagerPGSQL;
import com.fisei.visitapp.app.database.PgsqlDataAsyncTask;
import org.postgresql.util.PSQLException;


import javax.sql.ConnectionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends PreferenceActivity {
    /**
     * Determines whether to always show the simplified settings UI, where
     * settings are presented in a single list. When false, settings are shown
     * as a master/detail two-pane view on tablets. When true, a single pane is
     * shown on tablets.
     */
    private static final boolean ALWAYS_SIMPLE_PREFS = false;
    private ProgressDialog pDialog;
    public static String DATOS="DATOS";
    public static String SINCRONIZACION="SINCRONIZACION";


   final public Context ctx=this;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setupSimplePreferencesScreen();

    }


    private String getSharedPreference(String prf)
    {
        Preference prefs = (Preference)findPreference(prf);
        String value;
        value= PreferenceManager.getDefaultSharedPreferences(prefs.getContext()).getString(prefs.getKey(), "000");
        return value;
    }

    private Integer getIntegerSharedPreference(String prf)
    {
        Preference prefs = (Preference)findPreference(prf);
        int value;
        value= PreferenceManager.getDefaultSharedPreferences(prefs.getContext()).getInt(prefs.getKey(), 0);
        return value;
    }

    private boolean isDatabaseAvaliable()
    {
        String url="";
         boolean valor=false;
        try {

            url = String.format("jdbc:postgresql://%s:%d/%s",
                    getSharedPreference("prefDireccion"),
                    Integer.valueOf(getSharedPreference("prefPuerto")), "sppp");



            valor = new AsyncTask<String, Void, Boolean>() {

                @Override
                protected Boolean doInBackground(String... url) {
                    try {

                        if (android.os.Build.VERSION.SDK_INT > 9) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                        }
                        boolean isAlive=NetworkUtils.isHostReachable(
                                getSharedPreference("prefDireccion"),
                                Integer.valueOf(getSharedPreference("prefPuerto")),
                                        100);


                        if(isAlive) {


                            Class.forName("org.postgresql.Driver");
                            Connection conn = DriverManager.getConnection(url[0],
                                    getSharedPreference("prefUsuarioBD"),
                                    getSharedPreference("prefClaveBD"));

                            conn.close();

                            DatabaseManagerPGSQL.setDireccion(getSharedPreference("prefDireccion"));
                            DatabaseManagerPGSQL.setPuerto(Integer.valueOf(getSharedPreference("prefPuerto")));
                            DatabaseManagerPGSQL.setUsuario(getSharedPreference("prefUsuarioBD"));
                            DatabaseManagerPGSQL.setClave(getSharedPreference("prefClaveBD"));

                            DatabaseManagerPGSQL.getInstance();

                            return true;

                        }
                        else {
                            return false;
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }catch (PSQLException e) {
                        e.printStackTrace();
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return false;
                }
            }.execute(url).get();
            return false;

        }
        catch(NullPointerException e)
        {

        }
        catch (Exception e)
        {  e.printStackTrace();}

        finally {
            return valor;
        }



    }

    private boolean isUserAvaliable(String ccResponsable,String claveResponsable) throws ExecutionException, InterruptedException {
        PgsqlDataAsyncTask.UserCheck user = PgsqlDataAsyncTask.getInstance().getUserCheck(SettingsActivity.this);


        Map<String, String> userMap = new HashMap<String, String>();
        userMap.put("CCResponsable", ccResponsable);
        userMap.put("ClaveResponsable", claveResponsable);

        boolean result=user.execute(userMap).get();

        return  result;

    }

    private boolean loadEstudiantesInformacion(ProgressDialog progressDialog) throws ExecutionException, InterruptedException {

          PgsqlDataAsyncTask.TEstudianteInformacion tEstudianteInformacion =
                PgsqlDataAsyncTask.getInstance().getTEstudianteInformacion(SettingsActivity.this);
        tEstudianteInformacion.execute(getSharedPreference("prefCC"));

        progressDialog.setProgress(25);
        boolean result=tEstudianteInformacion.get();

        return result;

    }
    private boolean loadPasantiaPracticas(ProgressDialog progressDialog) throws ExecutionException, InterruptedException {

        PgsqlDataAsyncTask.TPasantiaPracticas tPasantiaPracticas =
                PgsqlDataAsyncTask.getInstance().getTPasantiaPracticas(SettingsActivity.this);
        tPasantiaPracticas.execute(getSharedPreference("prefCC"));

        progressDialog.setProgress(50);
        boolean result= tPasantiaPracticas.get();

        return result;

    }

    private boolean saveVisitaPractica(ProgressDialog progressDialog) throws ExecutionException, InterruptedException {

        PgsqlDataAsyncTask.TVisitaPracticaBD tVisitaPracticaBD =
                PgsqlDataAsyncTask.getInstance().getTVisitaPracticaBD(SettingsActivity.this);
        tVisitaPracticaBD.execute();

        progressDialog.setProgress(100);
        boolean result=tVisitaPracticaBD.get();

        return result;

    }
    private boolean loadVisitaPractica(ProgressDialog progressDialog) throws ExecutionException, InterruptedException {

        PgsqlDataAsyncTask.TVisitaPractica tVisitaPractica =
                PgsqlDataAsyncTask.getInstance().getTVisitaPractica(SettingsActivity.this);
        tVisitaPractica.execute(getSharedPreference("prefCC"));

        progressDialog.setProgress(75);
        boolean result= tVisitaPractica.get();

        return result;

    }

    private boolean loadResponsableIngreso(ProgressDialog progressDialog) throws ExecutionException, InterruptedException {

      PgsqlDataAsyncTask.TResponsableIngreso tResponsableIngreso =
                PgsqlDataAsyncTask.getInstance().getTResponsableIngreso(SettingsActivity.this);
        tResponsableIngreso.execute(getSharedPreference("prefCC"));

        progressDialog.setProgress(100);
        boolean result= tResponsableIngreso.get();

        return result;

    }

    /**
     * Shows the simplified settings UI if the device configuration if the
     * device configuration dictates that a simplified, single-pane UI should be
     * shown.
     */
    private void setupSimplePreferencesScreen() {
        if (!isSimplePreferences(this)) {
            return;
        }

        // In the simplified UI, fragments are not used at all and we instead
        // use the older PreferenceActivity APIs.

        // Add 'general' preferences.
        addPreferencesFromResource(R.xml.pref_general);


        Preference sincronizacionPref = (Preference) findPreference("prefSincronizacion");
        sincronizacionPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {

                try {


                    ProgressDialog progressDialog = new ProgressDialog(SettingsActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setMessage("Sincronizando.... Espere");
                    progressDialog.setCancelable(true);
                    progressDialog.setMax(100);
                    progressDialog.setProgress(0);
                    String ccResponsable, claveResponsable;
                    ccResponsable = claveResponsable = "";

                    ccResponsable = getSharedPreference("prefCC");
                    claveResponsable = getSharedPreference("prefClave");


                    if (ccResponsable != null && claveResponsable != null) {
                        if (NetworkUtils.isNetworkAvailable(SettingsActivity.this.getApplicationContext())) {


                            if (isDatabaseAvaliable()) {

                                try {
                                    if (isUserAvaliable(ccResponsable, claveResponsable)) {

//                                        Toast.makeText(SettingsActivity.this,
//                                                "Sincronizacion Iniciada.", Toast.LENGTH_SHORT).show();

                                        progressDialog.show();






                                        loadEstudiantesInformacion(progressDialog);

                                        loadPasantiaPracticas(progressDialog);

                                        loadVisitaPractica(progressDialog);


                                        loadResponsableIngreso(progressDialog);

                                        saveVisitaPractica(progressDialog);




                                            Toast.makeText(SettingsActivity.this,
                                                "Sincronizacion Finalizada.", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                        return true;
                                    }
                                }catch (ExecutionException ex)
                                {
                                    ex.printStackTrace();
                                }
                                catch (InterruptedException ex)
                                {
                                    ex.printStackTrace();
                                }




                                return true;
                                
                            } else {
                                Toast.makeText(SettingsActivity.this,
                                        "Conexión hacia BD invalida.Revise su información.", Toast.LENGTH_SHORT).show();

                                return false;
                            }


                        } else {
                            Toast.makeText(SettingsActivity.this, R.string.false_coneccion_wifi, Toast.LENGTH_SHORT).show();
                            return false;
                        }

                    }
                }catch (Exception e)
                {
                    e.printStackTrace();

                }


                return false;
            }

        });


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this) && !isSimplePreferences(this);
    }

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Determines whether the simplified settings UI should be shown. This is
     * true if this is forced via {@link #ALWAYS_SIMPLE_PREFS}, or the device
     * doesn't have newer APIs like {@link PreferenceFragment}, or the device
     * doesn't have an extra-large screen. In these cases, a single-pane
     * "simplified" settings UI should be shown.
     */
    private static boolean isSimplePreferences(Context context) {
        return ALWAYS_SIMPLE_PREFS
                || Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB
                || !isXLargeTablet(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        if (!isSimplePreferences(this)) {
            loadHeadersFromResource(R.xml.pref_headers, target);
        }
    }

    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    /**
     * This fragment shows general preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("prefCC"));
            bindPreferenceSummaryToValue(findPreference("prefClave"));
            bindPreferenceSummaryToValue(findPreference("prefDireccion"));
//            bindPreferenceSummaryToValue(findPreference("prefPuerto"));
            bindPreferenceSummaryToValue(findPreference("prefUsuarioBD"));
            bindPreferenceSummaryToValue(findPreference("prefClaveBD"));
        }
    }


}
