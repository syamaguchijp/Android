import java.util.Arrays;

public class StringSample {

    public static void main(String args[]) {

        String str = "ABCDEFG12345";

        // 文字列内の変数利用
        System.out.println(String.format("Hello %s", str));

        // 一致判定
        String strA = "ABC";
        System.out.println(strA.equals("ABC"));
        System.out.println(strA.equalsIgnoreCase("aBc"));

        // 長さ
        System.out.println(str.length());

        // 切り出し
        System.out.println(str.substring(1)); // BCDEFG12345
        System.out.println(str.substring(1, 3)); // BC

        // 検索
        System.out.println(str.contains("ABC")); // true
        System.out.println(str.indexOf("CDE")); // 2
        System.out.println(str.startsWith("ABC")); // true
        System.out.println(str.endsWith  ("45")); // true

        // 分割
        String[] result = "A,B,C,D".split(",");
        for (String s : result) {
            System.out.println(s);
        }

        // 結合
        System.out.println("Honda" + "Kawasaki" + 400);

        StringBuilder buff = new StringBuilder();
        buff.append("AA");
        buff.append("BB");
        buff.append("CC");
        System.out.println(buff.toString());

        // 大文字小文字
        System.out.println("abc".toUpperCase()); // ABC
        System.out.println("ABC".toLowerCase()); // abc

        // 置換
        System.out.println(str.replace("AB", "XXX"));
        System.out.println("ab123ab123".replaceAll("[a-z]+", "0")); // 0012300123
        System.out.println("ab123ab123".replaceFirst("[a-z]+", "0")); // 00123ab123

        // 前後の空白を削除
        String str2 = " ab cd ";
        System.out.println(str2.trim()); // ab cd
        
        // フォーマット
        System.out.println(String.format("%05d", 123)); // 00123
        System.out.println(String.format("%5d", 123)); //   123
        System.out.println(String.format("%d %s", 123, "abc")); // 123 abc

        // 数値に変換
        String numStr = "123";
        System.out.println(Integer.parseInt(numStr));
    }
}