
import java.util.UUID;





public class TransactionsLinkedList implements TransactionsList
{

    class Node{

        public Node(Transaction transaction) {
            this.transaction = transaction;
        }
        
        
        Transaction transaction;
        Node next;
        Node prev;
    }



    Node head;
    Node tail;
    Integer size = 0;

    public TransactionsLinkedList(){
        
    }

    @Override
    public void add(Transaction transaction){
        
        Node newNode = new Node(transaction);

        if (head == null)
            head = newNode;
        else
        {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;

        ++size;
    }

    @Override
    public void remove(UUID id){

        Node temp = head;
        
        while (temp != null){
            if (temp.transaction.getId().equals(id)){

                Node prev = temp.prev;
                Node next = temp.next;

                if (prev != null)
                    prev.next = next;
                else
                    head = next;

                if (next != null)
                    next.prev = prev;
                else
                    tail = prev;
                
                --size;
            }
            temp = temp.next;
        }
    }

    @Override
    public Transaction[] toArray()
    {
        Transaction[] transactions = new Transaction[size];
        Node temp = head;
        for (int i = 0; i < size; ++i){
            transactions[i] = temp.transaction;
            temp = temp.next;
        }
        return transactions;
    }
}