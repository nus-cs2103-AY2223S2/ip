package eevee.exception;

public class TaskNoContentException extends Exception {
    public TaskNoContentException() {
        super("Eevee? I don't think you gave me any information about the task...");
    }
}
