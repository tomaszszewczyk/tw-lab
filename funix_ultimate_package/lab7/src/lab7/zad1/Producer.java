package lab7.zad1;

import java.util.Random;

/**
 * Created by Patryk on 2015-05-26.
 */
public class Producer extends Thread{
    private int id;
    private Proxy proxy;
    private Random rand;

    public Producer(int id, Proxy proxy){
        this.id = id;
        this.proxy = proxy;
        rand = new Random();
    }

    public void run(){
        while(true){
            int tmp = rand.nextInt(100);
            proxy.add(tmp);
            System.out.println("Pracownik " + id + " dodal: " + tmp);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
