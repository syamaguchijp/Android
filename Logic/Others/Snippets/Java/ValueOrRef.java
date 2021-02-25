import java.util.HashMap;

// 値型と参照型のメモ
public class ValueOrRef {

    public static void main(String args[]) {
        
        Test test = new Test();

        // 値型
        String hoge = "hoge";
        String hoge2 = test.method1(hoge);
        System.out.println(hoge);
        System.out.println(hoge2);

        // 参照型

        String[] strAry = new String[]{"Tokyo", "Osaka", "Hakata"};
        String[] strAry2 = test.method2(strAry);
        System.out.println(strAry[1]);
        System.out.println(strAry2[1]);

        HashMap<String, String> hashmap = new HashMap<String, String>();
        hashmap.put("a", "Tokyo");
        hashmap.put("b", "Osaka");
        hashmap.put("c", "Kobe");
        HashMap<String, String> hashmap2 = test.method3(hashmap);
        System.out.println(hashmap.get("b"));
        System.out.println(hashmap2.get("b"));

        Person person = new Person();
        test.method4(person);
        System.out.println("person.name=" + person.name);

        Person person3 = person; // コピーされずに同じものの参照となる
        person3.name = "Person3";
        System.out.println("person.name=" + person.name); // こちらも"Person3"となる
        System.out.println("person3.name=" + person3.name);
    }
}

class Person {
    String name;
}

class Test {
    // 値型
    
    // 値型の値渡し（String）
    String method1(String str) {
        //str = "XXX" //<- コンパイルエラー
        String ans = str; // 値型のためcopyされて別物となる
        ans = "XXX";
        return ans;
    }
    
    // 参照型

    // 参照型の値渡し（Array）
    String[] method2(String[] array) {
        array[1] = "XXXX"; //<- コンパイルエラーにならない
        String[] ans = array; // 参照
        ans[1] = "ZZZZ";
        return ans;
    }

    // 参照型の値渡し（Dictionary）
    HashMap<String, String> method3(HashMap<String, String> dict) {
        dict.replace("b", "XXXX");
        HashMap<String, String> ans = dict; // 参照
        ans.replace("b", "ZZZZ");
        return ans;
    }

    // 参照型の値渡し
    void method4(Person person) {
        person.name = "Yamada";
    }
    
}
