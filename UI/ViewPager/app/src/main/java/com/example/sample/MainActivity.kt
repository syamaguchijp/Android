package com.example.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ViewPager2の生成
        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager)
        val pagerAdapter = MyFragmentStateAdapter(this)
        viewPager2.adapter = pagerAdapter

        // TabLayoutでインジケータを作り、ViewPagerと結びつける
        val tabLayout = findViewById<TabLayout>(R.id.inidicator_tab_layout)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
        }.attach()
    }
}