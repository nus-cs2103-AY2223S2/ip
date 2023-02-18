package eevee.exception;

public class NoTaskToDeleteException extends Exception {
    public NoTaskToDeleteException() {
        super("Eevee? Cannot delete task if task does not exist...");
    }
}
