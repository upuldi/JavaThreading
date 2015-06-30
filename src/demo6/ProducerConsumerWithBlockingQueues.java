package demo6;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ConsumerAndProducer {

    private BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);

    public void produce() throws InterruptedException {
        System.out.println("Producer started ......");
        Random random = new Random();
        while (true) {
            Integer randomValue = random.nextInt(100);
//            System.out.println("before putting");
            blockingQueue.put(randomValue);
//            System.out.println("after putting");
            System.out.println("Produced value : " + randomValue + " queue size : " + blockingQueue.size()
                    + " Values Are : " + Arrays.asList(blockingQueue));
        }
    }

    public void consume() throws InterruptedException {

        System.out.println("Consumer stated.....");
        Random random = new Random();
        while (true) {

            Thread.sleep(100);
            int randomNumber = random.nextInt(10);
            if (randomNumber == 0) {
//                System.out.println("before talking");
                Integer consumedValue = blockingQueue.take();
//                System.out.println("after taking");
                System.out.println(" Value consumed : " + consumedValue  + " queue size : " + blockingQueue.size() +
                        " Values Are : " + Arrays.asList(blockingQueue));
            }
        }
    }
}



public class ProducerConsumerWithBlockingQueues {


    public static void main(String[] args) throws InterruptedException {

        final ConsumerAndProducer consumerAndProducer = new ConsumerAndProducer();
        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumerAndProducer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumerThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    consumerAndProducer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        //doWithThreadPools();

    }

    private static void doWithThreadPools() {

        ExecutorService executorService = Executors.newFixedThreadPool(6);

        final ConsumerAndProducer consumerAndProducer = new ConsumerAndProducer();
        for (int i=0;i<6;i++) {
            if (i % 2 == 0) {
                executorService.submit( new Runnable() {
                    @Override
                    public void run() {
                        try {
                            consumerAndProducer.produce();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                executorService.submit( new Runnable() {
                    @Override
                    public void run() {
                        try {
                            consumerAndProducer.consume();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
        executorService.shutdown();
    }

}
