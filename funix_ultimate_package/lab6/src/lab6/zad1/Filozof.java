package lab6.zad1;

/**
 * Created by Patryk on 2015-04-30.
 */
public class Filozof extends Thread {
    private int _licznik = 0;
    private Widelec lewy;
    private Widelec prawy;
    private long startTime;
    private long endTime;

    public void run() {
        while(true) {
            think();
            synchronized(this) {
                pickLeftWidelec();
                pickRightWidelec();
            }
            //jedzenie
            eat();
            ++_licznik;
            if(_licznik % 100 == 0) {
                System.out.println("Filozof: " + Thread.currentThread() +
                    " jadlem " + _licznik + " razy.");
            }
            //koniec jedzenia
            synchronized (this) {
                putLeftBack();
                putRightBack();
            }
            checkIfNotDead();
//            putBothWidelecDown();
        }
    }

    private void checkIfNotDead() {
        if(endTime - startTime > 2000)
            System.out.println("Filozof: " + Thread.currentThread() + " nie zyje.");
    }

    private void eat() {
        System.out.println("Filozof: " + Thread.currentThread() + " je.");
        try {
            sleep((long) (Math.random() * 50 + 1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void putRightBack() {
        System.out.println("Filozof: " + Thread.currentThread() + " odlozyl prawy.");
        prawy.odloz();
    }

    private void putLeftBack() {
        System.out.println("Filozof: " + Thread.currentThread() + " odlozyl lewy.");
        lewy.odloz();
    }

    private void putBothWidelecDown() {
        lewy.odloz();
        prawy.odloz();
    }

    private void pickRightWidelec() {
        synchronized (this) {
            while (!prawy.isAvailable()) {
                try {
                    Thread.currentThread().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Filozof: " + Thread.currentThread() + " podniosl prawy: " + prawy.getNum());
        prawy.podnies();
    }

    private void pickLeftWidelec() {
        synchronized (this) {
            while (!lewy.isAvailable()) {
                try {
                    Thread.currentThread().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Filozof: " + Thread.currentThread() + " podniosl lewy: " + lewy.getNum());
        lewy.podnies();
    }

    public void think() {
        System.out.println("Filozof: " + Thread.currentThread() + " mysli.");
        try {
            sleep((long) (Math.random() * 100 + 1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setLewy(Widelec lewy) {
        this.lewy = lewy;
    }

    public void setPrawy(Widelec prawy) {
        this.prawy = prawy;
    }
}
