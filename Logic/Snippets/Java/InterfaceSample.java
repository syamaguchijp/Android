import java.lang.ref.WeakReference;

public class InterfaceSample {

    public static void main(String args[]) {
         Caller caller = new Caller();
         caller.execute();
    }
}

// デリゲートパターン
interface SomeCallback {
    public void didCallBack(int number);
}

class Callee {
    WeakReference<SomeCallback> callbackRef; // 弱参照で

    void execute(){
        if (callbackRef != null) {
            SomeCallback callback = callbackRef.get();
            callback.didCallBack(1);
        }
    }
}

// 呼び出し元
class Caller implements SomeCallback {
    Callee callee = new Callee();

    Caller() {
        callee.callbackRef = new WeakReference<SomeCallback>(this);
    }

    void execute() {
        callee.execute();
    }

    public void didCallBack(int number) {
        System.out.println("didCallBack!!!!");
    }
}
