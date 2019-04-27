package tomaszszewczyk;

public class lab2 {
    public static void main(String[] args) throws InterruptedException {
        final long startTime = System.currentTimeMillis();
        final CounterWithSemaphore myCounter = new CounterWithSemaphore();

        Thread firstThread = new Thread(){
            public void run() {
                for(int i=0; i<1000000; i++) {
                    myCounter.inc();
                }
            }
        };

        Thread secondThread = new Thread(){
            public void run() {
                for(int i=0; i<1000000 ; i++) {
                    myCounter.dec();
                }
            }
        };

        firstThread.start();
        secondThread.start();
        secondThread.join();
        firstThread.join();
        final long endTime = System.currentTimeMillis();
        System.out.println(myCounter.getVal());
        System.out.println(endTime - startTime);
    }
}

class CounterWithSemaphore extends Thread {
    private int val;
    private Semaphore sem;

    CounterWithSemaphore() {
        this.val = 0;
        this.sem = new Semaphore();
    }
    void inc() {
        sem.acquire();
        this.val++;
        sem.release();
    }
    void dec() {
        sem.acquire();
        this.val--;
        sem.release();
    }
    int getVal() {
        return val;
    }
}

class Semaphore {

    private enum SemState {ACQUIRED, RELEASED}
    private SemState state;
    private int waiting;

    Semaphore() {
        this.state = SemState.RELEASED;
        this.waiting = 0;
    }

    synchronized void acquire() {
        waiting++;
        while(state == SemState.ACQUIRED) {
            try {
                wait();
            } catch (InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
        }
        waiting--;
        state = SemState.ACQUIRED;
    }

    synchronized void release() {
        if(waiting > 0) {
            this.notify();
        }
        state = SemState.RELEASED;
    }
}