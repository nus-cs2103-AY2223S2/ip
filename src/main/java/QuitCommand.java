public class QuitCommand extends Command {

    protected boolean toQuit;

    public QuitCommand() {
        toQuit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showFarewell();
    }

    /**
     * Returns whether command is a quitting command
     * @return
     */
    @Override
    public boolean isExit() {
        return this.toQuit;
    }
}
