package duke.Exception;

public class InvalidArgumentsException extends DukeException {

    public InvalidArgumentsException() {
        super("Sorry, the arguments that you have input are in an incorrect format! Please try again!");
    }

    @Override
    public String getExceptionType() {
        return "Invalid Arguments";
    }
}
