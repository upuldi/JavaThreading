package demo10;


/**
 * Created by udoluweera on 2/3/15.
 */
public class WaitAndNotifyForReEntrantLockTest {

    public static void main(String[] args) throws InterruptedException {
        final Runner runner = new Runner();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.firstProcess();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.secondProcess();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        runner.finishProcess();

    }

}
