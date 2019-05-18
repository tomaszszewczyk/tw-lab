package lab2;

/**
 * Created by Patryk on 2015-04-29.
 */
public class Counter {
    private int _val;
    private Semaphore semaphore;

    public Counter(int n, Semaphore semaphore) {
        _val = n;
        this.semaphore = semaphore;
    }

    public void inc() {
        _val++;
    }
    public void dec() {
        _val--;
    }

    public int value() {
        return _val;
    }
}