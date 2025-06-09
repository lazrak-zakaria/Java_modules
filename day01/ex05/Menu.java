
import java.util.Scanner;
import java.util.UUID;

public class Menu {


    TransactionsService transactionsService = new TransactionsService(null);

    private static void printErr(String msg)
    {
        System.err.println(msg);
    }

    public void displayMenu(String type)
    {

        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        
        if (type.equals("dev")){
            System.out.println("5. DEV - remove a transfer by ID");
            System.out.println("6. DEV - check transfer validity");
        }

        System.out.println("7. Finish execution");

    }





    private  void addUser() {
        
        System.out.println("Enter a user name and a balance");
        System.out.print("->");

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running){
            if (sc.hasNext()){

                String line = sc.nextLine();

                Scanner scanner = new Scanner(line);

                    
                if (!scanner.hasNextInt()){
                    printErr("Invalid Input: balance must be integer");
                    continue;
                }

                int balance = scanner.nextInt();

                if (!scanner.hasNext()){
                    printErr("Invalid Input: user name error");
                    continue;
                }
                //debug check if is alpha
                String username = scanner.next();

                User user = new User(username, balance, new TransactionsLinkedList());


                transactionsService.addUser(user);
                System.out.printf("User with id = %d is added\n", user.getIdentifier());
            }
            running = false; 

        }
    }

    private static void viewUserBalances() {
        System.out.println("Function: viewUserBalances() - Displaying user balances...");
        // TODO: Implement balance display logic
    }

    private void performTransfer() {

        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        System.out.print("->");

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running){
            if (sc.hasNext()){

                String line = sc.nextLine();

                Scanner scanner = new Scanner(line);

                if (!scanner.hasNextInt()){
                    printErr("Invalid Input: balance must be integer");
                    continue;
                }

                int senderId = scanner.nextInt();

                if (!scanner.hasNextInt()){
                    printErr("Invalid Input: balance must be integer");
                    continue;
                }

                int recipientId = scanner.nextInt();


                   if (!scanner.hasNextInt()){
                    printErr("Invalid Input: balance must be integer");
                    continue;
                }

                int amount = scanner.nextInt();


                transactionsService.transferTransaction(senderId, recipientId, amount);

            }
            running = false; 

        }
    }

    private void viewUserTransactions() {
        System.out.println("Enter a user ID");
        System.out.print("-> ");
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running){
            if (sc.hasNext()){

                String line = sc.nextLine();

                Scanner scanner = new Scanner(line);

                if (!scanner.hasNextInt()){
                    printErr("Invalid Input: id must be integer");
                    continue;
                }

                int userId = scanner.nextInt();

                Transaction[] transactions = transactionsService.getTransactionsByUser(userId);

                for (Transaction transaction : transactions){

                    if (transaction.getCategory() == 'C'){
                        System.out.printf("From %s(id = %d) %d with id = %s\n", 
                                transaction.getSender().getName(), 
                                transaction.getSender().getIdentifier(), 
                                transaction.getAmount(), 
                                transaction.getId()
                            );

                    }
                    else
                    {
                        System.out.printf("To %s(id = %d) %d with id = %s\n", 
                                transaction.getRecipient().getName(), 
                                transaction.getRecipient().getIdentifier(), 
                                transaction.getAmount(), 
                                transaction.getId()
                            );
                    }

                }


            }
            running = false; 

        }
        
    }

    private void removeTransferById() {
        System.out.println("Enter a user ID and a transfer ID");
        System.out.print("-> ");


        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running){
            if (sc.hasNext()){

                String line = sc.nextLine();

                Scanner scanner = new Scanner(line);

                if (!scanner.hasNextInt()){
                    printErr("Invalid Input: id must be integer");
                    continue;
                }

                int userId = scanner.nextInt();

                if (!scanner.hasNext()){
                    printErr("Invalid Input: must provide the transfer id");
                    continue;
                }

                String id = scanner.next();
                UUID transferId = UUID.fromString(id);
                
                transactionsService.deleteTransactionByIdAndUser(transferId, userId);
            }
        }
    }

    private static void checkTransferValidity() {
        System.out.println("Function: checkTransferValidity() - Checking transfer validity...");
        // TODO: Implement transfer validation logic
    }
    
}
