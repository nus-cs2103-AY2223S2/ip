package src.main.c4po;

public class ListCommand extends Command{

    /**
     * An executable Command which lists all tasks in the task list
     */
    public ListCommand() {

    }

    /**
     * {@inheritDoc}
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList(true);
    }

    /**
     * {@inheritDoc}
     * @return boolean
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
