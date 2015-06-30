package demo2;


import java.util.Scanner;

class Runner extends Thread {

    /**
     * Sometimes OS may cache the value of this variable at the beginning to make this thread faster.
     * but if it happened the value of this variable may not be able to changed by the other thread (
     * The thread executing the main method of the UseOfVolatile class.). To prevent that we use volatile
     * keyword to guarantee that the value in this variable will not be cached by any running threads.
     */
    private volatile boolean running = true;

    @Override
    public void run() {

        while (running) {

            System.out.println("Hello ");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void shutdown() {
        System.out.println("Shutting down .....");
        running = false;
    }
}


public class UseOfVolatile {

    public static void main(String[] args) {
        Runner runner = new Runner();

        runner.start();

        System.out.println("Press return to stop .....");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();  // Checks the return character.

        runner.shutdown();
    }

}
