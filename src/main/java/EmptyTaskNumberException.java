public class EmptyTaskNumberException extends Exception {

    public EmptyTaskNumberException() {
        super("No task number was given!");
    }
}
