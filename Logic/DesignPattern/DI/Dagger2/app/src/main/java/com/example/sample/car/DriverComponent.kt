package com.example.sample.car

import dagger.Component

// Componetクラスから、daggerがコードを自動生成してインジェクションを行う
@Component
interface DriverComponent {
    var driver: Driver
}