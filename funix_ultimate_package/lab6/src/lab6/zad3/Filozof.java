package lab6.zad3;

public class Filozof extends Thread {
    private int _licznik = 0;
    private int num;
    private Lokaj lokaj;

    public Filozof(int num, Lokaj lokaj){
        this.num = num;
        this.lokaj = lokaj;
    }

    public void run() {
        long startTime = System.currentTimeMillis();

        int lewyWidelec = ((num == 0) ? 4 : num-1);
        int prawyWidelec = ((num == 4) ? 0 : num+1);

        while(true) {
            lokaj.podajWidelce(lewyWidelec, prawyWidelec);
            ++_licznik;
            lokaj.zabierzWidelce(lewyWidelec, prawyWidelec);

            if (_licznik == 1000000) {
                System.out.println("Filozof: " + Thread.currentThread() +
                        "jadlem " + _licznik + " razy" + " czas:" +
                        (System.currentTimeMillis() - startTime));
                break;
            }
        }
    }
}
