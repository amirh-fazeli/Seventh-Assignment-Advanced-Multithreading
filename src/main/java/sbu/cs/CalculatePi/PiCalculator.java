package sbu.cs.CalculatePi;

public class PiCalculator {

    /**
     * Calculate pi and represent it as a BigDecimal object with the given floating point number (digits after . )
     * There are several algorithms designed for calculating pi, it's up to you to decide which one to implement.
     * Experiment with different algorithms to find accurate results.
     * <p>
     * You must design a multithreaded program to calculate pi. Creating a thread pool is recommended.
     * Create as many classes and threads as you need.
     * Your code must pass all of the test cases provided in the test folder.
     *
     * @param floatingPoint the exact number of digits after the floating point
     * @return pi in string format (the string representation of the BigDecimal object)
     */

    public static String calculate(int floatingPoint){
        int N = 999999999;
        Pi.PiThread[] threads = new Pi.PiThread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new Pi.PiThread(i, N);
            threads[i].start();
        }
        for (int i = 0; i < 4; i++) {
            try {
                threads[i].join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        double pi = 0;
        for (int i = 0; i < 4; i++) {
            pi += threads[i].getSum();
        }
        System.out.println(pi);
        return null;
    }

    public static void main(String[] args){
        calculate(16);
    }
}
