import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateSample {

    public static void main(String args[]) {
        
        System.out.println(DateUtil.covertToString(new Date(), DateUtil.gregorianFormatterISO8601()));
        System.out.println(DateUtil.covertToDate("2021-02-12T11:29:54+0900", DateUtil.gregorianFormatterISO8601()));
        System.out.println(DateUtil.getDate(new Date(), 7));
        System.out.println(DateUtil.getDate(2021, 2, 15, 1, 2, 33));

        // ２つの日時の差分（秒）
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2021, 2, 15, 1, 2, 33);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2021, 2, 15, 1, 2, 34);
        System.out.println((cal2.getTimeInMillis() - cal1.getTimeInMillis()) / 1000); // ミリセカンドを秒に変換
    }
}

class DateUtil {
    /**
     * ISO8601形式のDateフォーマッタ
     */
    public static SimpleDateFormat gregorianFormatterISO8601() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    }

    /**
     * Dateから文字列への変換
     */
    public static String covertToString(Date date, SimpleDateFormat format) {
        return format.format(date);
    }

    /**
     * 文字列からDateへの変換
     */
    public static Date covertToDate(String dateString, SimpleDateFormat format) {
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 日付の加減計算
     */
    public static Date getDate(Date date, int dateDelta) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN);
        cal.setTime(date);
        cal.add(Calendar.DATE, dateDelta); // dateDelta日後（マイナスの場合はdateDelta日前）
        return cal.getTime();
    }

    /**
     * 年月日時分秒からDateオブジェクトを生成
     */
    public static Date getDate(int year, int month, int date, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN);
        cal.set(year, month-1, date, hour, minute, second); // monthは0から11の値を入力する
        return cal.getTime();
    }
}