package tomaszszewczyk.lab6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class lab6 {
    public static void main(String[] args) throws InterruptedException {
        class Writer extends Thread {
            private List list;

            public Writer(List aList) {
                list = aList;
            }

            public void run() {
                for(int i = 0; i < 1000; i++)
                    list.add(i);
            }
        }

        class Searcher extends Thread {
            private List list;

            public Searcher(List aList) {
                list = aList;
            }

            public void run() {
                for (int i = 1000; i >= 0; i--)
                    if (list.contains(i)) {
                        System.out.println("Found " + i);
                    } else {
                        System.out.println("Not found " + i);
                    }
            }
        }

        List list = new List(-1);
        Writer w = new Writer(list);
        Searcher s = new Searcher(list);

        w.start();
        s.start();

        w.join();
        s.join();
    }
}

class List {
    private Object data;
    private List next;
    private Lock lock = new ReentrantLock();

    public List(Object o) {
        data = o;
    }

    public List(Object o, List n) {
        data = o;
        next = n;
    }

    public Object get() {
        return data;
    }

    public List getNext() {
        return next;
    }

    public boolean contains(Object o) {
        boolean result;
        lock.lock();
        try {
            if(o == data) {
                result = true;
            } else if (next != null) {
                result = next.contains(o);
            } else {
                result = false;
            }
        } finally {
            lock.unlock();
        }

        return result;
    }

    public List remove(Object o) {
        List result;
        lock.lock();
        try {
            if(o == data) {
                result = next;
            } else if (o == next.get()) {
                next = next.getNext();
                result = this;
            } else {
                next.remove(o);
                result = this;
            }
        } finally {
            lock.unlock();
        }

        return result;
    }

    public void add(Object o) {
        lock.lock();
        try {
            List old_next = next;
            next= new List(o, old_next);
        } finally {
            lock.unlock();
        }
    }

}