import java.util.Scanner;

class Program 
{


    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);


        int num = sc.nextInt();
        if (num < 2)
        {
            System.out.println("IllegalArgument");
            System.exit(-1);
            return;
        }
        boolean isPrime = true;
        int counter = 0;
        for (int i = 2; i <= num/i && isPrime; ++i)
        {
            counter++;
            if (num%i==0)
                isPrime = false;
        }
        if (isPrime)
            System.out.print("true ");
        else
            System.out.print("false ");
        System.out.println(counter);
    }


}