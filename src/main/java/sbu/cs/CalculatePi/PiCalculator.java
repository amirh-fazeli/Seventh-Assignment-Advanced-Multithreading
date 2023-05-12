package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public static BigDecimal sum = new BigDecimal(0);

    public static synchronized void addToSum(BigDecimal value){
        MathContext mc = new MathContext(1000);
        sum = sum.add(value,mc);
    }

    public static class PiSeries implements Runnable{
        MathContext mc = new MathContext(1000);
        int n;
        BigDecimal result = new BigDecimal(0);

        public PiSeries(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            BigDecimal sign = new BigDecimal(1);
            if (n % 2 == 1) {
                sign = new BigDecimal(-1);
            }

            BigDecimal denominator = new BigDecimal(2 * n + 1);
            result = sign.divide(denominator, mc);
            addToSum(result.multiply(new BigDecimal(4),mc));
        }
    }



    public static String calculate(int floatingPoint) {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);


        for (int i = 0; i < 100; i++) {
            PiSeries task = new PiSeries(i);
            threadPool.execute(task);
        }

        return sum.toString();
    }


    public static void main(String[] args){
        System.out.println(calculate(1));
    }
}
