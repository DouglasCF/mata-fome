package br.com.fornaro.matafome.cloud

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val TAG = "FCM"
    }

    override fun onMessageReceived(p0: RemoteMessage?) {
        super.onMessageReceived(p0)
        Log.v(TAG, "new message received: $p0")

        // Implement notification
    }

    override fun onNewToken(p0: String?) {
        super.onNewToken(p0)
        Log.v(TAG, "new token received: $p0")
    }
}