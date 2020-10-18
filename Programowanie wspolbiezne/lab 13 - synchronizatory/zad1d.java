import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// generalnie to metody wymieniają się danymi w określonym punkcie :D

public class zad1d {

  public static void main(String[] args) throws InterruptedException {
    Exchanger<Integer> exchanger = new Exchanger<>();

    CreateDataSet t1 = new CreateDataSet(exchanger);
    HandleDataSet t2 = new HandleDataSet(exchanger);

    t1.start();
    t2.start();
    Thread.sleep(1000);
    t1.interrupt();
    t2.interrupt();
  }
}

class CreateDataSet extends Thread {
  Exchanger<Integer> ex;
  int x;
  int initialValue;

  public CreateDataSet(Exchanger<Integer> ex) {
    this.ex = ex;
    initialValue = 69;
    x = initialValue;
  }

  @Override
  public void run() {
    try {
      System.out.println("Wysyłam w exchange point -> " + initialValue);
      x = ex.exchange(x, 250, TimeUnit.MILLISECONDS);
      x = ex.exchange(x);
    } catch (InterruptedException e) {
      System.out.println(e + "<-- przerwany wątek :d");
    } catch (TimeoutException t) {
      System.out.println("Timeout exception!");
    }
  }
}

class HandleDataSet extends Thread {
  Exchanger<Integer> ex;
  int y;

  public HandleDataSet(Exchanger<Integer> ex) {
    this.ex = ex;
  }

  @Override
  public void run() {
    try {
      //System.out.println("im here");
      Thread.sleep(50);
      y = ex.exchange(y);
      System.out.println("Handler dostał -> " + y);
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }
}
