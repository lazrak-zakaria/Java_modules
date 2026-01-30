import java.util.Scanner;

public class Program {

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        scanner.close();

        if (num < 2) {
            System.out.println("IllegalArgument");
            System.exit(-1);
        }

        boolean isPrime = true;
        if (num <= 1) {
            isPrime = false;
        }
        int count = 1;
        for (int i = 2; i <= num / i; i++, count++) {
            if (num % i == 0)
            {
                isPrime = false;
                break;
            }
        }
        System.out.println(isPrime + " " + count);
    }

    public static void main(String[] args) {
        new Program().solve();
        ;
    }
}
