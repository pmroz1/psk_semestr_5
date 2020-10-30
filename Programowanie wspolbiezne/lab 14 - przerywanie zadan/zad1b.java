import java.util.concurrent.TimeUnit;

class InterruptableTask extends Thread {
    public void Anuluj() {
        System.out.println("Anuluj: " + System.currentTimeMillis());
        interrupt(); // no tutej siem anuluje
    }

    @Override
    public void run() {
        System.out.println("Rozpoczęto:     " + System.currentTimeMillis());
        try {
            while (!isInterrupted()) {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("POmiędzy:   " + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(10);
            }
        } catch (InterruptedException e) {
            System.out.println("Przewano w: " + System.currentTimeMillis() + " " + isInterrupted());
        }
        System.out.println("Zakończony w: " + System.currentTimeMillis());
    }
}

public class zad1b {
    public static void main(String[] args) throws InterruptedException {
        InterruptableTask thread = new InterruptableTask();
        thread.start();
        TimeUnit.MILLISECONDS.sleep(500);
        thread.Anuluj();
    }
}