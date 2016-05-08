package demo4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by udoluweera on 5/8/16.
 */
public class ExecutorServiceInvokeAllTest {


    public static void main(String[] args) {

        ExecutorService invokeAllTest = Executors.newSingleThreadExecutor();

        Collection<Callable<String>> callableList = new ArrayList<>();

        for (int i=0;i<5;i++) {

            final  int s = i;

            callableList.add(new Callable() {
                @Override
                public Object call() throws Exception {
                    System.out.println("Adding task : " + s );
                    return "Task " + s + " executed";
                }
            });
        }

        System.out.println("Size of callable list : " + callableList.size());

        List<Future<String>> returnedFutures = null;
        try {
            returnedFutures = invokeAllTest.invokeAll(callableList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Size of returned futures is :" + returnedFutures.size());

        for (Future<String> aFuture : returnedFutures) {
            try {
                System.out.println(" Future get() : " + aFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        invokeAllTest.shutdown();
        System.out.println("Complted the main method...");
    }
}
