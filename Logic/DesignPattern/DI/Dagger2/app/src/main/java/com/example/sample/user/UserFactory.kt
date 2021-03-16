package com.example.sample.user

import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module // DIするクラス
class UserFactory @Inject constructor() { // コンストラクタにInjectアノテーションを付けないと、Daggerは注入処理をしてくれない

    @Provides
    fun createUser(age: Int): User {
        if (age >= 50) {
            return SpecialUser()
        }
        return NormalUser()
    }

}