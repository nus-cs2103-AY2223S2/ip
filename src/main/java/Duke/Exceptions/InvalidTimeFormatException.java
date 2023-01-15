package Duke.Exceptions;

public class InvalidTimeFormatException extends Exception {
    public InvalidTimeFormatException() {
        super(String.format(" ☹ OOPS!!! The input format is wrong! Please enter in the form of <event> by <time>. "));
    }
}
