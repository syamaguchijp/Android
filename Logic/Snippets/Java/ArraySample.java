import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class ArraySample {

    public static void main(String args[]) {

        //region 配列

        // 空の配列
        int[] numAry = new int[3];

        // 初期値を入れて初期化
        int[] numAry3 = new int[]{4, 10, 7};

        // 配列の要素数
        System.out.println(numAry3.length);

        // 配列の走査
        String[] strAry = new String[]{"Tokyo", "Osaka", "Hakata"};
        for (int i = 0; i < strAry.length; i++) {
            System.out.println(strAry[i]);
        }
        for (String str: strAry) {
            System.out.println(str);
        }
        Arrays.stream(strAry).forEach(str -> System.out.println(str)); // ラムダ
        // 配列の末尾から先頭へのデクリメント
        for (int i = strAry.length-1; i>=0; i--) {
            System.out.println(strAry[i]);
        }

        // 存在確認
        if (Arrays.asList(strAry).contains("Hakata")) {
            System.out.println("contains Hakata");
        }

        // DeepCopy
        String[] copyAry = strAry.clone();
        strAry[0] = "Sapporo";
        System.out.println("copyAry[0]=" + copyAry[0]);

        // ShallowCopy
        String[] copyAry2 = strAry;
        strAry[0] = "Naha";
        System.out.println("copyAry2[0]=" + copyAry2[0]);

        // 多次元配列
        int[][] multiAry = new int[5][5];
        multiAry[0][0] = 7;
        multiAry[0][1] = 9;
        System.out.println(multiAry[0][0]);

        //endregion

        //region リスト

        // 空のリスト
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<String>(1000); // 要素数を指定して初期化し高速化

        // 初期値を入れて初期化
        List<String> list3 = new ArrayList<String>(Arrays.asList("Tokyo", "Osaka", "Nagoya"));
        List<String> list4 = new ArrayList<String>(){{
            add("Tokyo");
            add("osaka");
        }};
        List<String> list5 = Arrays.asList("AA", "BB", "CC");
        
        // リストの要素数
        System.out.println(list4.size());

        // 要素の追加と削除
        list.add("Tokyo");
        list.add("Osaka");
        List<String> list11 = new ArrayList<>();
        list11.add("Hakata");
        list11.add("Nagasaki");
        list.addAll(list11);
        list.remove(0); // インデックスを指定して削除
        list.remove(list.indexOf("Osaka")); // 要素を指定して削除
        list.clear(); // 全削除

        // 走査
        for (int i = 0; i < list3.size(); i++) {
            System.out.println(list3.get(i));
        }
        for (String str: list3) {
            System.out.println(str);
        }
        for (Iterator it = list3.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }
        list3.forEach(str -> System.out.println(str)); // ラムダ
        // 配列の末尾から先頭へのデクリメント
        for (int i = list3.size()-1; i>=0; i--) {
            System.out.println(list3.get(i));
        }

        // 存在確認
        if (list3.contains("Nagoya")) {
            System.out.println("contains Nagoya");
        }

        // DeepCopy
        // 要素がプリミティブではない場合は、自前でDeep copyを実装する必要がある
        ArrayList<String> strList = new ArrayList<String>(Arrays.asList("Tokyo", "Osaka", "Nagoya"));
        ArrayList<String> copyList = (ArrayList<String>)strList.clone(); // ArrayListはclonable
        strList.set(0, "Sapporo");
        System.out.println("copyList[0]=" + copyList.get(0));

        // ShallowCopy
        List<String> copyList2 = strList;
        strList.set(0, "Naha");
        System.out.println("copyList2[0]=" + copyList2.get(0));

        //endregion

        //region セット型

        Set<Integer> hashSet = new HashSet<Integer>();

        // 要素の追加と削除
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(1);
        hashSet.remove(2);

        // 要素数
        System.out.println(hashSet.size());

        // 走査
        for (Integer num: hashSet) {
            System.out.println(num);
        }
        // 存在確認
        if (hashSet.contains(1)) {
            System.out.println("contains 1");
        }

        //endregion

        //region 辞書

        HashMap<String, String> hashmap = new HashMap<String, String>();

        // 要素の追加と削除
        hashmap.put("tokyo", "東京");
        hashmap.put("osaka", "大阪");
        hashmap.put("nagoya", "名古屋");
        hashmap.put("hakata", "博多");
        hashmap.remove("osaka");

        // アクセス
        System.out.println(hashmap.get("tokyo"));

        // 更新
        hashmap.replace("hakata", "ハカタ");

        // 要素数
        System.out.println(hashmap.size());

        // 走査
        for (String key : hashmap.keySet()) {
            System.out.println(key);
        }
        for (String value : hashmap.values()) {
            System.out.println(value);
        }
        for (Map.Entry<String, String> entry : hashmap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        Set<String> keySet = hashmap.keySet();
        Iterator<String> iteratorForKey = keySet.iterator();
        while (iteratorForKey.hasNext()) {
            String key = iteratorForKey.next();
            String value = hashmap.get(key);
            System.out.println(key + ": " + value);
        }

        Set<Map.Entry<String, String>> entrySet = hashmap.entrySet();
        Iterator<Map.Entry<String, String>> iteratorForMap = entrySet.iterator();
        while (iteratorForMap.hasNext()) {
            Map.Entry<String, String> entry = iteratorForMap.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // 存在確認
        if (hashmap.containsKey("tokyo")) {
            System.out.println("contains tokyo");
        }
        if (hashmap.containsValue("東京")) {
            System.out.println("contains 東京");
        }

        //endregion
    }
}