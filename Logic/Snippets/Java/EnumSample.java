
public class EnumSample {

    public static void main(String args[]) {

        System.out.println(Season.SPRING);
        System.out.println(Season.SPRING.ordinal());
        if (Season.SUMMER == Season.valueOf("SUMMER")) {
            System.out.println("SUMMER equals");
        }
        for (Season s : Season.values()) {
            System.out.println(s);
        }

        System.out.println(City.OSAKA);
        System.out.println(City.OSAKA.ordinal());
        for (City city : City.values()) {
            System.out.println(city);
            System.out.println(city.name());
        }
    }
}

enum Season {
    SPRING,
    SUMMER,
    AUTUMN,
    WINTER
}

enum City {
    TOKYO("東京"),
    NAGOYA("名古屋"),
    OSAKA("大阪");

    private String hoge;
    
    // コンストラクタ
    private City(String hoge) {
        this.hoge = hoge;
    }
}
