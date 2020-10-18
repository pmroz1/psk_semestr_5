class Value {
    synchronized public void GetVal() {
        for (int i = 0; i < 10; ++i) {
            System.out.println(i);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class ChangeValue extends Thread {
    Value val;

    ChangeValue(Value line) {
        this.val = line;
    }

    @Override
    public void run() {
        val.GetVal();
    }
}

public class zad2c {
    public static void main(String[] args) {
        Value obj = new Value();

        ChangeValue train1 = new ChangeValue(obj);
        ChangeValue train2 = new ChangeValue(obj);

        train1.start();
        train2.start();
    }
}