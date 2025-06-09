
import java.util.UUID;




interface TransactionsList
{
    void add(Transaction transaction);
    void remove(UUID id);
    Transaction[]   toArray();
}