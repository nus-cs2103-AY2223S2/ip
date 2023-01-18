package Exceptions;

public class NoTaskDescriptionException extends Exception {
    public NoTaskDescriptionException(String string) {
        super("OOPS!!! The description of a " + string + " cannot be empty!");
    }
}
