class Program {

    public static int sum(int num)
    {
        int total = 0;
        while (num > 0) {
            total += num % 10;
            num /= 10;
        }
        return total;
    }

    public static void main(String[] args) {
        int num = 479598;
        System.out.println(sum(num));
    }
}