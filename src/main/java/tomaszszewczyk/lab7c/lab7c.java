package tomaszszewczyk.lab7c;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lab7c {
    public static void main(String[] args) throws InterruptedException {
        Waiter w = new Waiter(5);

        Man man1 = new Man(1, 0, 1, w);
        Man man2 = new Man(2, 1, 2, w);
        Man man3 = new Man(3, 2, 3, w);
        Man man4 = new Man(4, 3, 4, w);
        Man man5 = new Man(5, 4, 0, w);

        man1.start();
        man2.start();
        man3.start();
        man4.start();
        man5.start();

        man1.join();
        man2.join();
        man3.join();
        man4.join();
        man5.join();
    }
}

class Waiter {
    boolean[] in_use;
    Lock main_lock = new ReentrantLock();
    Condition someone_ends = main_lock.newCondition();

    Waiter(int count) {
        in_use = new boolean[count];
    }

    void please(int a, int b) {
        main_lock.lock();

        while (in_use[a] || in_use[b]) {
            try {
                someone_ends.await();
            } catch (Exception e) {
                System.out.println("interrupted");
            }
        }

        main_lock.unlock();
    }

    void thanks(int a, int b) {
        main_lock.lock();
        in_use[a] = false;
        in_use[b] = false;
        someone_ends.signal();
        main_lock.unlock();
    }
}

class Man extends Thread {
    private int counter = 0;
    private int fork1;
    private int fork2;
    private int id;
    private Waiter waiter;

    Man(int new_id, int f1, int f2, Waiter w) {
        id = new_id;
        fork1 = f1;
        fork2 = f2;
        waiter = w;
    }

    public void run() {
        while (true) {
            waiter.please(fork1, fork2);

            counter++;
            System.out.println("Filozof: " + id + " jadlem " + counter + " razy");

            waiter.thanks(fork1, fork2);
        }
    }
}
