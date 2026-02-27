package tanveer.ansari.tododb

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log


class SmsReceiver : BroadcastReceiver() {

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
                    Log.i("SMS", "Message recieved: " + messages[0]?.getMessageBody()
                            +"phno-"+messages[0]?.originatingAddress)
                }
            }

    }
}