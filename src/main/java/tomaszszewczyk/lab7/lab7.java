package tomaszszewczyk.lab7;

public class lab7 {
  public static void main(String[] args) {
    Fork fork1 = new Fork();
    Fork fork2 = new Fork();
    Fork fork3 = new Fork();
    Fork fork4 = new Fork();
    Fork fork5 = new Fork();

    Man man1 = new Man(fork1, fork2);
    Man man2 = new Man(fork2, fork3);
    Man man3 = new Man(fork3, fork4);
    Man man4 = new Man(fork4, fork5);
    Man man5 = new Man(fork5, fork1);
  }
}

class Fork {
  private Lock lock = new ReentrantLock();
  
  public void pick() {
    lock.lock();
  }

  public void release() {
    lock.unlock();
  }
}

class Man extends Thread {
  private int counter = 0;
  private Fork fork1;
  private Fork fork2;

  public Man(Fork f1, Fork f2) {
    fork1 = f1;
    fork2 = f2;
  }

  public void run() {
    while (true) {
      fork1.pick();
      fork2.pick();

      counter++;
      if (counter % 100 == 0) {
        System.out.println("Filozof: " + Thread.currentThread() + "jadlem " + counter + " razy");
      }
      
      fork1.release();
      fork2.release();
    }
  }
}
