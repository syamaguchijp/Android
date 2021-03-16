package com.example.sample.user

import com.example.sample.MainActivity
import dagger.Component

// DIさせる対象
// Componentを付けたinterfaceに対する実体クラスが、MainActivityにおいて自動生成される
@Component(modules = [UserFactory::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
}