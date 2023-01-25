/**
 * Encapsulation of the command to list out all tasks.
 */
public class ListCommand extends Command {
    /**
     * Prints out all tasks in the list.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.print();
    }

    /**
     * Returns whether the program should exit or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
