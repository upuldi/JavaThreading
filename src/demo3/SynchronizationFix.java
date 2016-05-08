package demo3;

/**
 * Created by udoluweera on 1/31/15.
 */
public class SynchronizationFix {

    private int count = 0;

    /**
     * Threads have to get the lock of the SynchronizationFix class instance.
     */
    public synchronized void incrementByOne() {
        count++;
    }

    public static void main(String[] args) {

        SynchronizationFix synchronizationFix = new SynchronizationFix();
        synchronizationFix.doCounting();
    }


    public void doCounting() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    incrementByOne();

                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    incrementByOne();

                }
            }
        });

        t1.start();
        t2.start();

        try {
            /* Waiting the main thread to finish the execution of both threads */
            t1.join();
            t2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is : " + count);
    }


}
