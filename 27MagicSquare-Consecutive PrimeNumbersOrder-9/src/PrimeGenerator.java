import java.util.ArrayList;

public class PrimeGenerator {

    public static ArrayList<Integer> createPrimeList(){
        ArrayList<Integer> primes = new ArrayList<Integer>();

        for(int i = 2; i <= Configuration.instance.maxPrimeArea; i++) {
            if(isPrime(i)==true){
                primes.add(i);
            }
        }

        return primes;
    }

    public static boolean isPrime( int n ) {
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




}
