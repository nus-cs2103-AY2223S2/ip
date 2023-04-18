package src.main.c4po;


public class MarkCommand extends Command {

    Integer indexToMark;

    public MarkCommand(Integer index) {
        assert(index > 0) : "index must be larger than 0";
        indexToMark = index;
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
        StringBuilder markResponse = new StringBuilder();
        try {
            boolean isSuccess = tasks.mark(indexToMark, "mark");
            if (isSuccess) {
                markResponse.append(Ui.showMarkedDone(true));
                //override storage file with all new tasks
                storage.writeToFile(tasks);
            } else {
                markResponse.append(Ui.showMarkFail(true));
            }

            markResponse.append("\n").append(tasks.getItem(indexToMark).getTaskInline());

        } catch (Exception e) {
            String markErr = "Sir! Index for toggling mark cannot be empty";
            throw new BotException(markErr);
        }
        return markResponse.toString();
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
