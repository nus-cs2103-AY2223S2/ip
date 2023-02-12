package duke.exceptions;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(String str) {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
