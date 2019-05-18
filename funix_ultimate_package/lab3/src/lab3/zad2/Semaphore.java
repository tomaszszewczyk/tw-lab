package lab3.zad2;

/**
 * Created by Patryk on 2015-04-09.
 */
public class Semaphore {
    private boolean _stan;
    private int _czeka;

    public Semaphore() {
        this._stan = true;
        this._czeka = 0;
    }

    public synchronized void P() throws InterruptedException {
        //opuszczenie semafora - acquire
        _czeka++;
        while(!_stan) {
            try {
                wait();
            } catch (InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
        }
        _czeka--;
        _stan = false;
    }

    public synchronized void V() {
        //podniesienie semafora - release
        if(_czeka > 0) {
            this.notify();
        }
        _stan = true;
    }
}
