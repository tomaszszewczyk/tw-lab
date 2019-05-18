package lab3.zad2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Patryk on 2015-04-09.
 */
public class Lab3Zad2 {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore();
        Buffer2 buf = new Buffer2(100);

        int n1 = 1;
        int n2 = 1;
        int prodIterations = 100;
        int consIterations = 100;

        if(n1*prodIterations != n2*consIterations) {
            throw new WrongParametersException2("Number of goods produced is not equal with number of goods consumed. Check the params!");
        }

        ExecutorService service = Executors.newFixedThreadPool(n1 + n2);

        for(int i=1; i<=n1; i++) {
            service.submit(new Producer2(buf, i, prodIterations, semaphore));
        }

        for(int i=1; i<=n2; i++) {
            service.submit(new Consumer2(buf, i, consIterations, semaphore));
        }

        service.shutdown();
    }
}