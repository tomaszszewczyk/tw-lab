package lab2;

/**
 * Created by Patryk on 2015-04-29.
 */
public class DThread extends Thread {
    private Counter _cnt;
    private Semaphore semaphore;

    public DThread(Counter c, Semaphore semaphore) {
        _cnt = c;
        this.semaphore = semaphore;
    }
    public void run() {
        for (int i = 0; i < 1000000; ++i) {
            try {
                this.semaphore.P();
                _cnt.dec();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.semaphore.V();
            }
        }
    }
}