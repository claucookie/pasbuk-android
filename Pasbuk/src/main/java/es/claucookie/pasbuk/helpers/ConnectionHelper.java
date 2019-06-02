package es.claucookie.pasbuk.helpers;

import android.net.Uri;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import es.claucookie.pasbuk.Consts;

/**
 * Created by claucookie on 19/04/15.
 */
public class ConnectionHelper {

    public static boolean serverExists (String uriString) {
        boolean exists = false;
        try {
            SocketAddress sockaddr = new InetSocketAddress(Uri.parse(uriString).getHost(), 80);
            // Create an unbound socket
            Socket sock = new Socket();

            // This method will block no more than timeoutMs.
            // If the timeout occurs, SocketTimeoutException is thrown.
            int timeoutMs = Consts.TIME_OUT_IN_MILLIS;   // 2 seconds
            sock.connect(sockaddr, timeoutMs);
            sock.close();
            exists = true;
        } catch (Exception e) {
            exists = false;
        } finally {
            return exists;
        }
    }
}
