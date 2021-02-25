public class ThreadSample {

    public static void main(String args[]) {
        
        System.out.println("MainThread 1");
        MySubThread subThread = new MySubThread();
        subThread.start();
        System.out.println("MainThread 2");

        // Join（別スレッドが終了するまで待機させる）
        try {
            MySubThread thread1 = new MySubThread();
            MySubThread thread2 = new MySubThread();

            thread1.start();
            thread1.join();  // thread1が終了するまで待機して、thread2を実行させない
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 排他制御
        MySubThread thread10 = new MySubThread();
        thread10.hoge();
        MySubThread thread11 = new MySubThread();
        thread11.hoge();

    }
}

class MySubThread extends Thread {
    public void run() {
        try {
            System.out.println("MySubThread 1");
            Thread.sleep(2000);
            System.out.println("MySubThread 2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // synchronized修飾子でロックして排他制御
    synchronized public void hoge() {
        try {
            System.out.println("hoge 1");
            Thread.sleep(3000);
            System.out.println("hoge 2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

