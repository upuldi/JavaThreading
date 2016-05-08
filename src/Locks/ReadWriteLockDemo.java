package Locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by udoluweera on 5/8/16.
 */
public class ReadWriteLockDemo implements Runnable {

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void someReadWriteDemo() throws InterruptedException {

        System.out.println("Start of the method");

        System.out.println("Before Acquiring the read Lock");
        readWriteLock.readLock().lock();
        System.out.println("Read Lock acquired");
        System.out.println("Doing Reading operation...");
        Thread.sleep(3000);
        System.out.println("Releasing the read lock");
        readWriteLock.readLock().unlock();
        System.out.println("Before acquiring the write lock");
        readWriteLock.writeLock().lock();
        System.out.println("Write Lock acquired");
        Thread.sleep(1000);
        System.out.println("Releasing write lock");
        readWriteLock.writeLock().unlock();

        System.out.println("End of the method");


    }


    public static void main(String[] args) throws InterruptedException {


        System.out.println("Start of the main");

        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();


        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            threadPool.submit(readWriteLockDemo);
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("End of the main");


    }

    @Override
    public void run() {
        try {
            someReadWriteDemo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
