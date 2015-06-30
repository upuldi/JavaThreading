package demo13;

import java.util.concurrent.Semaphore;

/**
 * Created by udoluweera on 2/4/15.
 */
public class Connection {

    private static int count =0;
    private static Connection connection = new Connection();

    /* To limit  the number of connections used by threads */
    private Semaphore semaphore = new Semaphore(10);

    private Connection(){}

    public static Connection getConnection() {
        return connection;
    }

    public void connect() {

        /* First a thread has to acquire a permit from the semaphore, and number of
         * permits released by semaphore is 10. So the connections count will never go beyond 10
          * as threads wait till the release of permits by other threads
          * */
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            count++;
            System.out.println("Current Connections : " + count);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            count--;
        }

        /* Once the work is done, semaphore release the permit so other threads can acquire it */
        //make sure this code is always reach.
        semaphore.release();
    }

}
