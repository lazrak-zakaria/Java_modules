import java.util.Scanner;

public class Program {

    private static final int STUDENT_INSERT_SUCCESS = 0;
    private static final int STUDENT_ALREADY_EXIST = 1;
    private static final int STUDENTS_MAX = 2;
    private static final int STUDENTS_MAX_LENGTH = 3;
    private static final int STUDENT_NAME_NOT_ALPHA = 4;
    

    private static final int HERE = 1;
    private static final int NOT_HERE = 2;



    
    private static final String[] studentsName = new String[10];
    private static int studentsNameIndex = 0;

    private static final boolean [][]  timeTableClass = new boolean[7][6];
    private static final int [][][] AttendanceRecording = new int[31][10][6];
    private static final String[] days = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};



    private static int  getNumberOfDay(String day)
    {
        for (int i = 0; i < days.length; ++i){
            if (day.equals(days[i]))
                return i;
        }
        return -1;
    }



    private static int insertStudent(String name){
        
        if (studentsNameIndex == 10)
            return STUDENTS_MAX ;

        if (name.length()>10)
            return STUDENTS_MAX_LENGTH;

        for (char h : name.toCharArray())
            if (!((h >= 'a' && h <= 'z') || (h >= 'A' && h <= 'Z')))
                return STUDENT_NAME_NOT_ALPHA;
        
        for (String n : studentsName){
            if (n != null && n.equals(name))
                return STUDENT_ALREADY_EXIST;
        }

        studentsName[studentsNameIndex++] = name;
        return STUDENT_INSERT_SUCCESS;
    }


    private static int getStudentIndex(String name){
        for (int i = 0; i < studentsName.length; ++i)
            if (name.equals(studentsName[i]))
                return i;
        return -1;
    }



    private static void printErr(String msg){
        System.err.println(msg);
    }


    private static void studentsNamePart(String name){

        int answer = insertStudent(name);
        switch (answer) {
            case STUDENT_INSERT_SUCCESS:
                break;
            case STUDENTS_MAX:
                printErr("Maximum students is 10");
                break;
            case STUDENTS_MAX_LENGTH:
                printErr("Length of student name must not exceed 10");
                break;
            case STUDENT_ALREADY_EXIST:
                printErr("Student already exist in the table");
                break;
            case STUDENT_NAME_NOT_ALPHA:
                printErr("Student name must contain only alphabetic characters");
                break;
        }
    }

    private static void classesTimePart(String line){
        
        Scanner sc = new Scanner(line);

        if (!sc.hasNextInt()) {
            printErr("Invalid input: missing or invalid class time");
            sc.close();
            return;
        }

        int hour = sc.nextInt();

         if (!sc.hasNext()) {
            printErr("Invalid input: missing day");
            sc.close();
            return;
        }

        String day = sc.next();


        if (sc.hasNext()){
            printErr("Invalid input: must follow this pattern CLASS_HOUR DAY");
            sc.close();
            return;
        }

        sc.close();


        if (hour < 1 || hour > 5)
        {
            printErr("Hour must be between 1 pm and 6 pm (6 not included)");
            return;
        }
        
        int numOfDay = getNumberOfDay(day);
        if (numOfDay == -1)
        {
            printErr("Day not exist");
            return;
        }

        timeTableClass[numOfDay][hour] = true;
    }   





    private static void AttendanceRecordingPart(String line)
    {

        Scanner sc = new Scanner(line);

        if (!sc.hasNext()) {
            printErr("Invalid input: missing name");
            sc.close();
            return;
        }
        String name = sc.next();

        if (!sc.hasNextInt()) {
            printErr("Invalid input: missing or invalid class time");
            sc.close();
            return;
        }
        int classNum = sc.nextInt();

        if (!sc.hasNextInt()) {
            printErr("Invalid input: missing or invalid roll number");
            sc.close();
            return;
        }

        int day = sc.nextInt();

        if (!sc.hasNext()) {
            printErr("Invalid input: missing attendance status");
            sc.close();
            return;
        }
        String status = sc.next();

        if (!status.equals("HERE") && !status.equals("NOT_HERE")) {
            printErr("Invalid input: attendance status must be HERE or NOT_HERE");
            sc.close();
            return;
        }


        if (sc.hasNext()){
            printErr("Invalid input: must follow this pattern NAME CLASS_HOUR DAY STATUS");
            sc.close();
            return;
        }

        sc.close();


        if (classNum < 1 || classNum > 5){
            printErr("Invalid input: Hour must be between 1 pm and 6 pm (6 not included)");
            return;
        }

        if (day < 1 || day > 30)
        {
            printErr("Invalid input: Day must be between 1 and 30 (30 included)");
            return;
        }

        int here = HERE;
        if (status.equals("NOT_HERE"))
            here = NOT_HERE;
        
        
        int studentIndex = getStudentIndex(name);
        if (studentIndex == -1)
        {
            printErr("Student does not exits");
            return;
        }
        AttendanceRecording[day][studentIndex][classNum] = here;
    }







    private static void printTable(){

        // for (String s : studentsName)
        //     System.out.println(s);


        System.out.printf("%10s", "");
        for (int dayStart = 1; dayStart < 31; ++dayStart)
        {
                for (int j = 0 ; j < timeTableClass[0].length; ++j){
                    if (timeTableClass[dayStart % 7][j])
                    {
                        System.out.printf("%d:00 %s %2s| ", j, days[dayStart % 7], dayStart);
                    }
                } 
        }
        System.out.println();
        for (int i = 0; i < studentsNameIndex; ++i){

            System.out.printf("%10s", studentsName[i]);
            for (int dayStart = 1; dayStart < 31; ++dayStart)
            {
                    for (int j = 0 ; j < timeTableClass[0].length; ++j){
                        if (timeTableClass[dayStart % 7][j])
                        {
                            int value = AttendanceRecording[dayStart][i][j];
                            String answer = "";
                            if (value == HERE)
                                answer = "1";
                            else if (value == NOT_HERE)
                                answer = "-1";
                            System.out.printf("%10s| ", answer);
                        }
                    } 
            }
            System.out.println();
        }

    }



    public static void run()
    {
        final int NAMES = 0;
        final int CLASSES = 1;
        final int ATTENDANCE = 2;

        Scanner sc = new Scanner(System.in);

        int cursor = NAMES;
        while (sc.hasNextLine()) {
            String line =  sc.nextLine();
            

            if (line.equals("."))
            {
                if (cursor == ATTENDANCE)
                {
                    printTable();
                    return;
                }

                cursor += 1;
                continue;
            }



            switch (cursor) {
                case NAMES -> studentsNamePart(line);

                case CLASSES -> classesTimePart(line);

                case ATTENDANCE -> AttendanceRecordingPart(line);
            }

        }
        sc.close();
    }








    public static void main(String[] args) {
        
        
        Program.run();

    }    

    public static int[][][] getAttendanceRecording() {
        return AttendanceRecording;
    }
}
