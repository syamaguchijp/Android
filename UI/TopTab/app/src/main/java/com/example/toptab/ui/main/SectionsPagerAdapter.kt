package com.example.toptab.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.toptab.Logging
import com.example.toptab.R

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2,
        R.string.tab_text_3,
        R.string.tab_text_4,
        R.string.tab_text_5,
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        Logging.d("")

        var urlStr = ""
        when (position) {
            0 -> {urlStr = "https://google.com"}
            1 -> {urlStr = "https://yahoo.co.jp"}
            2 -> {urlStr = "https://apple.com"}
            3 -> {urlStr = "https://www.amazon.co.jp"}
            4 -> {urlStr = "https://github.com"}
            else -> {}
        }
        return WebViewFragment.newInstance(urlStr)
    }

    override fun getPageTitle(position: Int): CharSequence? {

        Logging.d("")

        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {

        Logging.d("")

        return TAB_TITLES.size
    }
}