public class zad1a {
    public static void main(String[] args) {
        Server a = new Server();
        a.setRunning(true);
        System.out.println("Running server");

        Process th = new Process(a);
        th.start();
    }
}

class Server {
    private volatile boolean isRunning = true;

    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * @param isRunning the isRunning to set
     */
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
        System.out.println(" setting to:" + isRunning);
    }
}

class Process extends Thread {

    private final Server server;

    public Process(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        int i = 0;
        while (server.isRunning()) {
            try {
                Thread.sleep(100);
                System.out.println(i);
                ++i;
                if (i > 10) {
                    server.setRunning(false);
                    System.out.println("interruptin thread");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}