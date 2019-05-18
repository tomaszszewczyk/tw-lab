package lab4.zad1;

/**
 * Created by Patryk on 2015-04-16.
 */
public class Producer extends Thread {
    private Buffer _buf;

    public Producer(Buffer buf)  {
        this._buf = buf;
    }

    public void run() {
        int a[] = new int[(int) (Math.random() * 10 + 1)];
        for(int i=0; i<a.length; i++) {
            a[i] = (int)(Math.random() * 10) + 1;
        }

        while(true) {
            _buf.put(a);
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}