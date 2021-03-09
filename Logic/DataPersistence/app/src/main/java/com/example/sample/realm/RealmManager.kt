package com.example.sample.realm

import android.content.Context
import com.example.sample.log.Logging
import io.realm.Realm
import io.realm.kotlin.where
import java.util.*

class RealmManager {

    companion object {
        // Singleton
        private var instance: RealmManager? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: RealmManager().also { instance = it }
        }
    }

    private var isInitialized = false

    fun insert(context: Context) {

        if (!isInitialized) {Realm.init(context)}

        var realm = Realm.getDefaultInstance()
        val personData = Person(Date().time.toString(),31)
        realm.beginTransaction()
        realm.insert(personData)
        realm.commitTransaction()
        realm.close()
    }

    fun selectAll(context: Context) {

        if (!isInitialized) {Realm.init(context)}

        var realm = Realm.getDefaultInstance()
        val persons = realm.where<Person>().findAll()
        for (person in persons) {
            Logging.i("${person.toString()}")
        }
        realm.close() // closeするとRealmObjectは消えることに注意（この後利用する場合は、copyFromRealmでコピーして利用する）
    }

    fun deleteAll(context: Context) {

        if (!isInitialized) {Realm.init(context)}

        var realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val persons = realm.where<Person>().findAll()
        persons.deleteAllFromRealm()
        realm.commitTransaction()
        realm.close()
    }
}