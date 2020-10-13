public class zad2d {
    public static void main(String[] args) {
        MyThread mth = new MyThread();
        mth.start();
        synchronized (mth) {
            try {
                System.out.println("czekam na wątek");
                mth.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Total is: " + mth.total);
        }
    }
}

class MyThread extends Thread {
    int total;
    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                total += i;
            }
            notify();
        }
    }
}