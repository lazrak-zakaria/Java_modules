

public class User
{

    private final Integer id;
    private String  name;
    private Integer balance;

    private TransactionsList    transactionsList;


    public User(String name, Integer balance, TransactionsList transactionsList)
    {
        if (balance < 0)
            this.balance = 0;
        if (name.isEmpty())
            this.name = "Default Name";
        this.id = UserIdsGenerator.getInstance().generateId();
        this.transactionsList = transactionsList;
    }

    public Integer getIdentifier() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void addTransaction(Transaction transaction){
        transactionsList.add(transaction);
    }

    public TransactionsList getTransactionsList()
    {
        return transactionsList;
    }
}