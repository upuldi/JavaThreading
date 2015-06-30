package demo14;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by udoluweera on 2/4/15.
 */
public class FuturesAndCallableTestReturnValues {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {

                System.out.println("Starting...");

                Random random = new Random();
                int duration = random.nextInt(100);

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Finished");
                return duration;
            }
        });
        executorService.shutdown();

        executorService.awaitTermination(1, TimeUnit.DAYS);
        try {
            Integer returnedValue = future.get();
            System.out.println("Returned Value is : " + returnedValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
