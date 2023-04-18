package src.main.c4po;

public class ListCommand extends Command{

    /**
     * An executable Command which lists all tasks in the task list
     */
    public ListCommand() {

    }



    /**
     * Executes the command with actions specific to each extension of this
     * class Command
     *
     * @param tasks          are the list of tasks
     * @param ui             is the instance of UI
     * @param storage        the instance of Storage which holds and writes to the data file
     * @param isStringOutput
     * @throws BotException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, boolean isStringOutput) throws BotException {
        return tasks.printList(true,true);
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
