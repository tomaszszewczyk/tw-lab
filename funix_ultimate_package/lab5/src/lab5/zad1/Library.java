package lab5.zad1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Patryk on 2015-04-23.
 */
public class Library {
    private Lock libraryLock = new ReentrantLock();
    private Condition readers = libraryLock.newCondition();
    private Condition writers = libraryLock.newCondition();
    private int isReading = 0;
    private int isWriting = 0;
    private int writerWaiting = 0;
    private int readerWaiting = 0;
    private Main program;

    public Library(Main program) {
        this.program = program;
    }

    public void beginReading() {
        long tmp = System.nanoTime();
        libraryLock.lock();
        try {
            while (writerWaiting > 0 || isWriting > 0) {
                ++readerWaiting;
                readers.await();
            }
            program.setReaderWaitingTime(program.getReaderWaitingTime() + System.nanoTime() - tmp);
            isReading += 1;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            libraryLock.unlock();
        }
    }

    public void endReading() {
        long tmp = System.nanoTime();
        libraryLock.lock();
        program.setReaderWaitingTime(program.getReaderWaitingTime() + System.nanoTime() - tmp);
        try {
            isReading -= 1;
            if (isReading == 0) {
                if (writerWaiting > 0) {
                    --writerWaiting;
                }
                writers.signal();
            }
        } finally {
            libraryLock.unlock();
        }
    }

    public void beginWriting() {
        long tmp = System.nanoTime();
        libraryLock.lock();
        try {
            while (isReading + isWriting > 0) {
                ++writerWaiting;
                writers.await();
            }
            program.setWriterWaitingTime(program.getWriterWaitingTime() + System.nanoTime() - tmp);
            isWriting = 1;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            libraryLock.unlock();
        }
    }

    public void endWriting() {
        long tmp = System.nanoTime();
        libraryLock.lock();
        program.setWriterWaitingTime(program.getWriterWaitingTime() + System.nanoTime() - tmp);
        try {
            isWriting = 0;
            if (readerWaiting == 0) {
                if (writerWaiting > 0) {
                    --writerWaiting;
                }
                writers.signal();
            } else {
                readerWaiting = 0;
                readers.signalAll();
            }
        } finally {
            libraryLock.unlock();
        }
    }
}