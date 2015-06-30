package demo7;

/**
 * Created by udoluweera on 2/2/15.
 */
public class WaitAndNotifyTest {


    public static void main(String[] args) throws InterruptedException {

        final Processor processor = new Processor();


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.waitProcess();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.notifyProcess();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();


    }


}
