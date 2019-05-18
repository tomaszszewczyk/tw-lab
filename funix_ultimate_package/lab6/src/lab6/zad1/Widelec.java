package lab6.zad1;

/**
 * Created by Patryk on 2015-04-30.
 */
public class Widelec {
    private boolean available = true;
    private int num;

    public Widelec(int num) {
        this.num = num;
    }

    public synchronized void podnies() {
        this.available = false;
    }

    public synchronized void odloz() {
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getNum() {
        return num;
    }
}
