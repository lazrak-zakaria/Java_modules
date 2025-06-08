

public class Program {

    public static void main(String []args)
    {
        User user1 = new User("user1", 90);
        User user2 = new User("user2", 80);


        Transaction t = new Transaction(user1, user2, 'C', 70);
        Transaction t1 = new Transaction(user1, user2, 'D', 80);
    
    
        
    }
}