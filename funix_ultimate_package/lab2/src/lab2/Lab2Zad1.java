package lab2;

/**
 * Created by Patryk on 2015-03-26.
 */
public class Lab2Zad1 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore();
        Counter cnt = new Counter(0, semaphore);
        IThread it = new IThread(cnt, semaphore);
        DThread dt = new DThread(cnt, semaphore);

        it.start();
        dt.start();

        try {
            it.join();
            dt.join();
        } catch(InterruptedException ie) {
            System.out.println(ie.getMessage());
        }

        System.out.println("value = " + cnt.value());
    }
}

