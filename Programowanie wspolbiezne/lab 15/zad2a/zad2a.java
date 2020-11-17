package zad2a;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.concurrent.*;

class zad2a<active> {

  ArrayList<Pasazer> subscribers = new ArrayList<Pasazer>();

  void subscribe(Pasazer s) {
    subscribers.add(s);
  }

  public void post(String message) {
    CompletableFuture.runAsync(
      () -> {
        for (Pasazer s : subscribers) {
          s.post(message);
        }
      }
    );
  }

  static class Pasazer<active> {

    private String name;

    public Pasazer(String name) {
      this.name = name;
    }

    public void post(String message) {
      System.out.println(name + " wszedł " + message);
    }
  }

  public static class Elevator {

    public static void main(String args[]) {
      zad2a d = new zad2a();
      Pasazer a = new Pasazer("pasażer a");

      CompletableFuture.runAsync(
        () -> {
          d.subscribe(a);
        }
      );
      d.post("winda A");
      Pasazer b = new Pasazer("pasażer b");
      CompletableFuture.runAsync(
        () -> {
          d.subscribe(b);
        }
      );
      d.post("winda B");
      Pasazer c = new Pasazer("pasażer c");
      CompletableFuture.runAsync(
        () -> {
          d.subscribe(c);
        }
      );
      d.post("winda C");
    }
  }
}
