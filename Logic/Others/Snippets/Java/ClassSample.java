
public class ClassSample {

    public static void main(String args[]) {
        User user = new User();
        User user2 = new User("Yamada");
        user.setAddress("Tokyo");
        System.out.println(user.getAddress());

        // 継承
        //SpecialUser spu = new SpecialUser("Takeda"); <- コンストラクタやプライベート変数は継承されない
        SpecialUser spu = new SpecialUser();
        spu.dump();
    }
}

class User {

    String name = "No Name";
    private String address;

    //　コンストラクタ   
    public User() {
        System.out.println(name);
    }

    // コンストラクタをオーバーロード
    public User(String name) {
        this.name = name;
        System.out.println(this.name);
    }

    // プロパティ　getter & setter
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public void dump() {
        System.out.println(this.name);
    }
}

// Userクラスを継承
class SpecialUser extends User {
    // メソッドをオーバーライド
    @Override
    public void dump() {
        System.out.println("Special: " + this.name);
    }
}