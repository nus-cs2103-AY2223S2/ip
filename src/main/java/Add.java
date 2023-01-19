/**
 * The Add command.
 * Adds a new command.
 * Inherits from the superclass Command.
 */
public class Add extends Command {
    private final Task taskToAdd;

    /**
     * Keeps track of the task to be added.
     * @param taskToAdd The task to be added.
     */
    public Add (Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }
}
