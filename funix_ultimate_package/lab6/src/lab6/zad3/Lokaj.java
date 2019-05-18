package lab6.zad3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 2015-05-28.
 */
public class Lokaj {
    private List<Widelec> stol = new ArrayList<Widelec>();
    private int liczbaZajetychWidelcow = 0;

    public Lokaj() {
        for (int i = 0; i < 5; i++) {
            stol.add(new Widelec());
        }
    }

    public synchronized void podajWidelce(int num1, int num2) {
        while(!stol.get(num1).zajety() || !stol.get(num2).zajety()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while(liczbaZajetychWidelcow == 4){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        liczbaZajetychWidelcow += 2;
        stol.get(num1).podnies();
        stol.get(num2).odloz();
    }

    public synchronized void zabierzWidelce(int num1, int num2){
        liczbaZajetychWidelcow -= 2;
        stol.get(num1).odloz();
        stol.get(num2).odloz();
        notifyAll();
    }
}