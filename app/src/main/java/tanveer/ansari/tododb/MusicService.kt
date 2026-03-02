package tanveer.ansari.tododb

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MusicService : Service() {
var TAG  = "MusicService"
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG,"service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
         super.onStartCommand(intent, flags, startId)
        var dataReceived = intent?.getStringExtra("url")
        Log.i(TAG,"url is"+dataReceived)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"service destroyed")

    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}