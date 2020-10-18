import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class zad1b {

  //private CyclicBarrier cb;

  public static void main(String[] args) {
    CyclicBarrier cb = new CyclicBarrier(3, new WhenDone());

    Job th1 = new Job(cb, 1);
    Job th2 = new Job(cb, 2);
    Job th3 = new Job(cb, 3);
    th1.start();
    th2.start();
    th3.start();

    System.out.println("Done ");
  }
}

class Job extends Thread {
  private CyclicBarrier listener;
  private int thNumber;

  public Job(CyclicBarrier listener, int thInfo) {
    this.listener = listener;
    this.thNumber = thInfo;
  }

  @Override
  public void run() {
    System.out.println("Insied thread : " + thNumber);
    try {
      // calling await so the current thread suspends
      listener.await();
    } catch (InterruptedException e) {
      System.out.println(e);
    } catch (BrokenBarrierException e) {
      System.out.println(e);
    }
  }
}

class WhenDone implements Runnable {

  @Override
  public void run() {
    System.out.println("all threads finished work");
  }
}
