

public class User
{
    private static Integer id = 1;
    private final Integer identifier;
    private String  name;
    private Integer balance;



    public User(String name, Integer balance)
    {
        if (balance < 0)
            this.balance = 0;
        if (name.isEmpty())
            this.name = "Default Name";
        this.identifier = id++;
    }

    public Integer getIdentifier() {
        return identifier;
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

}