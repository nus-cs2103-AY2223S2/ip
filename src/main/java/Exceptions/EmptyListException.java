package Exceptions;

public class EmptyListException extends WessyException {
    public EmptyListException(String cmd) {
        super("You do not have any task on the list for you to " + cmd + ".");
    }
}
