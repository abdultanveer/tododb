package tanveer.ansari.tododb

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.util.Log
import androidx.annotation.RequiresApi


class SmsReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        Log.i("sms","received sms")
            val bundle = intent.getExtras()
            if (bundle != null) {
                val pdus = bundle.get("pdus") as Array<Any?>?
                val messages: Array<SmsMessage?> = kotlin.arrayOfNulls<SmsMessage>(pdus!!.size)
                for (i in pdus.indices) {
                    messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray?)
                }

                if (messages.size > -1) {
                    var musicServiceIntent = Intent(context, MusicService::class.java)
                    context.startService(musicServiceIntent)
                    Log.i("SMS", "Message recieved: " + messages[0]?.getMessageBody()
                        //.contains("get sms")
                            +"phno-"+messages[0]?.originatingAddress)
//
//                    var smsManager = SmsManager.getDefault()
//                    smsManager.sendTextMessage("98765432",null,"call-log",null,null,)
//
                             }
            }

    }
}