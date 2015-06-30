package MyTest;

import java.util.Random;

public class Runner implements Runnable {

    private int id;

    public Runner(Integer id) {
        this.id = id;
    }

    @Override
    public void run() {

        Random randomWait = new Random();
        try {
            Thread.sleep(randomWait.nextInt(5)*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Runner : " + id + " Finished.");

    }
}
