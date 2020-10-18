import java.util.HashSet;
import java.util.Set;

public class zad1b {
    public static void main(String[] args) {
        Set<String> mySet = new HashSet<String>();
        Thread th1 = new Thread(new Zadanko(mySet));
        Thread th2 = new Thread(new Zadanko(mySet));

        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Po wykonaniu asynchroniczniie wszystkich operacji"
                + ", watki zwiększyły łącznie rozmiar HASHSETU do: " + mySet.size() + "\nZawartość setu:\n");

        for (String itr : mySet) {
            System.out.println(itr);
        }
    }
}

class Zadanko implements Runnable { // pozwala na korzystanie z wątków
    private Set<String> insideSet; // nasza kolejkcja xd

    public Zadanko(Set<String> val) {
        this.insideSet = val;
    }

    @Override
    public void run() {
        System.out.println("Metoda run() cklasy zadanko" + Thread.currentThread().getName());
        String str = Thread.currentThread().getName();
        int i;
        for (i = 0; i < 5; i++) {
            insideSet.add(str + " itr nr[" + i + "]");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}