package duke.exceptions;

public class InvalidDateException extends Exception {
    public InvalidDateException() {
        super("OOPS!!! Please key the date in the format: yyyy-mm-dd");
    }
}
