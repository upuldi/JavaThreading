package demo1;


class Runner extends Thread {

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {

            System.out.println("Hello Runner : " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class AnotherRunner implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello Another Runner :" + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadCreation {


    public static void main(String[] args) {

        /* First way of defining threads */
        Runner run1 = new Runner();
        run1.start();

        /* Second way of defining threads */
        AnotherRunner run2 = new AnotherRunner();
        Thread thread1 = new Thread(run2);
        thread1.start();

        /* Again above can be done with anonymous inner classes*/
        Thread run3 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Hello Anonymous Runner : " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        run3.start();

    }
}
