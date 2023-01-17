public class NoDateException extends DukeException {
    public NoDateException(String task, Throwable err) {
        super("The date/time of the " + task + " cannot be empty.", err);
    }
}