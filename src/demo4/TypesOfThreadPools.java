package demo4;

import java.util.concurrent.*;

/**
 * Created by udoluweera on 5/8/16.
 */
public class TypesOfThreadPools {

    public static void main(String[] args) {

        /**
         * Executor service has two implementations
         */


        //ThreadPoolExecutor

        int corePoolSize = 5;
        int maxPoolSize = 10;
        long keepAliveTime = 10000 ;
        BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(10);
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime, TimeUnit.MINUTES,taskQueue);


    }
}
