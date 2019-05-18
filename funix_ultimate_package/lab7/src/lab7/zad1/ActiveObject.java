package lab7.zad1;

/**
 * Created by Patryk on 2015-05-26.
 */
public class ActiveObject {
    private Buffer buffer;
    private Scheduler scheduler;
    private Proxy proxy;

    public ActiveObject(int queueSize){
        super();
        buffer = new Buffer(queueSize);
        scheduler = new Scheduler();
        proxy = new Servant(buffer, scheduler);
        scheduler.start();
    }

    public Proxy getProxy(){
        return this.proxy;
    }
}
