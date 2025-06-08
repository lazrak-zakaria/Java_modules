class Program{

    public static void main(String[] args){
        int n = 479598;
        int ans = 0;

        while (n!=0){
            ans += n % 10;
            n /= 10;
        }

        System.out.println(ans);
    }
}