package lab6.zad3;

/**
 * Created by Patryk on 2015-05-28.
 */
public class Lab6Zad3 {
    public static void main(String[] args) {
        Filozof[] f = new Filozof[5];
        Lokaj lokaj = new Lokaj();

        for(int i = 0; i < 5; i++) {
            f[i] = new Filozof(i, lokaj);
        }

        for(int i = 0; i < 5; i++) {
            f[i].start();
        }

        for(int i = 0; i < 5; i++){
            try {
                f[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

