package sbu.cs.Semaphore;

import java.util.concurrent.Semaphore;

public class Operator extends Thread {
    Semaphore semaphore = new Semaphore(2);

    public Operator(String name, Semaphore semaphore) {
        super(name);
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
        {
            Resource.accessResource();         // critical section - a Maximum of 2 operators can access the resource concurrently
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();
        }
    }
}
