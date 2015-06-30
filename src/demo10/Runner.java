package demo10;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by udoluweera on 2/3/15.
 */
public class Runner {

    private int count =0;
    private Lock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();

    private void increment() {

        for (int i=0;i<10000;i++) {
            count++;
        }
    }

    public void firstProcess() throws InterruptedException {

        reentrantLock.lock();
        System.out.println("First Process is Waiting.....");
        condition.await(); /* Await will handover the lock to the other thread. */

        System.out.println("First Process is Woken up.....");

        try {
            increment();
        } catch (Exception e) {}
        finally {
            reentrantLock.unlock();
        }

    }
    public void secondProcess() throws InterruptedException {

        Thread.sleep(1000);

        reentrantLock.lock();

        System.out.println("Press Enter to signal other process");
        new Scanner(System.in).nextLine();
        System.out.println("SinganlAll other processes");
        condition.signal();

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
