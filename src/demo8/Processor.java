package demo8;

import java.util.LinkedList;
import java.util.Random;

public class Processor {

    private LinkedList<Integer> linkedList =  new LinkedList<Integer>();
    private final int LIST_SIZE = 10;
    private Object lock = new Object();


    public void produce() throws InterruptedException {

        System.out.println("Producer");
        Random  random = new Random();

        while (true) {
            synchronized (lock) {
                if (linkedList.size()==LIST_SIZE) {
                    lock.wait();
                }
                Integer producingValue = random.nextInt(100);
                System.out.println("Producing Value : " + producingValue);
                linkedList.add(producingValue);
                lock.notify();
            }
        }
    }


    public void consume() throws InterruptedException {

        System.out.println("Consumer");

        while (true) {
            synchronized (lock) {
                if (linkedList.size() == 0) {
                    lock.wait();
                }
                System.out.println("List size is : " + linkedList.size());
                Integer firstValue = linkedList.removeFirst();
                System.out.println("Retrived Value is : " + firstValue);
                lock.notify();

                Thread.sleep(1000);
            }
        }
    }
}
