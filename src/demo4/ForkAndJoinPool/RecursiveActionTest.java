package demo4.ForkAndJoinPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by udoluweera on 5/8/16.
 */
public class RecursiveActionTest {

    /**
     * Recursive Actions are implemented by subclassing it.
     */
    class SampleRecursiveAction extends RecursiveAction {

        private final int defaultThreshold = 10;

        private int currentWorkLoad;

        public SampleRecursiveAction(int currentWorkLoad) {
            this.currentWorkLoad = currentWorkLoad;
        }

        @Override
        protected void compute() {

            System.out.println("First check the workload size to make sure its beyond the default Threshold to be forked");

            if (currentWorkLoad > defaultThreshold) {

                System.out.println("Workload is above the threshold, forking it to set of subclasses....");

                List<SampleRecursiveAction> subTaskList = createSubTaskFromThisTasks();

                System.out.println("Created subtasks from this task");
                for (SampleRecursiveAction subTask : subTaskList) {
                    System.out.println("Forking Subtask");
                    subTask.fork();
                }
                System.out.println("Finished forking all subtasks....");


            } else {

                System.out.println("Workload is below the threshold, doing it myself without forking it....");

                System.out.println("Logic for doing the workload by myself......");

                System.out.println("Finished doing the workload by myself...");
            }
        }

        private List<SampleRecursiveAction> createSubTaskFromThisTasks() {

            List<SampleRecursiveAction> subTasksList = new ArrayList<>();

            System.out.println("Creating two subtasks");
            //Creating two subtasks
            SampleRecursiveAction sampleRecursiveActionSubTaskOne = new SampleRecursiveAction(currentWorkLoad/2);
            SampleRecursiveAction sampleRecursiveActionSubTaskTwo = new SampleRecursiveAction(currentWorkLoad/2);
            subTasksList.add(sampleRecursiveActionSubTaskOne);
            subTasksList.add(sampleRecursiveActionSubTaskTwo);
            return subTasksList;
        }
    }



    public static void main(String[] args) {

        System.out.println("Start of the main method");
        RecursiveActionTest recursiveActionTest = new RecursiveActionTest();
        recursiveActionTest.testRecursiveAction();
        System.out.println("End of the main method");


    }

    private void testRecursiveAction() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        SampleRecursiveAction sampleRecursiveAction = new SampleRecursiveAction(16);
        forkJoinPool.invoke(sampleRecursiveAction);
    }


}
