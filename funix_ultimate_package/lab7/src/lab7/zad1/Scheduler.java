package lab7.zad1;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Patryk on 2015-05-26.
 */
public class Scheduler extends Thread {
    private Queue<IMethodRequest> activationQueue;

    public Scheduler() {
        activationQueue = new ConcurrentLinkedQueue<IMethodRequest>();
    }

    public void enqueue(IMethodRequest request) {
        activationQueue.add(request);
    }

    public void run() {
        while (true) {
            IMethodRequest imr = activationQueue.poll();
            if (imr != null) {
                if (imr.guard()) {
                    imr.call();
                } else {
                    activationQueue.add(imr);
                }
            }
        }
    }
}
