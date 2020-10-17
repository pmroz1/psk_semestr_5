
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class zad1f {
  static final int listeners = 5;

  public static void main(String[] args) {
    BlockingQueue<Integer> syncQueue = new SynchronousQueue<>();

    Homie homiee = new Homie(syncQueue);
    homiee.start();

    ServiceGuy[] sGuys = new ServiceGuy[listeners];

    for (int i = 0; i < listeners; i++) {
      sGuys[i] = new ServiceGuy(syncQueue);
      sGuys[i].start();
    }
  }
}

class Homie extends Thread {
  private BlockingQueue<Integer> q;

  public Homie(BlockingQueue<Integer> q) {
    this.q = q;
  }

  private int GetRandom() {
    Random gen = new Random();
    int number = gen.nextInt(100);
    try {
      Thread.sleep(gen.nextInt(100));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Added number to synced queue " + number);
    return number;
  }

  public void run() {
    while (true) {
      try {
        q.put(GetRandom());
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }
    }
  }
}

class ServiceGuy extends Thread {
  private BlockingQueue<Integer> q;

  public ServiceGuy(BlockingQueue<Integer> q) {
    this.q = q;
  }

  private void Service(int number) {
    String message = "Service in: " + getName();
    message += " Serviced number: " + number;
    System.out.println(message);
  }

  public void run() {
    while (true) {
      try {
        int number = q.take();
        Service(number);
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }
    }
  }
}
