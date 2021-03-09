package com.example.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sample.log.Logging
import com.example.sample.preference.PreferenceManager
import com.example.sample.realm.RealmManager
import com.example.sample.room.UserManager
import com.example.sample.sqlite.SqlManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        // Log

        //Logging.context = this
        Logging.i("AAAAA")
        Logging.i("")

        // Preferences

        PreferenceManager.put("hoge", "fuga", this)
        Logging.i(PreferenceManager.get("hoge", this))

        // Room

        UserManager.getInstance().insert(this)
        UserManager.getInstance().insert(this)
        UserManager.getInstance().selectAll(this)
        UserManager.getInstance().deleteAll(this)

        // Realm

        RealmManager.getInstance().insert(this)
        RealmManager.getInstance().insert(this)
        RealmManager.getInstance().selectAll(this)
        RealmManager.getInstance().deleteAll(this)

        // SQLite

        SqlManager.getInstance().insert(this)
        SqlManager.getInstance().insert(this)
        SqlManager.getInstance().selectAll(this)
        SqlManager.getInstance().deleteAll(this)
    }
}