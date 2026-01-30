
import java.util.Scanner;

public class Program {

    private int MAX_CHAR = 65536;
    private short MAX_HEIGHT = 10;

    private void sort(int[] frequency, char[] chars, int[] freq) {
        int t = 0;
        for (int j = 0; j < 5; ++j) {
            int a = 0;
            int b = 0;
            for (int i = 1; i < 65536; ++i) {
                if (frequency[i] > frequency[a]) {
                    b = a;
                    a = i;
                } else if (frequency[i] > frequency[b])
                    b = i;
            }

            chars[t] = (char) a;
            freq[t++] = frequency[a];
            frequency[a] = -1;
            if (a != b) {
                freq[t] = frequency[b];
                chars[t++] = (char) b;
                frequency[b] = -1;
            }
        }
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);

        if (sc.hasNextLine()) {

            int[] frequency = new int[MAX_CHAR];
            char[] chars = new char[10];
            int[] freq = new int[10];
            int[] realFreq = new int[10];

            String line = sc.nextLine();
            if (line.length() == 0) {
                sc.close();
                return;
            }
            for (char c : line.toCharArray())
                frequency[c]++;

            sort(frequency, chars, freq);

            int mx = freq[0];

            for (int i = 0; i < 10; ++i) {
                realFreq[i] = freq[i] * MAX_HEIGHT;
                realFreq[i] /= mx;
            }

            for (int i = 0; i < 11; ++i) {
                for (int j = 0; j < 10; ++j) {
                    if (11 - i - 1 == realFreq[j] && freq[j] != 0)
                        System.out.printf("%5d", freq[j]);
                    else if (11 - i - 1 > realFreq[j] || freq[j] == 0)
                        break;
                    else
                        System.out.printf("%5s", "#");
                }
                System.out.println("");
            }

            for (int i = 0; i < 10; ++i) {
                if ((int) chars[i] == 0)
                    break;
                System.out.printf("%5c", chars[i]);
            }
            System.out.println("");

            sc.close();
        }
    }

    public static void main(String[] args) {
        new Program().solve();
    }
}