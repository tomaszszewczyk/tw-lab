package lab3.zad2;

/**
 * Created by Patryk on 2015-04-09.
 */
public class Consumer2 extends Thread {
    private Buffer2 _buf;
    private int num;
    private int iterations;
    private Semaphore semaphore;

    public Consumer2(Buffer2 buf, int num, int iterations, Semaphore semaphore) {
        this._buf = buf;
        this.num = num;
        this.iterations = iterations;
        this.semaphore = semaphore;
    }

    public void run() {
        for (int i = 0; i < iterations; ++i) {
            try {
                this.semaphore.P();
                _buf.get();
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } finally {
                this.semaphore.V();
            }
        }
    }
}