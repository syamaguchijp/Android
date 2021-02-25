import java.util.Date
import java.util.GregorianCalendar
import java.util.Calendar
import java.util.TimeZone
import java.util.Locale
import java.text.SimpleDateFormat

class DateUtil() {
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

fun main() {

    println(DateUtil.covertToString(Date(), DateUtil.gregorianFormatterISO8601()))
    println(DateUtil.covertToDate("2021-02-12T11:29:54+0900", DateUtil.gregorianFormatterISO8601()))  
    println(DateUtil.getDate(Date(), 7))
    println(DateUtil.getDate(2021, 2, 15, 1, 2, 33))

    // ２つの日時の差分（秒）
    val cal1 = Calendar.getInstance()
    cal1.set(2021, 2, 15, 1, 2, 33)
    val cal2 = Calendar.getInstance()
    cal2.set(2021, 2, 15, 1, 2, 34)
    println(((cal2.timeInMillis - cal1.timeInMillis) / 1000).toInt()) // ミリセカンドを秒に変換

}