package lab5.zad1;

/**
 * Created by Patryk on 2015-04-23.
 */
class Writer extends Thread {
    private int nr;
    private Library library;
    public Writer(int nr, Library library) {
        super();
        this.nr = nr;
        this.library = library;
    }
    @Override
    public void run() {
        int i = 0;
        while (i++ < 1000) {
            library.beginWriting();
            library.endWriting();
        }
    }
}
