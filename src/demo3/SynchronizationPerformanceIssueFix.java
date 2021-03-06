package demo3;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynchronizationPerformanceIssueFix {


    private Random random = new Random(100);

    private List<Integer> listWithRandomNumbers = new ArrayList<Integer>();
    private List<Integer> secondListWithRandomNumbers = new ArrayList<Integer>();

    /*
    * Thought it is possible to use two list objects for locking it is a good idea to create
    * separate objects for locking as it prevents a lot of unexpected behaviours,
    *
    * */
    private Object lockOne = new Object();
    private Object lockTwo = new Object();


    private void  fillListOne() throws InterruptedException {

        Thread.sleep(100);

        synchronized (lockOne) {
            for (int i=0;i<1000;i++) {
                listWithRandomNumbers.add(random.nextInt());
            }
        }
    }

    private void fillListTwo() throws InterruptedException {

        Thread.sleep(100);

        synchronized (lockTwo) {

            for (int i=0;i<1000;i++) {
                secondListWithRandomNumbers.add(random.nextInt());
            }
        }
    }


    private void process() {
        try {
            fillListOne();
            fillListTwo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        final SynchronizationPerformanceIssueFix synchronizationPerformanceIssueFix  = new SynchronizationPerformanceIssueFix();

        Long startTime = System.currentTimeMillis();

        Thread t1 = new Thread( new Runnable() {
            @Override
            public void run() {
                synchronizationPerformanceIssueFix.process();
            }
        });
        Thread t2 = new Thread( new Runnable() {
            @Override
            public void run() {
                synchronizationPerformanceIssueFix.process();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        Long endTime = System.currentTimeMillis();

        System.out.println("Time taken to process : " + (endTime - startTime));
        System.out.println("Size of lists : List one :" + synchronizationPerformanceIssueFix.getListWithRandomNumbers().size() +
                " Size of List Two : " + synchronizationPerformanceIssueFix.getSecondListWithRandomNumbers().size() );

        /*
        * Here the initial time is 204
        * With synchronization time is 409 - Value has been doubled.
        * With synchronized blocks time is back to 205 levels.
        *
        * */
    }


    public List<Integer> getListWithRandomNumbers() {
        return listWithRandomNumbers;
    }

    public List<Integer> getSecondListWithRandomNumbers() {
        return secondListWithRandomNumbers;
    }

}
