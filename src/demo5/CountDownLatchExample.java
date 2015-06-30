package demo5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {

    int thread;
    CountDownLatch countDownLatch;

    public Processor(CountDownLatch countDownLatch,int thread){
        this.countDownLatch = countDownLatch;
        this.thread = thread;
    }

    @Override
    public void run() {

        System.out.println("Started : " + thread);
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished : " + thread);
        countDownLatch.countDown();
    }
}

/**
 * This is a java multi threading example with countdown latches.
 *
 *
 * */
public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i=0;i<10;i++) {
            executorService.submit(new Processor(countDownLatch,i));
        }
        /* Waits till the latch reaches to zero. */
        countDownLatch.await();

        executorService.shutdown();
        System.out.println("Completed....");
    }
}
