package lab1;

/**
 * Created by Patryk on 2015-03-19.
 */
public class Lab1Zad1 {
    public static void main(String[] args)
            throws InterruptedException {
        final long startTime = System.currentTimeMillis();
        final Num myNum = new Num();

        Thread numeroUno = new Thread(){
            public void run() {
                for(int i=0; i<1000000; i++) {
                    myNum.inc();
                }
            }
        };

        Thread numeroDuo = new Thread(){
            public void run() {
                for(int i=0; i<1000000 ; i++) {
                    myNum.dec();
                }
            }
        };

        numeroUno.start();
        numeroDuo.start();

        numeroDuo.join();
        numeroUno.join();

        final long endTime = System.currentTimeMillis();
        System.out.println(myNum.getVal());
        System.out.println(endTime - startTime);
    }
}