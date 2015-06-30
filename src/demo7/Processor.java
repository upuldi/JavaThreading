package demo7;

import java.util.Scanner;

public class Processor {

    public void waitProcess() throws InterruptedException {

        synchronized (this) {
            System.out.println("Producer code is running....");
            System.out.println("wait() is going to called");
            wait();
            System.out.println("Resumed..");
        }
        System.out.println("end of wait process");
    }

    public void notifyProcess() throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this) {
            System.out.println("Waiting for return key...");
            scanner.nextLine();
            System.out.println("Return key pressed..");
            notify(); /* Notify just not wake up the other thread. */
            System.out.println("notiy() called");
        }
        System.out.println("end of notify process");
    }
}
