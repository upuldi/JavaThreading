package demo4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by udoluweera on 5/8/16.
 */
public class ExecutorServiceInvokeAnyTest {


    public static void main(String[] args) {


        ExecutorService invokeAnyTest = Executors.newSingleThreadExecutor();

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

        try {
            String returnValue = invokeAnyTest.invokeAny(callableList);
            System.out.println("What returned from invokeAny(list) is :" + returnValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        invokeAnyTest.shutdown();

        System.out.println("Complted the main method...");
    }
}
