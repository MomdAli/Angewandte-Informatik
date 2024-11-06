package dictionary;

public class Helper {

    public static int nextPrime(int n) {
        if (n < 2) {
            return 2;
        }
        if (n == 2) {
            return 3;
        }
        int p = n % 2 == 0 ? n + 1 : n;
        while (!isPrime(p)) {
            p += 2;
        }
        return p;
    }

    private static boolean isPrime(int n) {
        if (n < 2 || n % 2 == 0) {
            return false;
        }
        if (n == 2) {
            return true;
        }

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
