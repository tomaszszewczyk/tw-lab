package lab7.zad1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Patryk on 2015-05-26.
 */
public class Buffer {
    private int bufSize;
    private Queue<Object> buffer;
    public Buffer(int bufSize){
        this.bufSize = bufSize;
        this.buffer = new LinkedList<Object>();
    }
    public void add(Object object) {
        if(!this.isFull()){
            this.buffer.add(object);
        }
    }
    public Object remove() {
        if(this.isEmpty()){
            return null;
        }
        else{
            return buffer.remove();
        }
    }
    public boolean isFull() {
        return buffer.size() == bufSize;
    }
    public boolean isEmpty() {
        return buffer.isEmpty();
    }
}
