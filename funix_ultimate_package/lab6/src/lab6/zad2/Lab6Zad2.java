package lab6.zad2;

import java.util.ArrayList;
import java.util.List;

public class Lab6Zad2 {
    public static void main(String args[]){
        List<Widelec> widelce = new ArrayList<Widelec>();
        Filozof[] filozofs = new Filozof[5];

        Object lockA = new Object();

        for(int i = 0; i < 5; i++){
            widelce.add(new Widelec());
            filozofs[i]=new Filozof(i, widelce, lockA);
        }

        for(int i = 0; i < 5; i++) {
            filozofs[i].start();
        }

        for(int i = 0; i < 5; i++){
            try {
                filozofs[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}