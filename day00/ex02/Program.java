import java.util.Scanner;

class Program
{
    public static boolean isPrime(int n)
    {
        if (n <= 2)
            return false;
        for (int i = 2; i <= n/i; ++i){
            if (n%i==0)
                return false;
        }
        return true;

    }

    
    public static int sumDigit(Long n)
    {
        int ans = 0;
        while (n!=0){
            ans += n %10;
            n=n/10;
        }
        return ans;
    }

    public static void main(String []args)
    {
        Scanner sx= new Scanner(System.in);

        int ans = 0;
        while (sx.hasNextInt()){
            System.out.print("->");
            Long num = sx.nextLong();
            if (num == 42)
                break;
            int exNum = Program.sumDigit(num);
            if (isPrime(exNum))
                ans+=1;
        }
        System.out.print("Count of coffee-request : ");
        System.out.println(ans);

        
    }
}

/*




*/