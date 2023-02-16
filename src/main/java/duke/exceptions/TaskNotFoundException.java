package duke.exceptions;

public class TaskNotFoundException extends Exception {
	public TaskNotFoundException() {
		super("There is no such task.");
	}
}
