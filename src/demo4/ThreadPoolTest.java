package demo4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ProcessorJob implements Runnable {

    private int id;

    public ProcessorJob(int id){
        this.id = id;
    }

    @Override
    public void run() {

        System.out.println("Starting Processing Job : " + id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finishing Processing Job : " + id);
    }
}



public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {

        /* Using a thread pool */
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i=0;i<5;i++) {
            ProcessorJob processorJob = new ProcessorJob(i);
            executorService.submit(processorJob);
        }
        System.out.println("All tasks submitted.");

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("All tasks completed....");
    }
}
