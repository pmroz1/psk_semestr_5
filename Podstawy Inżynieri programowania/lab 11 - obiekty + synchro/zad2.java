
public class zad2 {

    private static volatile int placeholder = 0;

    public static void main(String[] args) {
        new Check().start();
        new Change().start();
    }

    static class Check extends Thread {
        @Override
        public void run() {
            int xd = placeholder;
            while (xd < 5) {
                if (xd != placeholder) {
                    System.out.println("val in cahnge maker: " + placeholder);
                    xd = placeholder;
                }
            }
        }
    }

    static class Change extends Thread {
        @Override
        public void run() {
            int heh = placeholder;
            while (placeholder < 5) {
                System.out.println("val in cahnge maker: " + placeholder);
                placeholder = ++heh;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}