package atomic;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by udoluweera on 5/8/16.
 */
public class AtomicStamptedReferenceTest {

    public static void main(String[] args) {

        String initialReference= "First String reference";
        int initialStamp = 10;
        AtomicStampedReference<String> atomicStampedStringReference = new AtomicStampedReference<>(initialReference,initialStamp);


        System.out.println(atomicStampedStringReference.getReference());
        System.out.println(atomicStampedStringReference.getStamp());


        /* You can get both the reference and the stamp using get() method */

        int[] stampHolder = new int[1];
        String currentValue = atomicStampedStringReference.get(stampHolder);

        System.out.println("get() returns : " + currentValue);
        System.out.println(" Stamp Holder Value : " + stampHolder[0]);


        /* Setting values*/
        int newStampValue = 15;
        atomicStampedStringReference.set("New Value Goes here" ,newStampValue);

        String newStampedValue = atomicStampedStringReference.get(stampHolder);
        System.out.println("get() returns : " + newStampedValue);
        System.out.println(" Stamp Holder Value : " + stampHolder[0]);

        /* Compare and set operations */

        //This compare and set operation will fail if your provide wron values for comparison and stamp.
        boolean compareAndSetResponse = atomicStampedStringReference.compareAndSet(newStampedValue,"CompareAndSetChangedValue",newStampValue,20);
        System.out.println("Compare and Set Response : " + compareAndSetResponse);
        System.out.println("Latest value : " + atomicStampedStringReference.get(stampHolder));




    }
}
