import java.util.Scanner;

public class Program {

    private static boolean isWeekValid(String wk, int expectedWeek){

        String s = "Week " + expectedWeek;
        return s.equals(wk);
    }   

    private static void printErrorExit(){
        System.err.println("IllegalArgument");
        System.exit(-1);
    }

    private static void printAnswer(int db)
    {
        while (db != 0){
            int num = db % 10;
            db /= 10;
            while (num-- != 0)
                System.out.print("=");
            System.out.println(">");
        }
    }

    public static void run(){
        Scanner sc = new Scanner(System.in);

        int expectedWeek = 1;
        int db = 0;
        int multiple = 1;

        System.out.print("->");
        while (sc.hasNext()){

            String line = sc.nextLine();

            if (line.equals("42"))
                break;
            if (expectedWeek == 19){
                System.out.println("The maximum number of weeks for the analysis is 18");
                break;
            }
            if (!isWeekValid(line, expectedWeek)){
                printErrorExit();
            }

            int grade = 10;
            for (int i = 0; i < 5; ++i){
                int num = sc.nextInt();
                if (num < 1 || num > 9)
                    printErrorExit();
                grade = num < grade ? num : grade; 
            }
            db = grade * multiple + db;
            multiple *= 10;
            expectedWeek++;
        }
        printAnswer(db);

    }

    
    public static void main(String []args){
        Program.run();
    }

}
