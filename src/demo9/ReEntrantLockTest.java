package demo9;

/**
 * Created by udoluweera on 2/3/15.
 */
public class ReEntrantLockTest {

    public static void main(String[] args) throws InterruptedException {

        final Runner runner = new Runner();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.firstProcess();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.secondProcess();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        runner.finishProcess();

    }


}
