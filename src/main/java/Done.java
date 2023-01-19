/**
 * The Done command.
 * Marks a command as done.
 * Inherits from the superclass Command.
 */
public class Done extends Command {
    private final int taskNumber;

    /**
     * Constructor of Done.
     * @param taskNumber The task number that is to be marked as done.
     */
    public Done(int taskNumber) {
        this.taskNumber = taskNumber;
    }
}
