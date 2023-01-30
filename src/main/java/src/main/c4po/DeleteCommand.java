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
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        try {
            Task toDelete = tasks.getItem(toDeletePosition);

            boolean isSuccess = tasks.deleteItem(toDelete);
            if (isSuccess) {
                Ui.showTaskDeletedQuote();
                Ui.printTask(toDelete);
            } else {
                Ui.showNoSuchTask();
            }

            Ui.print(tasks.getTaskCount());
            storage.writeToFile(tasks);
        } catch (Exception e) {
            throw new BotException(e.getMessage() + "Delete Failed");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
