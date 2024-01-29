package CampApplication.Exceptions;

public class UserIDException extends Exception{
    public UserIDException(){
        super("UserID not found in database");
    }
    public UserIDException(String errormessage){
        super(errormessage);
    }
}
