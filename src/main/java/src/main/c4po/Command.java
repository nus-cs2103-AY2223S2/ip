package src.main.c4po;

public abstract class Command {
    public boolean toQuit;

    /**
     * Executes the command with actions specific to each extension of this
     * class Command
     * @param tasks are the list of tasks
     * @param ui is the instance of UI
     * @param storage the instance of Storage which holds and writes to the data file
     * @throws BotException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BotException;

    /**
     * Returns whether a command should cause bot to end interaction
     * @return boolean to be used in the main loop
     */
    public abstract boolean isExit();

}
