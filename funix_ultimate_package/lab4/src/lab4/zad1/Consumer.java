package lab4.zad1;

/**
 * Created by Patryk on 2015-04-16.
 */
public class Consumer extends Thread {
    private Buffer _buf;

    public Consumer(Buffer buf) {
        this._buf = buf;
    }

    public void run() {
        while(true) {
            _buf.get((int) (Math.random() * 10 + 1));
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
