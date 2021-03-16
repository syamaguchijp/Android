package com.example.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sample.car.DaggerDriverComponent
import com.example.sample.car.DriverComponent
import com.example.sample.user.DaggerActivityComponent
import com.example.sample.user.UserFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userFactory: UserFactory // DaggerがInjectアノテーションによって、このインスタンスを生成して代入してくれる

    lateinit var driverComponent: DriverComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region User

        // Dagger
        DaggerActivityComponent.create().inject(this) // DaggerActivityComponentはActivityComponentから自動生成されるクラス
        val user = userFactory.createUser(21)
        println("paybill ${user.payBill(100)}")

        // Daggerが無い場合
        // UserFactoryクラスのインスタンスを明示的に生成しなければならない
        val userFactory2 = UserFactory()
        val user2 = userFactory2.createUser(20)

        //endregion


        //region Car
/*
        driverComponent = DaggerDriverComponent.create()
        driverComponent.driver.drive()
 */
        //endregion
    }
}