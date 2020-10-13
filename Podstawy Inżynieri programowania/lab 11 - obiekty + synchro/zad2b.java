class Test {
    void printTable(int n) {
        synchronized (this) {
            for (int i = 1; i <= 5; i++) {
                System.out.println(n * i);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}

class MyThread1 extends Thread {
    Test t;

    MyThread1(Test t) {
        this.t = t;
    }

    public void run() {
        t.printTable(5);
    }

}

class MyThread2 extends Thread {
    Test t;

    MyThread2(Test t) {
        this.t = t;
    }

    public void run() {
        t.printTable(100);
    }
}

public class zad2b {
    public static void main(String args[]) {
        Test obj = new Test();
        MyThread1 t1 = new MyThread1(obj);
        MyThread2 t2 = new MyThread2(obj);
        t1.start();
        t2.start();
    }
}