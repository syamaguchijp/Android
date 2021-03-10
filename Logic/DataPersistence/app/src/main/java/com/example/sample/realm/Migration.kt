package com.example.sample.realm

import io.realm.DynamicRealm
import io.realm.FieldAttribute
import io.realm.RealmMigration

// スキーマ変更
class Migration : RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {

        val realmSchema = realm.schema
        var oldVersion = oldVersion

        if (oldVersion == 0L) {
            realmSchema.get("Person")!!
                .addField("nickname", String::class.java, FieldAttribute.REQUIRED) // nullを許容しない
            oldVersion++ // migirationしたため、スキーマバージョンを上げる
        }
    }
}