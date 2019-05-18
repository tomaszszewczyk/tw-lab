package lab4.zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Patryk on 2015-04-16.
 */
public class Buffer {
    private List<Integer> buf = new ArrayList<Integer>();
    private int M;

    public Buffer(int m) {
        this.M = 2*m;
    }

    public synchronized void put(int[] a) {
        while(M - buf.size() < a.length) {
            try {
                System.out.println("\tProducer waits.");
                wait();
            } catch(InterruptedException iexp) {
                System.out.println(iexp.getMessage());
            }
        }

        for(int j=0; j<a.length; j++) {
            buf.add(a[j]);
            System.out.println("Producer puts " + a[j]);
        }
        notify();
    }

    public synchronized int get(int a) {
        while(buf.size() < a) {
            try {
                System.out.println("\tConsumer waits.");
                wait();
            } catch(InterruptedException iexp) {
                System.out.println(iexp.getMessage());
            }
        }

        int returnVal = 0;
        for(int i = 0; i < a; i++) {
            int index = new Random().nextInt(buf.size());
            returnVal = buf.get(index);
            buf.remove(index);
            notify();
            System.out.println("Consumer gets " + returnVal);
        }
        return returnVal;
    }
}