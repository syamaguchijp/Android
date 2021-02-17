package com.example.sample

import junit.framework.TestCase
import org.junit.Test

class DateUtilTest : TestCase() {

    public override fun setUp() {
        super.setUp()
    }

    public override fun tearDown() {}

    @Test
    fun testConertToString() {

        val testDate = DateUtil.getDate(2021, 2, 7, 1, 2, 33)
        val testStr = DateUtil.covertToString(testDate!!, DateUtil.gregorianFormatterISO8601())
        assertEquals("2021-02-07T01:02:33+0900", testStr)
    }
}