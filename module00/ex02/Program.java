
import java.util.Scanner;


class Pair {
    public boolean first;
    public int second;

    public Pair(boolean first, int second) {
        this.first = first;
        this.second = second;
    }
}

public class Program {
    
    private int sum(int num)
    {
        int total = 0;
        while (num > 0) {
            total += num % 10;
            num /= 10;
        }
        return total;
    }

    public void solve()
    {
        Scanner scanner = new Scanner(System.in);
        int countCofee = 0;
        while (scanner.hasNext()) {
            int num = scanner.nextInt();
            if (num == 42)
                break;
            int sum_num = sum(num);

            boolean isPrime = true;
            if (num <= 1) {
                isPrime = false;
            }
            for (int i = 2; i <= sum_num / i; i++) {
                if (sum_num % i == 0)
                {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime)
                countCofee++;
        }
        System.out.println("Count of coffee-request : " + countCofee);
        scanner.close();
    }


    public static void main(String[] args) {
        new Program().solve();
    }
    
}
