package dk.submarine.viverra.submarine.Services

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.iid.FirebaseInstanceId



class SubmarineFirebaseInstanceIdService : FirebaseInstanceIdService() {
    private val LOG_TAG = "FirebaseInstanceIdServi"

    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d(LOG_TAG, "Refreshed token: " + refreshedToken!!)

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.

        // TODO
        //sendRegistrationToServer(refreshedToken)
    }
}
