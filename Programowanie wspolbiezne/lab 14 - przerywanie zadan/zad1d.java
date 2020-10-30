import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class zad1d {
    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        Integer poison = -1;
        new Thread(new Serv(queue, poison)).start();
        //new Thread(new Serv(queue, poison)).start();
        new Thread(new Client(queue, poison)).start();
        //new Thread(new Client(queue, poison)).start();
    }
}

class Serv implements Runnable {

    private final BlockingQueue<Integer> queue;
    private final Integer POISON;

    public Serv(BlockingQueue<Integer> queue, Integer POISON) {
        this.queue = queue;
        this.POISON = POISON;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("finally");
            while (true) {
                try {
                    queue.put(POISON);
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void process() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            queue.put(i);
            System.out.println("Serv dodano : " + i);
            System.out.println("Serv rozmiar kolejki : " + queue.remainingCapacity());
            Thread.sleep(100);
        }
    }
}

class Client implements Runnable {

    private final BlockingQueue<Integer> queue;
    private final Integer POISON;

    public Client(BlockingQueue<Integer> queue, Integer POISON) {
        this.queue = queue;
        this.POISON = POISON;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer take = queue.take();
                process(take);
                if (take == POISON) {
                    System.out.println("ARGH ZATRUTO MNIE !! *kolejka umiera*");
                    break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    private void process(Integer take) throws InterruptedException {
        System.out.println("Klient sciagam z kolejki : " + take);
        Thread.sleep(500);
    }
}