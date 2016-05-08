package demo4.ForkAndJoinPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * Created by udoluweera on 5/8/16.
 */
public class RecursiveTaskTest {

    /**
     * Recursive Actions are implemented by subclassing it.
     */
    class SimpleRecursiveTask extends RecursiveTask<Long> {

        private final int defaultThreshold = 10;

        private int currentWorkLoad;

        public SimpleRecursiveTask(int currentWorkLoad) {
            this.currentWorkLoad = currentWorkLoad;
        }

        @Override
        protected Long compute() {

            System.out.println("First check the workload size to make sure its beyond the default Threshold to be forked");

            if (currentWorkLoad > defaultThreshold) {

                System.out.println("Workload is above the threshold, forking it to set of subclasses....");

                List<SimpleRecursiveTask> subTaskList = createSubTaskFromThisTasks();

                System.out.println("Created subtasks from this task");
                for (SimpleRecursiveTask subTask : subTaskList) {
                    System.out.println("Forking Subtask");
                    subTask.fork();
                }

                System.out.println("Finished forking all subtasks....");


                System.out.println("Start joining forked subtasks.....");
                Long result = 0L;
                for (SimpleRecursiveTask simpleRecursiveTask : subTaskList) {
                    System.out.println("Joining subtask.....");
                    result += simpleRecursiveTask.join();
                }

                System.out.println("Finished Joining subtasks and the final result is : " + result);
                return result;


            } else {

                System.out.println("Workload is below the threshold, doing it myself without forking it....");
                System.out.println("Logic for doing the workload by myself......");
                System.out.println("Finished doing the workload by myself And Returning some value...");
                return new Long(new Random().nextInt(5));
            }

        }

        private List<SimpleRecursiveTask> createSubTaskFromThisTasks() {

            List<SimpleRecursiveTask> subTasksList = new ArrayList<>();

            System.out.println("Creating two subtasks");
            //Creating two subtasks
            SimpleRecursiveTask simpleRecursiveTaskSubTaskOne = new SimpleRecursiveTask(currentWorkLoad/2);
            SimpleRecursiveTask simpleRecursiveTaskSubTaskTwo = new SimpleRecursiveTask(currentWorkLoad/2);
            subTasksList.add(simpleRecursiveTaskSubTaskOne);
            subTasksList.add(simpleRecursiveTaskSubTaskTwo);
            return subTasksList;
        }
    }



    public static void main(String[] args) {

        System.out.println("Start of the main method");
        RecursiveTaskTest recursiveTaskTest = new RecursiveTaskTest();
        recursiveTaskTest.testRecursiveTask();
        System.out.println("End of the main method");


    }

    private void testRecursiveTask() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        SimpleRecursiveTask simpleRecursiveTask = new SimpleRecursiveTask(16);
        Long returnedValueFromSimpleRecursiveTask = forkJoinPool.invoke(simpleRecursiveTask);
        System.out.println("Returned Value From Simple Recursive Task is : " + returnedValueFromSimpleRecursiveTask);
    }


}
