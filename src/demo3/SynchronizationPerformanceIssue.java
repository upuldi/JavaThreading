package demo3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynchronizationPerformanceIssue {

    private Random random = new Random(100);

    private List<Integer> listWithRandomNumbers = new ArrayList<Integer>();
    private List<Integer> secondListWithRandomNumbers = new ArrayList<Integer>();


     private synchronized void  fillListOne() throws InterruptedException {

        Thread.sleep(100);

        for (int i=0;i<1000;i++) {
            listWithRandomNumbers.add(random.nextInt());
        }
    }

    private synchronized void fillListTwo() throws InterruptedException {

        Thread.sleep(100);

        for (int i=0;i<1000;i++) {
            secondListWithRandomNumbers.add(random.nextInt());
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

        final SynchronizationPerformanceIssue synchronizationPerformanceIssue = new SynchronizationPerformanceIssue();

        Long startTime = System.currentTimeMillis();

        Thread t1 = new Thread( new Runnable() {
            @Override
            public void run() {
                synchronizationPerformanceIssue.process();
            }
        });
        Thread t2 = new Thread( new Runnable() {
            @Override
            public void run() {
                synchronizationPerformanceIssue.process();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        Long endTime = System.currentTimeMillis();

        System.out.println("Time taken to process : " + (endTime - startTime));
        System.out.println("Size of lists : List one :" + synchronizationPerformanceIssue.getListWithRandomNumbers().size() +
                " Size of List Two : " + synchronizationPerformanceIssue.getSecondListWithRandomNumbers().size() );

        /*
        * Here the initial time is 204
        * With synchronization time is 409 - Value has been doubled.
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
