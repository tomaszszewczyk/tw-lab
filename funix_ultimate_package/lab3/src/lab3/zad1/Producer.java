package lab3.zad1;

/**
 * Created by Patryk on 2015-04-09.
 */
public class Producer extends Thread {
    private Buffer _buf;
    private int iterations;

    public Producer(Buffer buf, int iterations)  {
        this._buf = buf;
        this.iterations = iterations;
    }

    public void run() {
        for (int i = 0; i < iterations; ++i) {
            _buf.put(i);
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}