import java.util.concurrent.Semaphore;

public class zad1e {

  public static void main(String args[]) throws InterruptedException {
    Semaphore sm = new Semaphore(1);
    SemJob th1 = new SemJob(sm, "first");
    SemJob th2 = new SemJob(sm, "second");

    th1.start();
    th2.start();

    th1.join();
    th2.join();

    System.out.println("Final value: " + Shared.iterator);
  }
}

class Shared {
  static int iterator = 0;
}

class SemJob extends Thread {
  Semaphore sm;
  String name;

  public SemJob(Semaphore sm, String threadName) {
    super(threadName);
    this.sm = sm;
    this.name = threadName;
  }

  @Override
  public void run() {
    if (this.getName().equals("first")) {
      try {
        System.out.println("Starting thread" + name);
        sm.acquire();
        System.out.println(name + " acquired access");
        for (int i = 0; i < 5; i++) {
          ++Shared.iterator;
          System.out.println(name + ": " + Shared.iterator);
          Thread.sleep(10);
        }
      } catch (InterruptedException e) {
        System.out.println(e + "<-- przerwany wątek :d");
      }
      System.out.println(name + " access droped.");
      sm.release();
      System.out.println("Iterator value: " + Shared.iterator);
    } else {
      try {
        System.out.println("Starting thread" + name);
        sm.acquire();
        System.out.println(name + " acquired access");
        for (int i = 0; i < 5; i++) {
          --Shared.iterator;
          System.out.println(name + ": " + Shared.iterator);
          Thread.sleep(10);
        }
      } catch (InterruptedException e) {
        System.out.println(e + "<-- przerwany wątek :d");
      }
      System.out.println(name + " access droped.");
      sm.release();
    }
  }
}
