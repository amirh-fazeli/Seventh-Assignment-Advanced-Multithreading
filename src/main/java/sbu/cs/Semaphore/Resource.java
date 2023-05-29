package sbu.cs.Semaphore;

public class Resource {

    public static void accessResource() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
