package lab5.zad2;

/**
 * Created by Patryk on 2015-05-13.
 */
public class Main extends Thread {
    private Object[] o;
    private List1 list;
    private List2 list2;
    private static long sleepTime;

    public Main(Object[] o, List1 list) {
        super();
        this.o = o;
        this.list = list;
    }

    public Main(Object[] o, List2 list2) {
        super();
        this.o = o;
        this.list2 = list2;
    }

    @Override
    public void run() {
        for (int i = 0, n = o.length; i < 10; ++i) {
            try {
                list.add(o[i % n]);
                list.contains(o[(i + 1) % n]);
                list.remove(o[(i + 2) % n]);
                list.contains(o[(i + 3) % n]);
                list.add(o[(i + 4) % n]);
                list.remove(o[(i + 5) % n]);
                list.add(o[(i + 6) % n]);
                list.remove(o[(i + 7) % n]);
                list.contains(o[(i + 8) % n]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        for (int i = 0, n = o.length; i < 10; ++i) {
//            try {
//                list2.add(o[i % n]);
//                list2.contains(o[(i + 1) % n]);
//                list2.remove(o[(i + 2) % n]);
//                list2.contains(o[(i + 3) % n]);
//                list2.add(o[(i + 4) % n]);
//                list2.remove(o[(i + 5) % n]);
//                list2.add(o[(i + 6) % n]);
//                list2.remove(o[(i + 7) % n]);
//                list2.contains(o[(i + 8) % n]);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static void main(String[] args) {
        Object[] o = { "ala ma kota", 10L, 10.0, "ola ma kota", 11L, 11.0,
                "ala ma kata", 11L, 11.0 };
        List1 list = new List1("ala ma kota", null);
        List2 list2 = new List2("ala ma kota", null);

        for (sleepTime = 0; sleepTime < 1240; sleepTime += 20) {
            list.setSleepTime(sleepTime);
            long time = System.nanoTime();
            Thread[] t = {new Main(o, list), new Main(o, list), new Main(o, list)};
            for(int i = 0; i < t.length; ++i) {
                t[i].start();
            }
            for(int i = 0; i < t.length; ++i) {
                try {
                    t[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            time = System.nanoTime() - time;
            System.out.println(sleepTime + " " + time);
        }

//        for (sleepTime = 0; sleepTime < 2000; sleepTime += 20) {
//            list2.setSleepTime(sleepTime);
//            long time = System.nanoTime();
//            Thread[] t = {new Main(o, list2), new Main(o, list2), new Main(o, list2)};
//            for(int i = 0; i < t.length; ++i) {
//                t[i].start();
//            }
//            for(int i = 0; i < t.length; ++i) {
//                try {
//                    t[i].join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            time = System.nanoTime() - time;
//            System.out.println(sleepTime + " " + time);
//        }
    }
}
