package demo14;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by udoluweera on 2/4/15.
 */
public class FuturesAndCallableTestWithVoidMethods {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Void> future = executorService.submit(new Callable<Void>() {

            @Override
            public Void call() throws Exception {

                System.out.println("Starting...");

                Random random = new Random();
                int duration = random.nextInt(100);

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Finished");
                return null;
            }
        });
        executorService.shutdown();

        executorService.awaitTermination(1, TimeUnit.DAYS);
        try {
            Void aVoid = future.get();
            System.out.println("Returned Value is : " + aVoid);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
