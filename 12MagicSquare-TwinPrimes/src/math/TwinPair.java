package math;

import random.MersenneTwister;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TwinPair {

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

    public static List<TwinPair> getPairs(int right) {
        PrimeGenerator primeGenerator = new PrimeGenerator();
        List<TwinPair> pairs = new ArrayList<TwinPair>();
        List<Integer> primeNumbers = primeGenerator.generatePrimes(3, right);
        Collections.sort(primeNumbers);
        for (int i = 0; i < primeNumbers.size() - 1; i++) {
            int first = primeNumbers.get(i);
            int second = primeNumbers.get(i + 1);

            if ((second - first) == 2) {
                pairs.add(new TwinPair(first, second));
            }
        }
        return pairs;
    }

    public String toString() {
        return "[" + first + ", " + second + "]";
    }
}
