import java.util.Scanner;


//https://stackoverflow.com/questions/23218961/how-can-i-print-out-in-columns-in-java

public class Program
{

    public static void getMaxten(char [] maxTenKey, int [] maxTenValue){

        
        Scanner sc = new Scanner(System.in);
        System.out.print("-> ");
        if (sc.hasNextLine()) // protect null
        {
            String line = sc.nextLine();
            
            int [] frequency = new int[65536];
            for (int i = 0; i < line.length(); ++i){
                frequency[line.charAt(i)]++;
            }


            for (int i = 0 ; i < 10; ++i){
                
                int max = 0;
                for (int j = 1; j < frequency.length; ++j){
                    max = frequency[max] >= frequency[j] ? max : j;
                }
                maxTenKey[i] = (char)max;
                maxTenValue[i] = frequency[max];
                frequency[max] = 0;
            }
        }
    }


    public static void run()
    {
        char [] maxTenKey = new char[10];
        int [] maxTenValue = new int[10];
        int [] graphValue = new int[10];
        
        getMaxten(maxTenKey, maxTenValue);
        
        int max = maxTenValue[0];
        int valueMax = 10;

        if (max == 0)
            return;
        for (int i = 0 ; i < 10; ++i)
            graphValue[i] = maxTenValue[i] * valueMax / max;
        
        System.out.println();

        for (int i = 0 ; i < 11; ++i){
            for (int j = 0 ; j < 10; ++j){
                if (valueMax - i == graphValue[j] && graphValue[j] !=0){
                    System.out.printf("%3s",maxTenValue[j]);

                }
                else if (valueMax - i < graphValue[j])
                    System.out.printf("%3s","#");
                
            }
            System.out.println();
        }
        for (int i = 0 ; i < 10; ++i)
            System.out.printf("%3s", maxTenKey[i]);
        System.out.println();

            


    }








    
    public static void main(String[] args){
        Program.run();
    }
}   