package duke.exception;

public class WrongFormatException extends Exception{
    public WrongFormatException(String message) {
        super("Please enter in a correct format in this way:" + "'" + message + "'");
    }
}
