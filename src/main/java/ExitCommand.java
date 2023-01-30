public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Exit command does not execute anything
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}
