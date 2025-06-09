
import java.util.UUID;








public class TransactionsService {

    private UsersList usersList;

    public TransactionsService(UsersList usersList) {
        this.usersList = usersList;
    }

    public void addUser(User user){
        usersList.add(user);
    }

    public Integer getUserBalanceById(Integer id){

        return usersList.getUserById(id).getBalance(); 

    }


    public void transferTransaction(Integer senderId, Integer recipientId, Integer amount){

        User sender = usersList.getUserById(senderId);
        User recipient = usersList.getUserById(recipientId);

        if (sender.getBalance() > amount)
            throw new IllegalTransactionException("The amount exceeding user’s residual balance");

        if (amount <= 0)
            throw new IllegalTransactionException("The amount must be greater than 0");

        UUID id = UUID.randomUUID();
        
        Transaction credit = new Transaction( sender, recipient,'C', amount, id);
        Transaction debit  = new Transaction( sender, recipient, 'D', -amount, id);

        recipient.addTransaction(credit);
        recipient.setBalance(recipient.getBalance() + amount);

        sender.addTransaction(debit);
        sender.setBalance(sender.getBalance() - amount);

    }

    public Transaction[] getTransactionsByUser(Integer id){
        User user = usersList.getUserById(id);
        return user.getTransactionsList().toArray();
    }

    public void deleteTransactionByIdAndUser(UUID id, Integer userId){

        usersList.getUserById(userId)
        .getTransactionsList()
        .remove(id);
    }

    public Transaction[] checkValidTransactions(){

        TransactionsList transactionsList = new TransactionsLinkedList();

        for (int i = 0; i < usersList.size(); ++i){
            User user = usersList.getUserByIndex(i);

            Transaction[] userTransactions = user.getTransactionsList().toArray();

            for (Transaction userTransaction : userTransactions){
                boolean found = false;

                for (int j = 0; j < usersList.size(); ++j){
                    if (j == i)
                        continue;
                    User user2 = usersList.getUserByIndex(j);
                    Transaction[] user2Transactions = user2.getTransactionsList().toArray();

                    for (Transaction user2Transaction : user2Transactions){
                        if (userTransaction.getId() == user2Transaction.getId())
                        {
                            found = true;
                            break;
                        }
                    }
                    if (found)
                        break;
                }

                if (!found)
                    transactionsList.add(userTransaction);
            }

        }
        return transactionsList.toArray();
    }

    public User getUserById(Integer id){
        return usersList.getUserById(id);
    }   
}
