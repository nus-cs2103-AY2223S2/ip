/**
 * The Delete command.
 * Deletes a task.
 * Inherits from the superclass Command.
 */
public class Delete extends Command {
    private final int taskNumber;

    public Delete(int taskNumber) {
        this.taskNumber = taskNumber;
    }
}
