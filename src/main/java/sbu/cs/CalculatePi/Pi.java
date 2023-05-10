package sbu.cs.CalculatePi;

public class Pi {

    static class PiThread extends Thread {

        private final int threadRemainder;
        private final int N;
        private double sum  = 0;

        public PiThread(int threadRemainder, int n) {
            this.threadRemainder = threadRemainder;
            N = n;
        }


        @Override
        public void run() {
            for (int i = 0; i <= N; i++) {
                if (i % 4 == threadRemainder) {
                    sum += (4 * Math.pow(-1, i)) / (2 * i + 1);
                }
            }
        }

        public double getSum() {
            return sum;
        }
    }
}
