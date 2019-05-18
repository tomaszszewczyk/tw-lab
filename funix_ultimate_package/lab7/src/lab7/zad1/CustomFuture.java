package lab7.zad1;

/**
 * Created by Patryk on 2015-05-26.
 */
public class CustomFuture {
    private Object object;
    public void setObject(Object object){
        this.object = object;
    }
    public Object getObject(){
        return object;
    }
    public boolean isReady(){
        return object != null;
    }
}
