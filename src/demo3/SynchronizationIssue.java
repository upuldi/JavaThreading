package demo3;

public class SynchronizationIssue {

    private int count = 0;

    public static void main(String[] args) {

        SynchronizationIssue synchronizationIssue = new SynchronizationIssue();
        synchronizationIssue.doCounting();
    }


    public void doCounting() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count++;

                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count++;

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
