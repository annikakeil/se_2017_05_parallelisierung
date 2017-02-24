package math;

import java.util.ArrayList;
import java.util.List;

public class PrimeGenerator {
    private boolean isPrime(int n) {
        if ( n < 2 ) {
            return false;
        }
        final int loopend = (int) Math.sqrt( n );
        for ( int i = 2; i <= loopend; i++ ) {
            if ( n % i == 0 ) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> generatePrimes(int left, int right) {
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < right; i++) {
            if (isPrime(i)) {
                numbers.add(i);
            }
        }
        return numbers;
    }
}
