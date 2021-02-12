import java.util.Date
import java.util.GregorianCalendar
import java.util.Calendar
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
            val cal = Calendar.getInstance()
            cal.time = date
            cal.add(Calendar.DATE, dateDelta) // dateDelta日後（マイナスの場合はdateDelta日前）
            return cal.time
        }
    }
}

fun main() {

    println(DateUtil.covertToString(Date(), DateUtil.gregorianFormatterISO8601()))
    println(DateUtil.covertToDate("2021-02-12T11:29:54+0900", DateUtil.gregorianFormatterISO8601()))  
    println(DateUtil.getDate(Date(), 7))
}