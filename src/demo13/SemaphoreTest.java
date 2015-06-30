package demo13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by udoluweera on 2/4/15.
 */
public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i =0;i<200;i++) {

            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Connection connection = Connection.getConnection();
                    connection.connect();
               }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }

}
