package com.example.sample

import java.util.Date
import java.util.Calendar
import java.util.TimeZone
import java.util.Locale
import java.text.SimpleDateFormat

class DateUtil {

    companion object {

        /**
         * ISO8601形式のDateフォーマッタ
         */
        fun gregorianFormatterISO8601(): SimpleDateFormat {
            return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        }

        /**
         * Dateから文字列への変換
         */
        fun covertToString(date: Date, format: SimpleDateFormat): String? {
            return format.format(date)
        }

        /**
         * 文字列からDateへの変換
         */
        fun covertToDate(dateString: String, format: SimpleDateFormat): Date? {
            return format.parse(dateString)
        }

        /**
         * 日付の加減計算
         */
        fun getDate(date: Date, dateDelta: Int): Date {
            val cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN)
            cal.time = date
            cal.add(Calendar.DATE, dateDelta) // dateDelta日後（マイナスの場合はdateDelta日前）
            return cal.time
        }

        /**
         * 年月日時分秒からDateオブジェクトを生成
         */
        fun getDate(year: Int, month: Int, date: Int, hour: Int, minute: Int, second: Int): Date? {
            val cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN)
            cal.set(year, month-1, date, hour, minute, second) // monthは0から11の値を入力する
            return cal.time
        }
    }

}