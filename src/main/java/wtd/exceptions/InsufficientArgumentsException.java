package wtd.exceptions;

public class InsufficientArgumentsException extends WtdException {
    public InsufficientArgumentsException(String arg, String usage) {
        super("You better specify the " + arg + " you want to " + usage + "...");
    }
    
    public InsufficientArgumentsException(String arg) {
        super("Specify the " + arg + "!");
    }
}
