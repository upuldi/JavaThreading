package demo4;

import java.util.concurrent.*;

/**
 * Created by udoluweera on 5/8/16.
 */
public class ScheduledExecutorServiceTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("Start of the main method");

        int corePoolSize = 5;
        ScheduledExecutorService scheduledExecutorServiceTest = Executors.newScheduledThreadPool(corePoolSize);

        ScheduledFuture scheduledFuture = scheduledExecutorServiceTest.schedule(new Callable() {
            @Override
            public Object call() throws Exception {

                System.out.println("Inside the callable");
                return "Something Happeed";
            }
        },3,TimeUnit.SECONDS);

        //System.out.println("ReturnedValue From the future : " + scheduledFuture.get());

        System.out.println("End of the Main Method");


    }




}
