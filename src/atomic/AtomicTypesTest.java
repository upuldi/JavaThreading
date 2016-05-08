package atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by udoluweera on 5/8/16.
 */
public class AtomicTypesTest {

    public static void main(String[] args) {

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        boolean currentValue = atomicBoolean.get();
        System.out.println("Current Atomic Boolean Value : " + currentValue);
        atomicBoolean.set(true);
        System.out.println("New value : " + atomicBoolean.get());
        boolean oldValue = atomicBoolean.getAndSet(false);
        System.out.println("Get and Set Returns the old value as  : " + oldValue);
        boolean swapOperationResult = atomicBoolean.compareAndSet(false,true);
        System.out.println("Response from swapping : " + swapOperationResult);
        System.out.println("New Value : " + atomicBoolean.get());


    }
}
