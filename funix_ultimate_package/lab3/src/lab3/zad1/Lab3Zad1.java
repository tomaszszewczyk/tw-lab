package lab3.zad1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Patryk on 2015-04-09.
 */

public class Lab3Zad1 {
    public static void main(String[] args) throws InterruptedException {
        Buffer buf = new Buffer(100);
        int n1 = 5;
        int n2 = 15;

        int prodIterations = 300;
        int consIterations = 100;

        if(n1*prodIterations != n2*consIterations) {
            throw new WrongParametersException("Number of goods produced " +
                    "is not equal with number of goods consumed. Check the params!");
        }

        ExecutorService service = Executors.newFixedThreadPool(n1 + n2);

        for(int i=1; i<=n1; i++) {
            service.submit(new Producer(buf, prodIterations));
        }

        for(int i=1; i<=n2; i++) {
            service.submit(new Consumer(buf, consIterations));
        }

        service.shutdown();
    }
}