package lab7.zad1;

/**
 * Created by Patryk on 2015-05-26.
 */
public class Servant implements Proxy {
    Buffer buffer;
    Scheduler scheduler;

    public Servant(Buffer buffer, Scheduler scheduler){
        this.buffer = buffer;
        this.scheduler = scheduler;
    }

    @Override
    public void add(Object object){
        scheduler.enqueue(new AddRequest(buffer, object));
    }

    @Override
    public CustomFuture remove(){
        CustomFuture customFuture = new CustomFuture();
        scheduler.enqueue(new RemoveRequest(buffer, customFuture));
        return customFuture;
    }
}
