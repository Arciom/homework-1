package by.Homework2;

/**
 * Created by arciom on 01.06.2017.
 */
public class Primes {

  public static boolean isPrimeFast(int n) {
    int m = (int) Math.sqrt(n);
    for(int i = 2; i < m; i++) {
      if(n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeWhile(int n) {
    int i = 2;
    while (i < n && n % i != 0) {
     i++;
    }
    return  i == n;
  }

  public static boolean isPrime(long n) {
    for(int i = 0; i < n / 2; i++) {
      if(n % i == 0) {
        return false;
      }
    }
    return true;
  }
}


