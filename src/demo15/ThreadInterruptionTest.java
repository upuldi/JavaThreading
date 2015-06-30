package demo15;

import java.util.Random;

public class ThreadInterruptionTest {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting..");

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                Random random = new Random();
                for (int i=0;i<1E8;i++) {

             /*       if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Thread has marked interrupted");
                        break;
                    }*/

                  /* or else you could do followings */
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    Math.sin(random.nextDouble());
                }
            }
        });

        thread.start();
        /* This does not interrupt the thread. */
        thread.interrupt();
        thread.join();

        System.out.println("Finished.");
    }
}
