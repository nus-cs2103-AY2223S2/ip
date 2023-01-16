package exceptions;
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String cmd) {
        super("☹ OOPS!!! I'm sorry, but I don't know what "+ cmd +" means :-(");
    }
}