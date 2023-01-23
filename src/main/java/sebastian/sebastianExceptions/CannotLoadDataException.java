package sebastian.sebastianExceptions;


public class CannotLoadDataException extends SebastianException {
    public CannotLoadDataException() {
        super("Sorry, I cannot retrieve your past tasks. I'll create a new task list for you");
    }
}
