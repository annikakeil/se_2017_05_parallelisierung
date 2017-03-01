package math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwinPair {

    private static List<TwinPair> twinPairsCache = new ArrayList<TwinPair>();

    public int first = 0;
    public int second = 0;

    public TwinPair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public static synchronized List<TwinPair> getPairs(int right) {
        if (twinPairsCache.size() <= 0) {
            PrimeGenerator primeGenerator = new PrimeGenerator();
            List<Integer> primeNumbers = primeGenerator.generatePrimes(2, right);
            Collections.sort(primeNumbers);
            for (int i = 0; i < primeNumbers.size() - 1; i++) {
                int first = primeNumbers.get(i);
                int second = primeNumbers.get(i + 1);

                if ((second - first) == 2) {
                    twinPairsCache.add(new TwinPair(first, second));
                }
            }
        }
        return twinPairsCache;
    }

    public String toString() {
        return "[" + first + ", " + second + "]";
    }
}
