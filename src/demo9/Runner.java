package demo9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by udoluweera on 2/3/15.
 */
public class Runner {

    private int count =0;
    private Lock reentrantLock = new ReentrantLock();

    private void increment() {

        for (int i=0;i<10000;i++) {
            count++;
        }
    }

    public void firstProcess() {
        reentrantLock.lock();
        try {
            increment();
        } catch (Exception e) {}
        finally {
            reentrantLock.unlock();
        }

    }
    public void secondProcess() {
        reentrantLock.lock();
        try {
            increment();
        } catch (Exception e) {}
        finally {
            reentrantLock.unlock();
        }
    }
    public void finishProcess() {
        System.out.println("Final Count is : " + count);

    }

}
