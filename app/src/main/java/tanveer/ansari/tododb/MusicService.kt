package tanveer.ansari.tododb

import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import androidx.core.app.ServiceCompat

class MusicService : Service() {
    lateinit  var mediaPlayer: MediaPlayer
    var TAG  = "MusicService"
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG,"service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
         super.onStartCommand(intent, flags, startId)
//        ServiceCompat.startForeground(this, 123, notification,
//            ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION); // Use the correct type

        var dataReceived = intent?.getStringExtra("url")
        Log.i(TAG,"url is"+dataReceived)
         mediaPlayer = MediaPlayer.create(this,R.raw.mymysic)
        mediaPlayer.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"service destroyed")
        mediaPlayer.release()

    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}