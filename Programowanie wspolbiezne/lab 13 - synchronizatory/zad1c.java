import java.util.concurrent.Phaser;

// no generalnie to ustawia się fazy które wykonują się kolejno
public class zad1c {

  public static void main(String[] args) {
    Phaser phaser = new Phaser();
    int currentPhase;

    System.out.println("Running Phaser: ");
    Phase p1 = new Phase(phaser, "First");
    Phase p2 = new Phase(phaser, "Second");
    Phase p3 = new Phase(phaser, "Third");
    p1.start();
    try { // sprawia że odpalają się w ładnej kolejności xd
      Thread.sleep(100);
    } catch (Exception e) {
      e.printStackTrace();
    }
    p2.start();
    p3.start();

    currentPhase = phaser.getPhase();
    phaser.arriveAndAwaitAdvance();
    System.out.println("Phase done, phase : " + ++currentPhase);
    System.out.println("Phase one Ended");

    currentPhase = phaser.getPhase();
    phaser.arriveAndAwaitAdvance();
    System.out.println("Phase done, phase : " + ++currentPhase);
    System.out.println("Phase two Ended");

    phaser.arriveAndDeregister();
    if (phaser.isTerminated()) {
      System.out.println("Phaser is terminated");
    }
  }
}

class Phase extends Thread {
  Phaser phaser;
  String name;

  public Phase(Phaser phaser, String name) {
    this.phaser = phaser;
    this.name = name;

    phaser.register();
  }

  @Override
  public void run() {
    System.out.println("Thread: " + name + " -> Phase One Started");
    phaser.arriveAndAwaitAdvance();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      System.out.println(e);
    }

    System.out.println("Thread: " + name + " -> Phase two Started");
    phaser.arriveAndAwaitAdvance();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }
}
