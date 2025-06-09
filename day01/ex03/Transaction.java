import java.util.UUID;

public class Transaction
{
    private final UUID        id;
    private User        recipient;
    private User        sender;
    private Character   category;
    
    private Integer     amount;


    


    public Transaction(User recipient, User sender, Character category, Integer amount ){

        boolean is_valid = true;

        switch (category) {
            case 'C':
                if (amount < 0)
                    is_valid = false;
                break;
            case 'D':
                if (amount > 0)
                    is_valid = false;
                break;
            default:
                is_valid = false;
                break;
        }
        
        if (!is_valid)
        {
            sender = null;
            recipient = null;
            category = '\0';
            amount = 0;
        }

        this.sender = sender;
        this.category = category;
        this.recipient = recipient;
        this.amount = amount;

        this.id = UUID.randomUUID();

    }








    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Character getCategory() {
        return category;
    }

    public void setCategory(Character category) {
        this.category = category;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public UUID getId(){
        return id;
    }
}