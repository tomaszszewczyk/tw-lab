package tomaszszewczyk.lab7b;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lab7b {
    public static void main(String[] args) throws InterruptedException {
        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();

        Man man1 = new Man(1, fork1, fork2);
        Man man2 = new Man(2, fork2, fork3);
        Man man3 = new Man(3, fork3, fork4);
        Man man4 = new Man(4, fork4, fork5);
        Man man5 = new Man(5, fork5, fork1);

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

class Fork {
    private Lock lock = new ReentrantLock();

    public void pick() {
        lock.lock();
    }

    public void release() {
        lock.unlock();
    }
}

class Man extends Thread {
    private int counter = 0;
    private Fork fork1;
    private Fork fork2;
    private int id;
    private Lock lock = new ReentrantLock();

    public Man(int new_id, Fork f1, Fork f2) {
        id = new_id;
        fork1 = f1;
        fork2 = f2;
    }

    public void run() {
        while (true) {
            lock.lock();
            fork1.pick();
            fork2.pick();
            lock.unlock();

            counter++;
            System.out.println("Filozof: " + id + " jadlem " + counter + " razy");

            fork1.release();
            fork2.release();
        }
    }
}
