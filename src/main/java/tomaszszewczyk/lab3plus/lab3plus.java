package tomaszszewczyk.lab3plus;

public class lab3plus {
    public static void main(String[] args) throws InterruptedException {

        test(new Buffer());
        test(new BufferWithSemaphore());
    }

    static private void test(AbstractBuffer myBuffer) throws InterruptedException {
        {
            myBuffer.clear();
            Producer producer = new Producer(myBuffer, 100);
            Consumer consumer = new Consumer(myBuffer, 100);

            System.out.println("p == c == 1");

            producer.start();
            consumer.start();

            consumer.join();
            producer.join();
        }

        {
            myBuffer.clear();
            Producer producer = new Producer(myBuffer, 75);
            Consumer consumer1 = new Consumer(myBuffer, 25);
            Consumer consumer2 = new Consumer(myBuffer, 25);
            Consumer consumer3 = new Consumer(myBuffer, 25);

            System.out.println("p < c");

            producer.start();
            consumer1.start();
            consumer2.start();
            consumer3.start();

            consumer1.join();
            consumer2.join();
            consumer3.join();
            producer.join();
        }

        {
            myBuffer.clear();
            Producer producer1 = new Producer(myBuffer, 25);
            Producer producer2 = new Producer(myBuffer, 25);
            Producer producer3 = new Producer(myBuffer, 25);
            Consumer consumer = new Consumer(myBuffer, 75);

            System.out.println("p > c");

            producer1.start();
            producer2.start();
            producer3.start();
            consumer.start();

            consumer.join();
            producer1.join();
            producer2.join();
            producer3.join();
        }
    }
}

class Producer extends Thread {

    private AbstractBuffer myBuffer;
    private int count;

    Producer(AbstractBuffer newBuffer, int newCount) {
        myBuffer = newBuffer;
        count = newCount;
    }

    public void run() {
        for(int i=0; i<count; i++) {
            myBuffer.put(Integer.toString(i));

            try{
                sleep(20, 0);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class Consumer extends Thread {

    private AbstractBuffer myBuffer;
    private int count;

    Consumer(AbstractBuffer newBuffer, int newCount) {
        myBuffer = newBuffer;
        count = newCount;
    }

    public void run() {
        for(int i=0; i<count; i++) {
            System.out.println(myBuffer.get());
        }
    }
}

interface AbstractBuffer {
    void clear();
    void put(String x);
    String get();
}

class Buffer implements AbstractBuffer {
    private String[] buf;
    private int start = 0;
    private int stop = 0;
    private enum BufState {ACQUIRED, RELEASED}
    private BufState state;

    Buffer() {
        buf = new String[100];
        state = BufState.RELEASED;
    }

    public void clear() {
        buf = new String[100];
        start = 0;
        stop = 0;
    }

    public synchronized void put(String x) {
        while(state == BufState.ACQUIRED) {
            try {
                wait();
            } catch (InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
        }
        state = BufState.ACQUIRED;

        buf[stop] = x;
        stop += 1;

        state = BufState.RELEASED;
        notify();
    }

    public synchronized String get() {
        while(state == BufState.ACQUIRED || start == stop) {
            try {
                wait();
            } catch (InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
        }
        state = BufState.ACQUIRED;

        String result = buf[start];
        start += 1;

        state = BufState.RELEASED;
        notify();

        return result;
    }
}

class BufferWithSemaphore implements AbstractBuffer {
    private String[] buf;
    private int start = 0;
    private int stop = 0;
    private Semaphore semaphore;

    BufferWithSemaphore() {
        buf = new String[100];
        semaphore = new Semaphore();
    }

    public void clear() {
        buf = new String[100];
        start = 0;
        stop = 0;
    }

    @Override
    public void put(String x) {
        semaphore.acquire();
        buf[stop] = x;
        stop += 1;
        semaphore.release();
    }

    @Override
    public String get() {
        semaphore.acquire();
        while(start == stop) {
            semaphore.release();
            semaphore.acquire();
        }
        String result = buf[start];
        start += 1;
        semaphore.release();
        return result;
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
            notify();
        }
        state = SemState.RELEASED;
    }
}