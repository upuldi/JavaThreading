package demo14;

import java.util.Random;
import java.util.concurrent.*;


class MyTestException extends Exception {
    public MyTestException(String cause){
        super(cause);
    }
}

public class FuturesAndCalllablesWithException {

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

                if (duration < 50) {
                    throw new MyTestException("Something Terribly happened");

                }

                System.out.println("Finished");
                return duration;
            }
        });
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);

        /* Exceptions will be wrapped in ExecutionException */
        try {
            Integer returnedValue = future.get();
            System.out.println("Returned Value is : " + returnedValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("Exception Message is : " + e.getMessage());
        }

    }



}
