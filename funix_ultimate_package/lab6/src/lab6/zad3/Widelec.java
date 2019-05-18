package lab6.zad3;

public class Widelec {
    private boolean state;

    public Widelec(){
        this.state = true;
    }

    public void podnies() {
        state=false;
    }

    public void odloz() {
        state=true;
    }

    public boolean zajety(){
        return state;
    }
}

