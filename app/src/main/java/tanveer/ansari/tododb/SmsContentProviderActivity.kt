package tanveer.ansari.tododb

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.cursoradapter.widget.SimpleCursorAdapter


class SmsContentProviderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_content_provider)

        val listView: ListView = findViewById<ListView>(R.id.cpListView)

        val uriSms = Uri.parse("content://sms/inbox")
        val cursor: Cursor? = getContentResolver().query(uriSms, null, null, null, null)

        val fromColNames = arrayOf("address","body")
        val toTextViews = intArrayOf(android.R.id.text1,android.R.id.text2)

        var simpleCursorAdapter = SimpleCursorAdapter(this,
            android.R.layout.simple_list_item_2,
            cursor,fromColNames,toTextViews)

        listView.adapter = simpleCursorAdapter

    }
}