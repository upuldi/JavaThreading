package demo12;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Runner {

    Account accountOne = new Account(10000);
    Account accountTwo = new Account(10000);

    Lock lockOne = new ReentrantLock();
    Lock lockTwo = new ReentrantLock();

    private void acquireLocks(Lock lockOne, Lock lockTwo) throws InterruptedException {

        boolean gotFirstLock = false;
        boolean gotSecondLock = false;

        while (true) {

            try {
                gotFirstLock = lockOne.tryLock();
                gotSecondLock = lockTwo.tryLock();

            } finally {

                if (gotFirstLock && gotSecondLock) {
                    return;
                }

                if (gotFirstLock) {
                    lockOne.unlock();
                }

                if (gotSecondLock) {
                    lockTwo.unlock();
                }
            }

            /* Waits till locks to release */
            Thread.sleep(1);
        }
    }

    public void firstProcess() throws InterruptedException {

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            try {
//                lockOne.lock();
//                lockTwo.lock();
                acquireLocks(lockOne, lockTwo);
                Account.transfer(accountOne, accountTwo, random.nextInt(100));
            } finally {
                lockOne.unlock();
                lockTwo.unlock();
            }
        }
    }

    public void secondProcess() throws InterruptedException {

        Random random = new Random();

        for (int i = 0; i < 10000; i++) {

            try {
                /* Here you could stop a deadlock with locking locks in same order. */
//                lockTwo.lock();
//                lockOne.lock();
                acquireLocks(lockTwo, lockOne);
                Account.transfer(accountTwo, accountOne, random.nextInt(100));
            } finally {
                lockTwo.unlock();
                lockOne.unlock();
            }
        }
    }

    public void finishProcess() {

        System.out.println("Account one balance : " + accountOne.getBalance());
        System.out.println("Account two balance : " + accountTwo.getBalance());
        System.out.println("Total balance : " + (accountOne.getBalance()
                + accountTwo.getBalance()));
    }

}
