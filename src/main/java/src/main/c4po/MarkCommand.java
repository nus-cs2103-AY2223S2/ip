package src.main.c4po;


public class MarkCommand extends Command {

    Integer indexToMark;

    public MarkCommand(Integer index) {
        indexToMark = index;
    }

    /**
     * {@inheritDoc}
     * @param tasks are the list of tasks
     * @param ui is the instance of UI
     * @param storage the instance of Storage which holds and writes to the data file
     * @throws BotException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        try {
            boolean success = tasks.mark(indexToMark, "mark");
            if (success) {
                Ui.showMarkedDone();
                //override storage file with all new tasks
                storage.writeToFile(tasks);
            } else {
                Ui.showMarkFail();
            }

            Ui.print(tasks.getItem(indexToMark).getTaskInline());

        } catch (Exception e) {
            String markErr = "Sir! Index for toggling mark cannot be empty";
            throw new BotException(markErr);
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
