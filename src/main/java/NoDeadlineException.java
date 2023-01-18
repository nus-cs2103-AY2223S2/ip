public class NoDeadlineException extends Exception {

    public NoDeadlineException() {
        super("No deadline was given!");
    }
}
