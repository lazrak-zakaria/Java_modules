
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

    }

    @Override
    public void remove(UUID id){

    }

    @Override
    public Transaction[] toArray()
    {
        
    }
}