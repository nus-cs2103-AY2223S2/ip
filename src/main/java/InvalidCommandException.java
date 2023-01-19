public class InvalidCommandException extends Exception {
    public String errorMessage;
    
    public InvalidCommandException(String err) {
        super(err);
        errorMessage = err;
    }
}