package demo14;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FuturesAndCallableWithMultiThreads {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        for (int i=0;i<10;i++) {
            Runner runner = new Runner(i);
            Future<String> future = executorService.submit(runner);
            futureList.add(future);
        }

        /* Waiting for 10 sec */
//        Thread.sleep(10000);

        for (Future<String> integerFuture : futureList) {
            /* future.get() wait on the thread to finish the call. */
            System.out.println("Returned value is : " + integerFuture.get());
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
