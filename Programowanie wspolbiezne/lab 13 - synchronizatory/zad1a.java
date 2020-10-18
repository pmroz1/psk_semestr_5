import java.util.concurrent.CountDownLatch;

public class zad1a {

  public static void main(String args[]) throws InterruptedException {
    CountDownLatch threadListener = new CountDownLatch(3);

    MyThread th1 = new MyThread(threadListener, 1);
    MyThread th2 = new MyThread(threadListener, 2);
    MyThread th3 = new MyThread(threadListener, 3);

    th1.start();
    th2.start();
    th3.start();

    threadListener.await(); // no czekamy na wykonanie wątków
    System.out.println("All threads finished");
  }
}

class MyThread extends Thread {
  protected CountDownLatch listener;
  private int thNumber;

  public MyThread(CountDownLatch listener, int thInfo) {
    this.listener = listener;
    this.thNumber = thInfo;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(1000 * thNumber);
      listener.countDown(); // dekremeentuje zawartość couuntdownlatch
      System.out.println(
        "Inside -> " + Thread.currentThread().getName() + ", Thread Finished"
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
