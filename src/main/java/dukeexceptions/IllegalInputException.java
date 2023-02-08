package dukeexceptions;

public class IllegalInputException extends DukeException{
    public IllegalInputException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "Sorry, your input for the command is invalid:" + getMessage();
    }
}
