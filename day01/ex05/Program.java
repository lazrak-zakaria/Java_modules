
import java.util.Scanner;





public class Program {

    public static void main(String []args)
    {
        Scanner sc = new Scanner(System.in);
        String type = "dev";
        Menu menu = new Menu();
        while (sc. hasNext()){

            
            menu.displayMenu(type);

            if (sc.hasNextInt())
            {
                Menu.printErr("Should provide an integer value");
                continue;
            }


            int value = sc.nextInt();
            
            switch (value) {
                case 1:
                    menu.addUser();
                    break;
                case 2:
                    menu.viewUserBalances();
                    break;
                case 3:
                    menu.performTransfer();
                    break;
                case 4:
                    menu.viewUserTransactions();
                    break;
                case 5:
                    if (type.equals("dev"))
                        menu.removeTransferById();
                    else
                        return;
                    break;
                case 6:
                    if (type.equals("dev"))
                        menu.checkTransferValidity();
                    else
                        Menu.printErr("Option not found");
                    break;
                case 7:
                    if (type.equals("dev"))
                        return;
                    else
                        Menu.printErr("Option not found");
                    break;
                default:
                    Menu.printErr("Option not found");
            }

        }
        
    }
}