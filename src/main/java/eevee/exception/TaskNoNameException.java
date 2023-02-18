package eevee.exception;

import eevee.task.Task;

public class TaskNoNameException extends Exception {
    public TaskNoNameException() {
        super("Eevee? I don't think you gave me the name of the task...");
    }
}
