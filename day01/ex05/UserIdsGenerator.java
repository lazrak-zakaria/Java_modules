


public class UserIdsGenerator
{

    private static UserIdsGenerator userIdsGenerator;
    private static Integer          id = 0;

    private UserIdsGenerator()
    {

    }

    public static UserIdsGenerator getInstance()
    {
        if (userIdsGenerator == null)
            userIdsGenerator = new UserIdsGenerator();
        return userIdsGenerator;
    }

    public Integer generateId(){
        return ++id;
    }

}