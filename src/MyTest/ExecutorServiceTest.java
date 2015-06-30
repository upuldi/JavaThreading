package MyTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<10;i++) {
            Runner runner = new Runner(i);
            executorService.submit(runner);
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
