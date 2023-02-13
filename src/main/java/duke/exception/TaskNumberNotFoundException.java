package duke.exception;

/**
 * Thrown when the task number stated is not found in the task list of Duke.
 */
public class TaskNumberNotFoundException extends DukeException {
    /**
     * Thrown when the task number stated is not found in the task list of Duke.
     */
    public TaskNumberNotFoundException() {
        super("\n"
                + "Either there is no task with that number or you didn't format your command correctly!" + "\n"
                + "Use \"mark N\", \"unmark N\" or \"delete N\" where N is your task number." + "\n");
    }
}
