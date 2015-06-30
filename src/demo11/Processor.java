package demo11;

import demo9.ReEntrantLockTest;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {

    private Lock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();

    public void waitProcess() throws InterruptedException {

        System.out.println("Producer code is running....");
        reentrantLock.lock();
        try {
            System.out.println("await() is going to called");
            condition.await();
            System.out.println("Resumed work on wait process..");

        }catch (Exception e){}
        finally {
            reentrantLock.unlock();
        }
        System.out.println("end of wait process");
    }

    public void notifyProcess() throws InterruptedException {

        System.out.println("Start of signal process...");
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        reentrantLock.lock();
        try {
            System.out.println("Waiting for return key...");
            scanner.nextLine();
            System.out.println("Return key pressed..");
            condition.signal();
            System.out.println("signal() called");

        }catch (Exception e) {}
        finally {
            reentrantLock.unlock();
        }
        System.out.println("end of notify process");
    }
}
