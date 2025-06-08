




public class UsersArrayList implements UsersList{


    private User[]  users;
    private Integer index = 0;
    private Integer capacity = 10;
    
    public UsersArrayList() {
        users = new User[capacity];
    }

    
    private void reInsert(Integer start, Integer end, User[] from, User[] to){
        for (int i = start ; i < end; ++i){
            to[i] = from[i];
        }
    }

    @Override
    public void add(User user)
    {
        if (index.equals(capacity)){
            User[] newArray = new User[capacity + capacity/2];
            reInsert(0, capacity, users, newArray);

            capacity = capacity + capacity/2;
            users = newArray;
        }

        users[index++] = user;
    }

    @Override
    public User getUserById(Integer id)
    {
        for (User user : users)
        {
            if (user.getIdentifier().equals(id))
                return user;
        }
        return null; // throw later;
    }

    @Override
    public User getUserByIndex(Integer index)
    {
        if (index < 0 || index >= this.index){
            return null; // later throw exption
        }
        return users[index];
    }

    @Override
    public Integer size()
    {
        return index;
    }



}
