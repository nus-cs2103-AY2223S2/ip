package src.main.c4po;
public class QuitCommand extends Command {

    protected boolean toQuit;

    public QuitCommand() {
        toQuit = true;
    }

    /**
     * {@inheritDoc}
     * @param tasks are the list of tasks
     * @param ui is the instance of UI
     * @param storage the instance of Storage which holds and writes to the data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showFarewell();
    }

    /**
     * {@inheritDoc}
     * Returns whether command is a quitting command
     * @return
     */
    @Override
    public boolean isExit() {
        return this.toQuit;
    }
}
