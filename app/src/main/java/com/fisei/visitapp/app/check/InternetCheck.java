package com.fisei.visitapp.app.check;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by diegoztc on 25/02/15.
 */
public class InternetCheck {

    Context context;
    static ConnectivityManager connManager;
    static NetworkInfo mWifi;

    static InternetCheck instance;

    /**
     *
     * @param ctx
     * @return InternetCheck object
     */
    static public InternetCheck getInstance(Context ctx) {
        if ( instance == null ) {
            instance = new InternetCheck(ctx);
        }
        return instance;
    }

     public InternetCheck(Context ctx){
        this.context=ctx;
        connManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    }

    public boolean hasInternetConnection(){
        if(mWifi.isConnected())
            return true;

        return false;
    }
}