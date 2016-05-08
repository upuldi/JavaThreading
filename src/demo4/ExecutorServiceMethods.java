package demo4;

import java.util.concurrent.*;

/**
 * Created by udoluweera on 5/8/16.
 */
public class ExecutorServiceMethods {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("Start of the main method...");

        /* Creates a pool with a single thread */
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        /* This will creates a thread pool with fixed number of threads in it */
        ExecutorService executorServiceWithFixedNoOfThreads = Executors.newFixedThreadPool(10);

        /* TODO: ADD A Description */
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);


        /* Execute() returns nothing ....*/
        ExecutorService executorServiceExecuteRunnableTest = Executors.newSingleThreadExecutor();
        executorServiceExecuteRunnableTest.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Running in a separate thread...., execute() will execute the code right away.");
            }
        });
        executorServiceExecuteRunnableTest.shutdown();

        /* Submit() Returns a future... */
        ExecutorService executroServiceSubmitRunnableTest = Executors.newSingleThreadExecutor();
        Future<?> submitRunnableReturnedFuture = executroServiceSubmitRunnableTest.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("submit(Runnable) Returns a future ......");

            }
        });

        executroServiceSubmitRunnableTest.shutdown();
        System.out.println(" Submit(Runnable) done check by the future : " + submitRunnableReturnedFuture.isDone());


        /* Callable returns a return type, */

        ExecutorService executorServiceExecuteCallableTest = Executors.newSingleThreadExecutor();
        Future submitCallableReturnedFuture = executorServiceExecuteCallableTest.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("Submit(Callable) executing....");
                return "Submit(callable) executed return value....";
            }
        });

        executorServiceExecuteCallableTest.shutdown();
        System.out.println("Submit(Callable) returned type : "  + submitCallableReturnedFuture.get());






        System.out.println("End of the main method");



    }
}
