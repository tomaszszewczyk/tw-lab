package lab4.zad1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Patryk on 2015-04-16.
 */
public class Lab4Zad1 {
    public static void main(String[] args) {
        int M = 100;
        int m = 1;
        int n = 1;

        Buffer buf = new Buffer(M);
        ExecutorService service = Executors.newFixedThreadPool(m + n);

        for(int i=1; i<=m; i++) {
            service.submit(new Producer(buf));
        }

        for(int i=1; i<=n; i++) {
            service.submit(new Consumer(buf));
        }

        service.shutdown();
    }
}
