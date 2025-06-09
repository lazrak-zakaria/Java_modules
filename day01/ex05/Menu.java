
import java.util.Scanner;
import java.util.UUID;

public class Menu {


    TransactionsService transactionsService = new TransactionsService(null);

    public static void printErr(String msg)
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
            System.out.println("7. Finish execution");
        }
        else
            System.out.println("5. Finish execution");

        

    }





    public  void addUser() {
        
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

    public void viewUserBalances() {

        System.out.println("Enter a user ID");
        System.out.print("->");

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
                
                scanner.close();
                sc.close();
                System.out.printf("%s - %d\n",
                        transactionsService.getUserById(userId),
                        transactionsService.getUserBalanceById(userId));
            }
            running = false;
        }

    }

    public void performTransfer() {

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

    public void viewUserTransactions() {
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

    public void removeTransferById() {
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

    public void checkTransferValidity() {

        System.out.println("Check results:");


        Transaction[] transactions = transactionsService.checkValidTransactions();

        for (Transaction transaction : transactions){

            if (transaction.getCategory() == 'C')
            {
                System.out.printf(
                    "%s (id = %d) has an unacknowledged transfer id = %s from %s (id = %d) for %d%n",
                    transaction.getRecipient().getName(),
                    transaction.getRecipient().getIdentifier(), 
                    transaction.getId(),
                    transaction.getSender().getName(),
                    transaction.getSender().getIdentifier(),
                    transaction.getAmount()
                );
            }
            else
            {
                System.out.printf(
                    "%s (id = %d) has an unacknowledged transfer id = %s to %s (id = %d) for %d%n",
                    transaction.getSender().getName(),
                    transaction.getSender().getIdentifier(),
                    transaction.getId(),
                    transaction.getRecipient().getName(),
                    transaction.getRecipient().getIdentifier(), 
                    transaction.getAmount()
                );
            }
        }
    }
    
}
