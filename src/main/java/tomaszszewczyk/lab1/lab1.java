package tomaszszewczyk.lab1;

public class lab1 {
    public static void main(String[] args) throws InterruptedException {
        final long startTime = System.currentTimeMillis();
        final Counter myCounter = new Counter();

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

class Counter extends Thread {
    private int val;
    Counter() {
        this.val = 0;
    }
    void inc() {
        this.val++;
    }
    void dec() {
        this.val--;
    }
    int getVal() {
        return val;
    }
}