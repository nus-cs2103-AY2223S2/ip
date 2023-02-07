package src.main.c4po;

public class DeleteCommand extends Command {

    private final Integer toDeletePosition;

    /**
     * Creates a new delete command, an executable which deletes item at
     * specified position (1-indexed)
     * @param index
     */
    public DeleteCommand(Integer index) {
        this.toDeletePosition = index;
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
    public String execute(TaskList tasks, Ui ui, Storage storage,
                          boolean isStringOutput) throws BotException {
        StringBuilder deleteTaskResponse = new StringBuilder();
        try {
            Task toDelete = tasks.getItem(toDeletePosition);
            System.out.println(tasks.taskListByPriority);
            boolean isSuccess = tasks.deleteItem(toDelete);
            if (isSuccess) {
                deleteTaskResponse.append(Ui.showTaskDeletedQuote(true))
                        .append("\n")
                        .append(Ui.printTask(toDelete, true))
                        .append("\n");
            } else {
                deleteTaskResponse.append(Ui.showNoSuchTask(true));
            }

            deleteTaskResponse.append(tasks.getTaskCount());
            storage.writeToFile(tasks);
        } catch (Exception e) {
            throw new BotException(e.getMessage() + "Delete Failed");
        }
        return deleteTaskResponse.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
