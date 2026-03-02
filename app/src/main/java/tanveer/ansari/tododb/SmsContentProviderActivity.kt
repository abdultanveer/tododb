package tanveer.ansari.tododb

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.cursoradapter.widget.SimpleCursorAdapter


class SmsContentProviderActivity : AppCompatActivity() {
lateinit var  simpleCursorAdapter: SimpleCursorAdapter
    private
     val SMS_PERMISSION_REQUEST_CODE: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_content_provider)
        requestSmsPermission()
        val listView: ListView = findViewById<ListView>(R.id.cpListView)


        val fromColNames = arrayOf("address","body")
        val toTextViews = intArrayOf(android.R.id.text1,android.R.id.text2)

         simpleCursorAdapter = SimpleCursorAdapter(this,
            android.R.layout.simple_list_item_2,
            null,fromColNames,toTextViews)

        listView.adapter = simpleCursorAdapter

    }



    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted, proceed to query SMS
                querySmsInbox()
            } else {
                // Permission is denied, show an explanation or handle accordingly
                Toast.makeText(this, "SMS permission denied. Cannot query inbox.", Toast.LENGTH_LONG).show()
                // Optionally, show a rationale if the user denied it previously
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_SMS)) {
                    // Show a rationale in an educational UI
                }
            }
        }

    private fun requestSmsPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_SMS
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission
                querySmsInbox()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_SMS) -> {
                // In an educational UI, explain to the user why your app requires this permission
                Toast.makeText(this, "We need SMS permission to read the inbox for our feature.", Toast.LENGTH_LONG).show()
                requestPermissionLauncher.launch(Manifest.permission.READ_SMS)
            }
            else -> {
                // Directly request the permission
                requestPermissionLauncher.launch(Manifest.permission.READ_SMS)
            }
        }
    }

    private fun querySmsInbox() {
        val uriSms = Uri.parse("content://sms/inbox")


        val cursor: Cursor? = getContentResolver().query(uriSms, null, null, null, null)

        simpleCursorAdapter.changeCursor(cursor)
    }

    }