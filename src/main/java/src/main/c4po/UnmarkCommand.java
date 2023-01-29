package src.main.c4po;
public class UnmarkCommand extends Command {

    private Integer indexToUnmark;
    public UnmarkCommand(Integer index) {
        this.indexToUnmark = index;
    }


    /**
     * {@inheritDoc}
     * In this case, UnmarkCommand unmarks the task specified
     * @param tasks are the list of tasks
     * @param ui is the instance of UI
     * @param storage the instance of Storage which holds and writes to the data file
     * @throws BotException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {

        try {
            boolean success = tasks.mark(indexToUnmark, "unmark");
            if (success) {
                Ui.showUnmarked();
                storage.writeToFile(tasks);
            } else {
                Ui.showMarkFail();
            }
            Ui.print(tasks.getItem(indexToUnmark).getTaskInline());
        } catch (Exception e) {
            String markErr = "Sir! Index for toggling mark cannot be empty";
            throw new BotException(e.getMessage() + markErr);
        }

    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
