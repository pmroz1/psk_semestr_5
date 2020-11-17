package zad2b;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.concurrent.*;

class zad2a<active> {

  ArrayList<Uzytkownik> subscribers = new ArrayList<Uzytkownik>();

  void subscribe(Uzytkownik s) {
    subscribers.add(s);
  }

  public void post(String message) {
    CompletableFuture.runAsync(
      () -> {
        for (Uzytkownik s : subscribers) {
          s.post(message);
        }
      }
    );
  }

  static class Uzytkownik<active> {

    private String name;

    public Uzytkownik(String name) {
      this.name = name;
    }

    public void post(String message) {
      System.out.println(name + " drukuje na " + message);
    }
  }

  public static class Drukarka {

    public static void main(String args[]) {
      zad2a d = new zad2a();
      Uzytkownik a = new Uzytkownik("Użytkownik a");

      CompletableFuture.runAsync(
        () -> {
          d.subscribe(a);
        }
      );
      d.post("Drukarka A");
      Uzytkownik b = new Uzytkownik("Użytkownik b");
      CompletableFuture.runAsync(
        () -> {
          d.subscribe(b);
        }
      );
      d.post("Drukarka B");
      Uzytkownik c = new Uzytkownik("Użytkownik c");
      CompletableFuture.runAsync(
        () -> {
          d.subscribe(c);
        }
      );
      d.post("Drukarka C");
    }
  }
}
