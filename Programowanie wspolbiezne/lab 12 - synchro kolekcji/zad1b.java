import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//As its name implies, it allows us to create synchronized Sets with minimal fuss.
public class zad1b {
    public static void main(String[] args) {
        Set<String> mySet = Collections.synchronizedSet(new HashSet<String>());
        ;
        Thread th1 = new Thread(new Zadanko((mySet), true));
        Thread th2 = new Thread(new Zadanko((mySet), false));

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
    private boolean first;

    public Zadanko(Set<String> val, boolean isFirst) {
        this.first = isFirst;
        this.insideSet = val;
    }

    @Override
    public void run() {
        System.out.println("Metoda run() cklasy zadanko" + Thread.currentThread().getName());
        // String str = Thread.currentThread().getName();
        int i;
        if (first) {
            for (i = 0; i < 5; i++) {
                insideSet.add(String.valueOf(i * 100));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            for (i = 0; i < 5; i++) {
                insideSet.add(String.valueOf(i * 200));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}