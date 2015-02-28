package com.fisei.visitapp.app.check;

/**
 * Created by diegoztc on 25/02/15.
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

public class NetworkUtils {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connection = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = null;
        if (connection != null) {
            nInfo = connection.getActiveNetworkInfo();
        }
        if (nInfo == null || !nInfo.isConnectedOrConnecting()) {
            return false;
        }

        if (nInfo == null || !nInfo.isConnected()) {
            return false;
        }
        if (nInfo != null
                && ((nInfo.getType() == ConnectivityManager.TYPE_MOBILE) || (nInfo
                .getType() == ConnectivityManager.TYPE_WIFI))) {
            if (nInfo.getState() != NetworkInfo.State.CONNECTED
                    || nInfo.getState() == NetworkInfo.State.CONNECTING) {
                return false;
            }
        }
        return true;
    }

    public static boolean isHostReachable(String serverAddress, int serverTCPport, int timeoutMS){
        boolean connected = false;
        Socket socket;
        try {
            socket = new Socket();
           // Log.e("Server",serverAddress);
            SocketAddress socketAddress = new InetSocketAddress(serverAddress, serverTCPport);
            socket.connect(socketAddress, timeoutMS);
            if (socket.isConnected()) {
                connected = true;
                socket.close();
            }
        } catch (SocketTimeoutException e) {
        }
         catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket = null;
        }
        return connected;
    }
}