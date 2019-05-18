package lab6.zad1;

/**
 * Created by Patryk on 2015-04-30.
 */
public class Lab6Zad1 {
    private static final int N = 5;

    public static void main(String[] args) {
        Filozof[] filozofs = new Filozof[N];
        Widelec[] widelce = new Widelec[N];

        for(int i=0; i<N; i++) {
            filozofs[i] = new Filozof();
            widelce[i] = new Widelec(i);
        }

        //przypisanie widelcow
        for(int i=0; i<N; i++) {
            filozofs[i].setLewy(widelce[i]);
        }

        for(int i=0; i<N; i++) {
            if(i==0) {
                filozofs[i].setPrawy(widelce[4]);
            } else {
                filozofs[i].setPrawy(widelce[i - 1]);
            }
        }

        //uruchomienie watkow z filozofami
        for(Filozof f : filozofs) {
            f.start();
        }

        for(Filozof f : filozofs) {
            try {
                f.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
