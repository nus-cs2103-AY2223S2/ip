package wtd.exceptions;

public class InvalidArgumentException extends WtdException {
    public InvalidArgumentException(String arg, String usage) {
        super("The argument " + arg + " you specified should be " + usage + " Get it right!");
    }
}
