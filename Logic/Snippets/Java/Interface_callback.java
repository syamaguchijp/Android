// Interfaceを利用したコールバック

interface CallBackListener {
    public void onFinishedHoge(boolean isSuccess, String errorCode);
}

class User {
    void execute(CallBackListener callback) {
        System.out.println("execute");
        if (callback != null) {
            callback.onFinishedHoge(true, "NO_ERROR");
        }
    }
}

public class Interface_callback {

    public static void main(String args[]) {
        
        User user = new User();
        user.execute(new CallBackListener() {
            // コールバック処理
            @Override
            public void onFinishedHoge(boolean isSuccess, String errorCode) {
                System.out.println("onFinishedHoge!!!");
            }
        });
    }
}

