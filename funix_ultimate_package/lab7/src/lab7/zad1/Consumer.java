package lab7.zad1;

/**
 * Created by Patryk on 2015-05-26.
 */
public class Consumer extends Thread{
    private int id;
    private Proxy proxy;

    public Consumer(int id, Proxy proxy){
        this.id = id;
        this.proxy = proxy;
    }

    public void run(){
        while(true){
            CustomFuture consumed = proxy.remove();
            while(!consumed.isReady()){
                System.out.println("Konsumenr " + id + " czeka.");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Konsument " + id
                    + " zjadl: " + consumed.getObject());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
