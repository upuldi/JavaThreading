package MyTest;



public class TestWait {

    static {
        System.out.println("Some strange code goes here ..... ");
    }
    public synchronized void someMethod() throws InterruptedException {
        System.out.println("Starting....");
        wait();
        System.out.println("Ending....");
    }

    public static void main(String[] args) throws InterruptedException {
        TestWait testWait = new TestWait();
        testWait.someMethod();
    }
}
