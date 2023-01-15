package Duke.Exceptions;

public class OutRangeException extends Exception {
    public OutRangeException() {
        super(String.format("☹ OOPS!!! The number you entered is out of range!"));
    }

}
