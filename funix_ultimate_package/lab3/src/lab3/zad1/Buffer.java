package lab3.zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Patryk on 2015-04-09.
 */
public class Buffer {
    private List<Integer> buf = new ArrayList<Integer>();
    private int M;

    public Buffer(int m) {
        this.M = m;
    }

    public synchronized void put(int i) {
        while(buf.size() >= M) {
            try {
                System.out.println("\tProducer waits.");
                wait();
            } catch(InterruptedException iexp) {
                System.out.println(iexp.getMessage());
            }
        }

        System.out.println("Producer puts " + i);
        buf.add(i);
        notify();
    }

    public synchronized int get() {
        while(buf.size() == 0) {
            try {
                System.out.println("\tConsumer waits.");
                wait();
            } catch(InterruptedException iexp) {
                System.out.println(iexp.getMessage());
            }
        }

        int index = new Random().nextInt(buf.size());
        int returnVal = buf.get(index);
        buf.remove(index);
        notify();
        System.out.println("Consumer gets " + returnVal);
        return returnVal;
    }
}