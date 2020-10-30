import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class zad2b {
    public static void main(String[] args) {
        Dataset printerQueue = new Dataset();
        try {
            Thread thread[] = new Thread[10];
            for (int i = 0; i < 5; i++) {
                thread[i] = new Thread(new Job(printerQueue), "Thread " + i);
                Thread.sleep(50);
            }
            for (int i = 0; i < 5; i++) {
                thread[i].start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class Job implements Runnable {
    private Dataset printerQueue;

    public Job(Dataset printerQueue) {
        this.printerQueue = printerQueue;
    }

    @Override
    public void run() {
        System.out.printf("Locking thread %s\n", Thread.currentThread().getName());
        printerQueue.printJob(new Object());
    }
}

class Dataset {
    private final Lock queueLock = new ReentrantLock();

    public void printJob(Object document) {
        queueLock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(
                    Thread.currentThread().getName() + ": waslocked for " + (duration / 1000) + " seconds time  ");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.printf("blokowano wątek przez ->", Thread.currentThread().getName());
            queueLock.unlock();
        }
    }
}