public interface UsersList {
    
    void add(User user);
    User getUserById(Integer id);
    User getUserByIndex(Integer index);
    Integer size();


}
