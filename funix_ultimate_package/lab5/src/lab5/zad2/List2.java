package lab5.zad2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Patryk on 2015-05-13.
 */
public class List2 {
    private Object val;
    private List2 next;
    private static Lock lock = new ReentrantLock();
    private static long sleepTime;

    public List2(Object val, List2 next) {
        this.val = val;
        this.next = next;
    }

    public boolean contains(Object o) throws InterruptedException {
        List2 tmp = this;
        lock.lock();
        try {
            while (tmp != null) {
                if (val == o) {
                    Thread.sleep(sleepTime / 10);
                    return true;
                }
                tmp = tmp.next;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    public boolean remove(Object o) throws InterruptedException {
        List2 prev = null, tmp = this;
        lock.lock();
        try {
            while (tmp != null) {
                if (val == o) {
                    if (prev != null) {
                        prev.next = tmp.next;
                        tmp.next = null;
                    }
                    Thread.sleep(sleepTime / 3);
                    return true;
                }
                prev = tmp;
                tmp = tmp.next;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    public boolean add(Object o) throws InterruptedException {
        if (o == null) {
            return false;
        }
        List2 tmp = this, next = this.next;
        lock.lock();
        try {
            while (next != null) {
                tmp = next;
                next = next.next;
            }
            tmp.next = new List2(o, null);
            Thread.sleep(sleepTime);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public static void setSleepTime(long sleepTime) {
        List2.sleepTime = sleepTime;
    }
}
