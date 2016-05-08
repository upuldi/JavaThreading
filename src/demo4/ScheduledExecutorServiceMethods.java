package demo4;

import java.util.concurrent.*;

/**
 * Created by udoluweera on 5/8/16.
 */
public class ScheduledExecutorServiceMethods {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        scheduleCallableTest();
        scheduleRunnableTest();


        scheduledAtFiexdRateTest();


    }

    private static void scheduleCallableTest() throws InterruptedException, ExecutionException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(
                new Callable() {
                    public Object call() throws Exception {
                        System.out.println("Executed!");
                        return "Called!";
                    }
                },
                5,
                TimeUnit.SECONDS);

        System.out.println("result = " + scheduledFuture.get());
        scheduledExecutorService.shutdown();
    }

    private static void scheduleRunnableTest() throws InterruptedException, ExecutionException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Inside Schedule(runnable) method");
                    }
                },
                5,
                TimeUnit.SECONDS);

        System.out.println("result = " + scheduledFuture.get());
        System.out.println("Since Runnable can not returns a value, call on ScheduledFuture.get() method will always returns null.");
        scheduledExecutorService.shutdown();
    }

    private static void scheduledAtFiexdRateTest()
            throws InterruptedException, ExecutionException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        System.out.println("Initial Delay is 4 sec");

        ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Reoccurring task at every 2 seconds ......");
                    }
                },4,
                2,
                TimeUnit.SECONDS);
        //This will execute until you shutdown the schedule...
        //scheduledExecutorService.shutdown();
    }
}
