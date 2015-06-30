package demo14;

import java.util.Random;
import java.util.concurrent.Callable;

public class Runner implements Callable<String> {

    private int id;

    public Runner(Integer id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {

        System.out.println("Starting... : " + id);
        Random randomWait = new Random();
        Thread.sleep(randomWait.nextInt(10)*1000);

        Random random = new Random();
        int randomInt = random.nextInt(100);
        System.out.println("Finished... : " + id);
        return id + " - " + randomInt;
    }
}
