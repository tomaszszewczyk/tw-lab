package lab7.zad1;

/**
 * Created by Patryk on 2015-05-14.
 */
public class Main {
    private final static int PROD_COUNT = 3;
    private final static int CONS_COUNT = 5;
    private final static int ACTIVE_OBJECTS = 10;

    public static void main(String[] argv) throws InterruptedException {
        ActiveObject activeObject = new ActiveObject(ACTIVE_OBJECTS);
        Proxy proxy = activeObject.getProxy();
        Producer[] producers = new Producer[PROD_COUNT];
        Consumer[] consumers = new Consumer[CONS_COUNT];

        for (int i = 0; i < PROD_COUNT; i++) {
            producers[i] = new Producer(i, proxy);
        }

        for (int i = 0; i < CONS_COUNT; i++) {
            consumers[i] = new Consumer(i, proxy);
        }

        for (int i = 0; i < CONS_COUNT; i++) {
            consumers[i].start();
        }

        for (int i = 0; i < PROD_COUNT; i++) {
            producers[i].start();
        }

        for (int i = 0; i < PROD_COUNT; i++) {
            producers[i].join();
        }

        for (int i = 0; i < CONS_COUNT; i++) {
            consumers[i].join();
        }
    }
}
