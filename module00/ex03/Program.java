import java.util.Scanner;

public class Program {

    private boolean isValid(String wk, int wkNum) {
        String week = "Week " + wkNum;
        return (week.equals(wk));
    }

    private void illegalarg() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }

    private void printAnswer(long db) {
        int i = 1;
        while (db != 0) {
            System.out.print("   Week " + i++ + " ");
            long note = db % 10;
            db /= 10;
            while (note-- > 0)
                System.out.print("=");
            System.out.println(">");
        }
        return;
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        // Scanner scInt = new Scanner(System.in);

        long db = 0;
        int expectedWeek = 1;
        int multiple = 1;
        while (sc.hasNextLine()) {
            String name = sc.nextLine();
            if (name.equals("42")) {
                printAnswer(db);
                break;
            }

            if (!isValid(name, expectedWeek) || expectedWeek > 18)
                illegalarg();

            int mn = 0;

            for (int i = 0; i < 5; ++i) {
                int num = sc.nextInt();
                if (!(num > 0 && num < 10))
                    illegalarg();

                if (i != 0)
                    mn = mn > num ? num : mn;
                else
                    mn = num;
            }
            sc.nextLine();
            db += mn * multiple;
            multiple *= 10;
            expectedWeek++;
        }
        sc.close();
        // scInt.close();
    }

    public static void main(String[] args) {
        new Program().solve();
    }
}


